package com.example.rajiv.thala;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class bottag extends AppCompatActivity {
ImageView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottag);
         t = (ImageView) findViewById(R.id.boticon);
        t.setVisibility(View.INVISIBLE);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade2);
        t.setAnimation(anim);
        anim.start();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(bottag.this, startexploring.class);
                        startActivity(i);

                        // close this activity
                        finish();
                    }
                }, 1800);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
