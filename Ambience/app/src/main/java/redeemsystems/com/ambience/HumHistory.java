package redeemsystems.com.ambience;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static redeemsystems.com.ambience.MainActivity.arrayList;

public class HumHistory extends AppCompatActivity {
    LineGraphSeries<DataPoint> series1, series2, series3, series4;
    GraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hum_history);
        graph = (GraphView) findViewById(R.id.graph);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                series1 = new LineGraphSeries<>(new DataPoint[]{

                        new DataPoint(0, Float.parseFloat(arrayList.get(0).getExternal_humidity_1())),
                        new DataPoint(1, Float.parseFloat(arrayList.get(1).getExternal_humidity_1())),
                        new DataPoint(2, Float.parseFloat(arrayList.get(2).getExternal_humidity_1())),
                        new DataPoint(4, Float.parseFloat(arrayList.get(3).getExternal_humidity_1())),
                        new DataPoint(5, Float.parseFloat(arrayList.get(4).getExternal_humidity_1())),
                        new DataPoint(6, Float.parseFloat(arrayList.get(5).getExternal_humidity_1())),
                        new DataPoint(7, Float.parseFloat(arrayList.get(6).getExternal_humidity_1())),
                        new DataPoint(8, Float.parseFloat(arrayList.get(7).getExternal_humidity_1())),
                        new DataPoint(9, Float.parseFloat(arrayList.get(8).getExternal_humidity_1()))


                });
                graph.addSeries(series1);
                series2 = new LineGraphSeries<>(new DataPoint[]{

                        new DataPoint(0, Float.parseFloat(arrayList.get(0).getExternal_humidity_2())),
                        new DataPoint(1, Float.parseFloat(arrayList.get(1).getExternal_humidity_2())),
                        new DataPoint(2, Float.parseFloat(arrayList.get(2).getExternal_humidity_2())),
                        new DataPoint(4, Float.parseFloat(arrayList.get(3).getExternal_humidity_2())),
                        new DataPoint(5, Float.parseFloat(arrayList.get(4).getExternal_humidity_2())),
                        new DataPoint(6, Float.parseFloat(arrayList.get(5).getExternal_humidity_2())),
                        new DataPoint(7, Float.parseFloat(arrayList.get(6).getExternal_humidity_2())),
                        new DataPoint(8, Float.parseFloat(arrayList.get(7).getExternal_humidity_2())),
                        new DataPoint(9, Float.parseFloat(arrayList.get(8).getExternal_humidity_2()))



                });
                graph.addSeries(series2);
                series3 = new LineGraphSeries<>(new DataPoint[]{

                        new DataPoint(0, Float.parseFloat(arrayList.get(0).getExternal_humidity_3())),
                        new DataPoint(1, Float.parseFloat(arrayList.get(1).getExternal_humidity_3())),
                        new DataPoint(2, Float.parseFloat(arrayList.get(2).getExternal_humidity_3())),
                        new DataPoint(4, Float.parseFloat(arrayList.get(3).getExternal_humidity_3())),
                        new DataPoint(5, Float.parseFloat(arrayList.get(4).getExternal_humidity_3())),
                        new DataPoint(6, Float.parseFloat(arrayList.get(5).getExternal_humidity_3())),
                        new DataPoint(7, Float.parseFloat(arrayList.get(6).getExternal_humidity_3())),
                        new DataPoint(8, Float.parseFloat(arrayList.get(7).getExternal_humidity_3())),
                        new DataPoint(9, Float.parseFloat(arrayList.get(8).getExternal_humidity_3()))



                });
                graph.addSeries(series3);
                series4 = new LineGraphSeries<>(new DataPoint[]{

                        new DataPoint(0, Float.parseFloat(arrayList.get(0).getFreezer_humidity())),
                        new DataPoint(1, Float.parseFloat(arrayList.get(1).getFreezer_humidity())),
                        new DataPoint(2, Float.parseFloat(arrayList.get(2).getFreezer_humidity())),
                        new DataPoint(4, Float.parseFloat(arrayList.get(3).getFreezer_humidity())),
                        new DataPoint(5, Float.parseFloat(arrayList.get(4).getFreezer_humidity())),
                        new DataPoint(6, Float.parseFloat(arrayList.get(5).getFreezer_humidity())),
                        new DataPoint(7, Float.parseFloat(arrayList.get(6).getFreezer_humidity())),
                        new DataPoint(8, Float.parseFloat(arrayList.get(7).getFreezer_humidity())),
                        new DataPoint(9, Float.parseFloat(arrayList.get(8).getFreezer_humidity()))


                });
                graph.addSeries(series4);

            }}, 2000);
    }
}
