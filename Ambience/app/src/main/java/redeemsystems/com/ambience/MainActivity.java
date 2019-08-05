package redeemsystems.com.ambience;

import android.app.AlertDialog;
import android.app.usage.UsageStats;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity
{
    TextView E_Temp, E_Hum, F_Temp, F_Hum, R_Status, D_State, E_num, power, datetime, Temp_2, Temp_3, Hum_2, Hum_3;
    String e_temp, e_hum, f_temp, f_hum, r_status, d_state, e_num, power1, temp_2, temp_3, hum_2, hum_3;
    MyTask myTask;
    int count = 0;
    LinearLayout statushistory, temphistory, humdityhistory;
    MyTask1 myTask1;
    String refrigerator_id, refrigerator_status, External_temperature_1,External_humidity_1, External_temperature_2,
            External_humidity_2, External_temperature_3, External_humidity_3, freezer_temperature, freezer_humidity,
            vc_new, Door_state, power_consumption;
    static ArrayList<MyBean> arrayList;
    static String date;
    static Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        E_Temp = findViewById(R.id.E_Temp);
        E_Hum = findViewById(R.id.E_Hum);
        F_Temp = findViewById(R.id.F_Temp);
        F_Hum = findViewById(R.id.F_Hum);
        R_Status = findViewById(R.id.R_Status);
        D_State = findViewById(R.id.D_State);
        datetime = findViewById(R.id.dateTime);
        E_num = findViewById(R.id.E_num);
        power = findViewById(R.id.power);
        Temp_2 = findViewById(R.id.Temp_2);
        Temp_3 = findViewById(R.id.Temp_3);
        Hum_2 = findViewById(R.id.Hum_2);
        Hum_3 = findViewById(R.id.Hum_3);
        statushistory = findViewById(R.id.statushistory);
        temphistory = findViewById(R.id.temphistory);
        humdityhistory = findViewById(R.id.humdityhistory);
        arrayList = new ArrayList<>();

        if (!checkInternet()){
//            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Connection Error");
            ab.setMessage("Unable to connect with the server. Check your internet connection and try again.");
            ab.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 check();

                }
            });
            ab.show();
            return;
        }
       check();
    }

    public void check()
    {
        myTask1 = new MyTask1();
        myTask1.execute();



        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(c.getTime());
        datetime.setText(date);
        currentTime = Calendar.getInstance().getTime();
        Log.d("joan", "Current time: " +currentTime);

        statushistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StatusHistory.class);
                startActivity(intent);
            }
        });
        temphistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String url = "http://182.74.149.122:8080/ambience/temperature.php";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        humdityhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String url = "http://182.74.149.122:8080/ambience/humidity.php";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        callAsynchronousTask();

    }
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            count++;
                            myTask = new MyTask();
                            myTask.execute();
                        } catch (Exception e) {
                            Log.d("joan", e.toString());
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 10000); //execute in every 50000 ms
    }

    public class MyTask extends AsyncTask<Void, Void, String>
    {
        URL url;
        HttpURLConnection connection;
        InputStream inputstream;
        InputStreamReader inputstreamreader;
        BufferedReader bufferreader;
        StringBuilder sb = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Toast.makeText(MainActivity.this, "starting", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            try {

                    url = new URL("http://182.74.149.122:8080/ambience/ambience_json.php?a=RS001");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Accept", "application/json");
                    Log.d("check", "success");

                    //                connection.setRequestProperty("appKey", "65a73f5e-3877-4881-818b-d46b89be7783");
                    Log.d("check", "success");

                    //                connection.setRequestProperty("Content-Type", "application/json");
                    Log.d("check", "success");

                    inputstream = connection.getInputStream();

                    Log.d("check", "success");
                    inputstreamreader = new InputStreamReader(inputstream);
                    bufferreader = new BufferedReader(inputstreamreader);

                    String str = bufferreader.readLine();

                    while (str != null) {
                        sb.append(str);
                        str = bufferreader.readLine();
                    }
                Log.d("joan", "Response: "+sb.toString());
                return sb.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("check", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("check", e.toString());

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("B39", "some other exception : " + e);
            }
            return null;
        }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject obj = new JSONObject(s);
                e_temp = obj.getString("External_temperature_1");
                e_hum = obj.getString("External_humidity_1");
                f_temp = obj.getString("Freezer_temperature");
                f_hum = obj.getString("Freezer_humidity");
                r_status = obj.getString("refrigerator_status");
                d_state = obj.getString("Door_state");
                e_num = obj.getString("Refrigerator_id");
                power1 = obj.getString("power_consumption");
                temp_2 = obj.getString("External_temperature_2");
                temp_3 = obj.getString("External_temperature_3");
                hum_2 = obj.getString("External_humidity_2");
                hum_3 = obj.getString("External_humidity_3");

                E_Temp.setText(e_temp+"\u2103");
                E_Hum.setText(Math.round(Float.parseFloat(e_hum))+"%H");
                F_Temp.setText((f_temp+"\u2103"));
                F_Hum.setText(Math.round(Float.parseFloat(f_hum))+"%H");
                R_Status.setText(r_status);
                if(d_state.equals("Alert")) {
                    D_State.setText(d_state);
                    D_State.setTextColor(Color.RED);
                    D_State.setTypeface(D_State.getTypeface(), Typeface.BOLD);
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(50); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    D_State.startAnimation(anim);
                }
                else
                {
                    D_State.setText(d_state);
                    D_State.setTextColor(Color.CYAN);
                    D_State.setTypeface(D_State.getTypeface(), Typeface.NORMAL);
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(50); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    D_State.startAnimation(anim);
                }
                E_num.setText(e_num);
                power.setText(power1+"kw-h");
                Temp_2.setText(temp_2+"\u2103");
                Temp_3.setText(temp_3+"\u2103");
                Hum_2.setText(Math.round(Float.parseFloat(hum_2))+"%H");
                Hum_3.setText(Math.round(Float.parseFloat(hum_3))+"%H");

                Log.d("joan", "Loop : "+count);
            }
            catch (Exception e)
            {
                Log.d("joan", e.toString());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//
    }

    @Override
    protected void onPause() {
        super.onPause();
//
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public class MyTask1 extends AsyncTask<Void, Void, String>
    {
        URL url;
        HttpURLConnection connection;
        InputStream inputstream;
        InputStreamReader inputstreamreader;
        BufferedReader bufferreader;
        StringBuilder sb = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Toast.makeText(MainActivity.this, "starting", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            try {

                url = new URL("http://182.74.149.122:8080/ambience/ambience_json.php?a=RS001&b=1");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                Log.d("check", "success");

                //                connection.setRequestProperty("appKey", "65a73f5e-3877-4881-818b-d46b89be7783");
                Log.d("check", "success");

                //                connection.setRequestProperty("Content-Type", "application/json");
                Log.d("check", "success");

                inputstream = connection.getInputStream();

                Log.d("check", "success");
                inputstreamreader = new InputStreamReader(inputstream);
                bufferreader = new BufferedReader(inputstreamreader);

                String str = bufferreader.readLine();

                while (str != null) {
                    sb.append(str);
                    str = bufferreader.readLine();
                }
                Log.d("joan", "Response: "+sb.toString());
                return sb.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("check", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("check", e.toString());

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("B39", "some other exception : " + e);
            }
            return null;
        }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Log.d("joan", s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    refrigerator_id = obj.getString("refrigerator_id");
                    refrigerator_status = obj.getString("refrigerator_status");
                    External_temperature_1 = obj.getString("External_temperature_1");
                    External_humidity_1 = obj.getString("External_humidity_1");
                    External_temperature_2 = obj.getString("External_temperature_2");
                    External_humidity_2 = obj.getString("External_humidity_2");
                    External_temperature_3 = obj.getString("External_temperature_3");
                    External_humidity_3 = obj.getString("External_humidity_3");
                    freezer_temperature = obj.getString("freezer_temperature");
                    freezer_humidity = obj.getString("freezer_humidity");
                    vc_new = obj.getString("vc_new");
                    Door_state = obj.getString("Door_state");
                    power_consumption = obj.getString("power_consumption");

                    MyBean myBean = new MyBean(refrigerator_id, refrigerator_status, External_temperature_1,
                            External_humidity_1, External_temperature_2, External_humidity_2, External_temperature_3, External_humidity_3,
                            freezer_temperature, freezer_humidity, vc_new, Door_state, power_consumption);
                    arrayList.add(myBean);


                }
//                myAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean checkInternet(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}
