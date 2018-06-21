package com.example.rajiv.thala;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
//

public class remainstats extends AppCompatActivity {
    ProgressBar pesticide,p1prog,p2prog,p3prog,p4prog,wedicide;

    TextView wedicidestat,pesticidestat,p1view,p2view,p3view,p4view;
    Button weed,pest,pb1,pb2,pb3,pb4;
    public static int wedi=10,pesti=12,p1=10,p2=17,p3=46,p4=87;
    RelativeLayout overall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainstats);
        int warn= Color.parseColor("#FF0000");
        int normal = Color.parseColor("#000000");
        wedicidestat=(TextView) findViewById(R.id.wedicideview);
        pesticidestat=(TextView) findViewById(R.id.pesticideview);
        pest =(Button) findViewById(R.id.pestibuy);
        pb1 = (Button) findViewById(R.id.p1buy);
        pb2 = (Button) findViewById(R.id.p2buy);
        pb3 = (Button) findViewById(R.id.p3buy);
        pb4 = (Button) findViewById(R.id.p4buy);
        weed = (Button) findViewById(R.id.wedibuy);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadesmall);

        overall = (RelativeLayout) findViewById(R.id.overall);
        overall.setVisibility(View.INVISIBLE);
        overall.setAnimation(anim);
        anim.start();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            overall.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        p1prog = (ProgressBar) findViewById(R.id.p1progress);
        p2prog = (ProgressBar) findViewById(R.id.p2progress);
        p3prog = (ProgressBar) findViewById(R.id.p3progress);
        p4prog = (ProgressBar) findViewById(R.id.p4progress);
        p1view = (TextView) findViewById(R.id.p1text);
        p2view = (TextView) findViewById(R.id.p2text);
        p3view = (TextView) findViewById(R.id.p3text);
        p4view = (TextView) findViewById(R.id.p4text);
        wedicide= (ProgressBar) findViewById(R.id.wedicideleft);
        pesticide = (ProgressBar) findViewById(R.id.pesticideleft);
        wedicide.setProgress(wedi);
        pesticide.setProgress(pesti);
        weed.setVisibility(View.INVISIBLE);
        pest.setVisibility(View.INVISIBLE);

        overall = (RelativeLayout) findViewById(R.id.overall);
        wedicidestat.setText("Wedicide left:"+wedi+"%");
        pesticidestat.setText("Pesticide left:"+pesti+"%");
        setgeneral(p1prog,pb1,p1view,p1,"Lettuce");
        setgeneral(p2prog,pb2,p2view,p2,"Pea");
        setgeneral(p3prog,pb3,p3view,p3,"Spinach");
        setgeneral(p4prog,pb4,p4view,p4,"Corn");
        if(wedi<20)
        {

            weed.setVisibility(View.VISIBLE);
            //set
        }
        if(pesti<20)
        {

            pest.setVisibility(View.VISIBLE);
        }
    }
    public void setgeneral(ProgressBar p,Button q,TextView t,int val,String s)
    {
        p.setProgress(val);
        q.setVisibility(View.INVISIBLE);
        if(val<20)
            q.setVisibility(View.VISIBLE);
        t.setText(s+":"+val+"%");
    }
    public void buyweedicide(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/s/ref=nb_sb_ss_i_2_9?url=search-alias%3Daps&field-keywords=weedicide+for+lawn&sprefix=weedicide%2Caps%2C306&crid=1B9LRRMAWNNR6"));
        startActivity(viewIntent);
    }
    public void buypesticide(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/s/ref=nb_sb_ss_c_1_4?url=search-alias%3Daps&field-keywords=pesticides+for+plants&sprefix=pest%2Caps%2C845&crid=3N72N61LQ0H5C&rh=i%3Aaps%2Ck%3Apesticides+for+plants"));
        startActivity(viewIntent);
    }
    public void buyp1(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/s/ref=nb_sb_ss_i_3_8?url=search-alias%3Daps&field-keywords=lettuce+seeds+for+home+garden&sprefix=lettuce+%2Caps%2C309&crid=2HWK5AIXNFIYN"));
        startActivity(viewIntent);
    }
    public void buyp2(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/s/ref=nb_sb_ss_i_1_25?url=search-alias%3Daps&field-keywords=pea+seeds+for+home+garden&sprefix=pea+seeds+for+home+garden%2Caps%2C1046&crid=2U9CKNW32JDQU&rh=i%3Aaps%2Ck%3Apea+seeds+for+home+garden"));
        startActivity(viewIntent);
    }
    public void buyp3(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=spinach+seeds+for+home+garden&rh=i%3Aaps%2Ck%3Aspinach+seeds+for+home+garden"));
        startActivity(viewIntent);
    }
    public void buyp4(View view)
    {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.amazon.in/Divine-Tree-Sweet-Seeds-Garden/dp/B078T2WP8M/ref=sr_1_6?ie=UTF8&qid=1519741882&sr=8-6&keywords=corn+seeds+for+home+garden"));
        startActivity(viewIntent);
    }
}
