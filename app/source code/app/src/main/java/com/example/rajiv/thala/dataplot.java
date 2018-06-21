package com.example.rajiv.thala;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class dataplot extends AppCompatActivity {
    GraphView g1,g2,g3,g4;
    Animation animatefade1,animatefade2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int graphcolor = Color.parseColor("#FF0000");
        int green = Color.parseColor("#00FF00");
        int axescolor = Color.parseColor("#001200");
        int plain = Color.parseColor("#FFFFFF");
        setContentView(R.layout.activity_dataplot);
        g1=(GraphView) findViewById(R.id.graph1);
        g2 = (GraphView) findViewById(R.id.graph2);
        g3=(GraphView) findViewById(R.id.graph3);
        g4=(GraphView) findViewById(R.id.graph4);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(1, 2),
                new DataPoint(2, 3),
                new DataPoint(3, 5),
                new DataPoint(4, 7),
                new DataPoint(5, 10),
                new DataPoint(6, 14)
        });
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 3),
                new DataPoint(3, 4),
                new DataPoint(4, 6),
                new DataPoint(5, 8),
                new DataPoint(6, 11)
        });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(1, 2),
                new DataPoint(2, 4),
                new DataPoint(3, 6),
                new DataPoint(4, 9),
                new DataPoint(5, 14),
                new DataPoint(6, 19)
        });
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 4),
                new DataPoint(3, 6),
                new DataPoint(4, 7),
                new DataPoint(5, 12),
                new DataPoint(6, 15)
        });
        LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 5),
                new DataPoint(3, 8),
                new DataPoint(4, 9),
                new DataPoint(5, 12),
                new DataPoint(6, 15)
        });
        LineGraphSeries<DataPoint> series6 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 2),
                new DataPoint(3, 4),
                new DataPoint(4, 3),
                new DataPoint(5, 7),
                new DataPoint(6, 8)
        });
        series1.setColor(graphcolor);
        series1.setTitle("Expected");
        series2.setTitle("Actual");
        series2.setColor(green);
        series3.setColor(graphcolor);
        series4.setColor(green);
        series3.setTitle("Expected");
        series4.setTitle("Actual");
        animatefade1  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        animatefade2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        series1.setDrawDataPoints(true);
        series1.setDataPointsRadius(10);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);
        g1.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        g1.getGridLabelRenderer().setVerticalAxisTitle("Growth actual/expected");
        g1.getGridLabelRenderer().setVerticalAxisTitleColor(axescolor);
        g1.setTitle("Lettuce");
        g1.setBackgroundColor(plain);

        g1.startAnimation(animatefade1);
        g1.setTitleTextSize(30);
        g2.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        g2.getGridLabelRenderer().setVerticalAxisTitle("Growth actual/expected");
        g2.getGridLabelRenderer().setVerticalAxisTitleColor(axescolor);
        g2.setTitle("Pea");
        g2.setBackgroundColor(plain);
        g2.setVisibility(View.INVISIBLE);
        g2.setTitleTextSize(30);
        g1.addSeries(series1);
        g1.addSeries(series2);
        g2.addSeries(series3);
        g2.addSeries(series4);
        g1.getLegendRenderer().setVisible(true);
        g1.getLegendRenderer().setBackgroundColor(Color.parseColor("#FFFFFF"));
        g1.getLegendRenderer().setFixedPosition(2,15);
        g1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        g2.getLegendRenderer().setVisible(true);
        g2.getLegendRenderer().setBackgroundColor(Color.parseColor("#FFFFFF"));
        g2.getLegendRenderer().setFixedPosition(2,15);
        g2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        g3.getLegendRenderer().setVisible(true);
        g3.getLegendRenderer().setBackgroundColor(Color.parseColor("#FFFFFF"));
        g3.getLegendRenderer().setFixedPosition(2,15);
        g3.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        g4.getLegendRenderer().setVisible(true);
        g4.getLegendRenderer().setBackgroundColor(Color.parseColor("#FFFFFF"));
        g4.getLegendRenderer().setFixedPosition(2,15);
        g4.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        g3.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        g3.getGridLabelRenderer().setVerticalAxisTitle("Growth actual/expected");
        g3.getGridLabelRenderer().setVerticalAxisTitleColor(axescolor);
        g3.setTitle("Spinach");
        g3.setBackgroundColor(plain);
        g4.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        g4.getGridLabelRenderer().setVerticalAxisTitle("Growth actual/expected");
        g4.getGridLabelRenderer().setVerticalAxisTitleColor(axescolor);
        g4.setTitle("Corn");
        g4.setBackgroundColor(plain);
        g3.setTitleTextSize(30);
        g4.setTitleTextSize(30);
        g3.addSeries(series1);
        g3.addSeries(series2);
        g4.addSeries(series3);
        g4.addSeries(series4 );



        animatefade1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            g2.startAnimation(animatefade2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animatefade2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
              g2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }
}
