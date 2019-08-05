package redeemsystems.com.ambience;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;

import static redeemsystems.com.ambience.MainActivity.arrayList;

public class StatusHistory extends AppCompatActivity {

    RecyclerView recycler;
    MyAdapter myAdapter;
    LinearLayoutManager manager;

    public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>
    {

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.row, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView ID, status, Temp1, Hum1, Temp2, Hum2, Temp3, Hum3, FTemp, FHumidity, vc_new1, Door1, Power;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                ID = itemView.findViewById(R.id.ID);
                status = itemView.findViewById(R.id.Status);
                Temp1 = itemView.findViewById(R.id.Temp1);
                Hum1 = itemView.findViewById(R.id.Hum1);
                Temp2 = itemView.findViewById(R.id.Temp2);
                Hum2 = itemView.findViewById(R.id.hum2);
                Temp3 = itemView.findViewById(R.id.Temp3);
                Hum3 = itemView.findViewById(R.id.Hum3);
                FTemp = itemView.findViewById(R.id.FTemp);
                FHumidity = itemView.findViewById(R.id.FHumidity);
                vc_new1 = itemView.findViewById(R.id.vc_new);
                Door1 = itemView.findViewById(R.id.Door_State);
                Power = itemView.findViewById(R.id.Power);
            }
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
        {
            MyBean myBean = arrayList.get(i);
            viewHolder.ID.setText(myBean.getRefrigerator_id());
            viewHolder.status.setText(myBean.getRefrigerator_status());
            viewHolder.Temp1.setText(myBean.getExternal_temperature_1());
            viewHolder.Hum1.setText(myBean.getExternal_humidity_1());
            viewHolder.Temp2.setText(myBean.getExternal_temperature_2());
            viewHolder.Hum2.setText(myBean.getExternal_humidity_2());
            viewHolder.Temp3.setText(myBean.getExternal_temperature_3());
            viewHolder.Hum3.setText(myBean.getExternal_humidity_3());
            viewHolder.FTemp.setText(myBean.getFreezer_temperature());
            viewHolder.FHumidity.setText(myBean.getFreezer_humidity());
            viewHolder.vc_new1.setText(myBean.getVc_new());
            viewHolder.Door1.setText(myBean.getDoor_state());
            viewHolder.Power.setText(myBean.getPower_consumption());

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_history);

        recycler = findViewById(R.id.recycler);
        myAdapter = new MyAdapter();
        recycler.setAdapter(myAdapter);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
