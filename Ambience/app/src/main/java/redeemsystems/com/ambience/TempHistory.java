package redeemsystems.com.ambience;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
import java.util.Calendar;
import java.util.Date;

import static redeemsystems.com.ambience.MainActivity.arrayList;

public class TempHistory extends AppCompatActivity {


    LineGraphSeries<DataPoint> series1, series2, series3, series4;
    GraphView graph;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_history);



        graph = (GraphView) findViewById(R.id.graph);
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        GraphView graph = (GraphView) findViewById(R.id.graph);

// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3)
        });

        graph.addSeries(series);

//
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                        series1 = new LineGraphSeries<>(new DataPoint[]{
//
//                                new DataPoint(Double.parseDouble(arrayList.get(0).getVc_new()), Float.parseFloat(arrayList.get(0).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(1).getVc_new()), Float.parseFloat(arrayList.get(1).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(2).getVc_new()), Float.parseFloat(arrayList.get(2).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(3).getVc_new()), Float.parseFloat(arrayList.get(3).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(4).getVc_new()), Float.parseFloat(arrayList.get(4).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(5).getVc_new()), Float.parseFloat(arrayList.get(5).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(6).getVc_new()), Float.parseFloat(arrayList.get(6).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(7).getVc_new()), Float.parseFloat(arrayList.get(7).getExternal_temperature_1())),
//                                new DataPoint(Double.parseDouble(arrayList.get(8).getVc_new()), Float.parseFloat(arrayList.get(8).getExternal_temperature_1()))
//
//
//                        });
//                        graph.addSeries(series1);
//
//                    series2 = new LineGraphSeries<>(new DataPoint[]{
//
//                            new DataPoint(0, Float.parseFloat(arrayList.get(0).getExternal_temperature_2())),
//                            new DataPoint(1, Float.parseFloat(arrayList.get(5).getExternal_temperature_2())),
//                            new DataPoint(2, Float.parseFloat(arrayList.get(6).getExternal_temperature_2())),
//                            new DataPoint(4, Float.parseFloat(arrayList.get(7).getExternal_temperature_2())),
//                            new DataPoint(5, Float.parseFloat(arrayList.get(8).getExternal_temperature_2())),
//                            new DataPoint(6, Float.parseFloat(arrayList.get(9).getExternal_temperature_2())),
//                            new DataPoint(7, Float.parseFloat(arrayList.get(10).getExternal_temperature_2())),
//                            new DataPoint(8, Float.parseFloat(arrayList.get(11).getExternal_temperature_2())),
//                            new DataPoint(9, Float.parseFloat(arrayList.get(12).getExternal_temperature_2()))
//
//
//                    });
////                    graph.addSeries(series2);
//                    series3 = new LineGraphSeries<>(new DataPoint[]{
//
//                            new DataPoint(0, Float.parseFloat(arrayList.get(0).getExternal_temperature_3())),
//                            new DataPoint(1, Float.parseFloat(arrayList.get(5).getExternal_temperature_3())),
//                            new DataPoint(2, Float.parseFloat(arrayList.get(6).getExternal_temperature_3())),
//                            new DataPoint(4, Float.parseFloat(arrayList.get(7).getExternal_temperature_3())),
//                            new DataPoint(5, Float.parseFloat(arrayList.get(8).getExternal_temperature_3())),
//                            new DataPoint(6, Float.parseFloat(arrayList.get(9).getExternal_temperature_3())),
//                            new DataPoint(7, Float.parseFloat(arrayList.get(10).getExternal_temperature_3())),
//                            new DataPoint(8, Float.parseFloat(arrayList.get(11).getExternal_temperature_3())),
//                            new DataPoint(9, Float.parseFloat(arrayList.get(12).getExternal_temperature_3()))
//
//
//                    });
////                    graph.addSeries(series3);
//                    series4 = new LineGraphSeries<>(new DataPoint[]{
//
//                            new DataPoint(0, Float.parseFloat(arrayList.get(0).getFreezer_temperature())),
//                            new DataPoint(1, Float.parseFloat(arrayList.get(5).getFreezer_temperature())),
//                            new DataPoint(2, Float.parseFloat(arrayList.get(6).getFreezer_temperature())),
//                            new DataPoint(4, Float.parseFloat(arrayList.get(7).getFreezer_temperature())),
//                            new DataPoint(5, Float.parseFloat(arrayList.get(8).getFreezer_temperature())),
//                            new DataPoint(6, Float.parseFloat(arrayList.get(9).getFreezer_temperature())),
//                            new DataPoint(7, Float.parseFloat(arrayList.get(10).getFreezer_temperature())),
//                            new DataPoint(8, Float.parseFloat(arrayList.get(11).getFreezer_temperature())),
//                            new DataPoint(9, Float.parseFloat(arrayList.get(12).getFreezer_temperature()))
//
//
//                    });
////                    graph.addSeries(series4);
//
//                    }}, 2000);

    }



}
