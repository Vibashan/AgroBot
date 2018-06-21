package com.example.rajiv.thala;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.rajiv.thala.datasend.connected;
import static com.example.rajiv.thala.datasend.out;

public class proportionselect extends AppCompatActivity {
    SeekBar seekbar1,seekbar2,seekbar3,seekbar4;
    RelativeLayout overall;
    ProgressBar over;
    int field_area=100,yield=0,profit=0;
    Animation anim;
    TextView yieldq,profitq;
    int seek1=0,seek2=0,seek3=0,seek4=0;
    TextView percent1,percent2,percent3,percent4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantselection);
        over = (ProgressBar) findViewById(R.id.overallfill);
        seekbar1 = (SeekBar) findViewById(R.id.percentbar1);
        seekbar2 = (SeekBar) findViewById(R.id.percentbar2);
        overall=(RelativeLayout) findViewById(R.id.overall);
        yieldq=(TextView) findViewById(R.id.yield);
        profitq=(TextView) findViewById(R.id.profit);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadesmall);
        overall.setAnimation(anim);
        seekbar3 = (SeekBar) findViewById(R.id.percentbar3);
        seekbar4 = (SeekBar) findViewById(R.id.percentbar4);
        percent1=(TextView) findViewById(R.id.percentdisp1);
        percent2=(TextView) findViewById(R.id.percentdisp2);
        percent3=(TextView) findViewById(R.id.percentdisp3);
        percent4=(TextView) findViewById(R.id.percentdisp4);
        percent1.setText("0%");
        percent2.setText("0%");
        percent3.setText("0%");
        percent4.setText("0%");
        yieldq.setText("");
        profitq.setText("");
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               seek1=progress;
               if(!sum(seek1,seek2,seek3,seek4))
               seekbar1.setProgress(progress-1);
               percent1.setText(seekbar1.getProgress()*10+"%");
               yieldset(seek1,seek2,seek3,seek4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek2=progress;
                if(!sum(seek1,seek2,seek3,seek4))
                    seekbar2.setProgress(progress-1);
                percent2.setText(seekbar2.getProgress()*10+"%");
                yieldset(seek1,seek2,seek3,seek4);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek3=progress;
                if(!sum(seek1,seek2,seek3,seek4))
                    seekbar3.setProgress(progress-1);
                percent3.setText(seekbar3.getProgress()*10+"%");
                yieldset(seek1,seek2,seek3,seek4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek4=progress;
                if(!sum(seek1,seek2,seek3,seek4))
                    seekbar4.setProgress(progress-1);
                percent4.setText(seekbar4.getProgress()*10+"%");
                yieldset(seek1,seek2,seek3,seek4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    boolean sum(int a,int b,int c,int d)
    {
        if(a+b+c+d>10)
            return false;
        else
            return true;
    }
    void yieldset(int a,int b,int c,int d)
    {
        profit=a*300+b*500+c*400+d*600;
        profitq.setVisibility(View.GONE);
        yield=a*16+b*5+c*11+d*6;
        profitq.setText("Profit (INR):"+profit);

        yieldq.setText("Yield (in Kgs/sq.metre):"+(float)(yield)/100);
        over.setProgress(a+b+c+d);

    }
    void startbutton(View view)
    {
        DialogFragment newFragment = plantalert.newInstance("Do you want to continue with this proportion?");
        newFragment.show(getFragmentManager(), "Dialog");


    }

    public void doPositiveClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Positive click!");
        //send data to laptop and never reach this screen again
        if(connected)
        out.println(seekbar1.getProgress()*10+" " +seekbar2.getProgress()*10+" "+seekbar3.getProgress()*10+" "+seekbar4.getProgress()*10+" ");
        Intent i = new Intent(this,startexploring.class);
        startActivity(i);
    }

    public void doNegativeClick() {
        // Do stuff here.

    }


}
