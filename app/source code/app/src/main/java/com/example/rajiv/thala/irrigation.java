package com.example.rajiv.thala;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class irrigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation);
        int warn= Color.parseColor("#FF0000");
        final int black= Color.parseColor("#000000");
        final int normal = Color.parseColor("#00FF00");
        int usual = Color.parseColor("#000000");
        final RelativeLayout main;
        final ProgressBar p1,progbar;
        final TextView health;
        String level;
        level="";
        main=(RelativeLayout) findViewById(R.id.main);
        main.setBackgroundColor(usual);
        p1=(ProgressBar) findViewById(R.id.prog);
        progbar=(ProgressBar) findViewById(R.id.progbar);
        progbar.setVisibility(View.INVISIBLE);
        health=(TextView)findViewById(R.id.healthines);
       /* try {
            level=red.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       level="37";
        Log.i("status",level);
        health.setTextColor(Color.parseColor("#FFFFFF"));
        health.setText("Scanning");

        final String finalLevel = level;
        new Handler().postDelayed(new Runnable() {





                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    p1.setVisibility(View.GONE);
                   /* main.setBackgroundColor(normal);
                    health.setTextColor(black);*/
                    health.setText("Mositure level:"+finalLevel +"%");
                    progbar.setVisibility(View.VISIBLE);
                    progbar.setProgress(Integer.parseInt(finalLevel));
                    // close this activity

                }
            }, 16000);




    }
}
