package com.example.rajiv.thala;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class startexploring extends AppCompatActivity {
    Button ps,ps1,ps2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startexp);
      /*  ps=(Button) findViewById(R.id.ps);
       // ps1=(Button) findViewById(R.id.ps1);
        //ps2 = (Button) findViewById(R.id.ps2);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.faderepeat);
        ps.setAnimation(anim);
        ps1.setAnimation(anim);
        ps2.setAnimation(anim);
        anim.start();*/
    }
    public void opt2func(View view)
    {
//        out.println("scan");
        Intent i=new Intent(this,dataplot.class);
        startActivity(i);
    }
    public void opt1func(View view)
    {

        Intent i=new Intent(this,proportionselect.class);
        startActivity(i);
    }
    public void opt3func(View view)
    {

        Intent i=new Intent(this,remainstats.class);
        startActivity(i);
    }
}
