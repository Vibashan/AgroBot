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

import java.io.IOException;

import static com.example.rajiv.thala.datasend.red;

public class healthy extends AppCompatActivity {
    ProgressBar p1;
    TextView health;
    RelativeLayout main;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthiness);
        int warn= Color.parseColor("#FF0000");
        final int black= Color.parseColor("#000000");
        final int normal = Color.parseColor("#00FF00");
        int usual = Color.parseColor("#000000");

        main=(RelativeLayout) findViewById(R.id.main);
        main.setBackgroundColor(usual);
        p1=(ProgressBar) findViewById(R.id.prog);
        health=(TextView)findViewById(R.id.healthines);
        try {
            status=red.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("status",status);

        if(status.equalsIgnoreCase("healthy"))
        {
            health.setTextColor(Color.parseColor("#FFFFFF"));
            health.setText("Scanning");

            new Handler().postDelayed(new Runnable() {





                @Override
                public void run() {
                    

                    p1.setVisibility(View.GONE);
                    main.setBackgroundColor(normal);
                    health.setTextColor(black);
                    health.setText("Healthy!!");
                    

                }
            }, 16000);
        }
        else {
            p1.setVisibility(View.GONE);
            main.setBackgroundColor(normal);
        }


    }
}
