package com.example.hp.waverrmerchant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Created by HP on 21-05-2015.
 */
public class Main extends ActionBarActivity implements View.OnClickListener{

    ImageButton deal,announcement,stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deal=(ImageButton)findViewById(R.id.adddeal);
        announcement=(ImageButton)findViewById(R.id.addannouncement);
        deal.setOnClickListener(this);
        announcement.setOnClickListener(this);

        setTitle("Merchants");

    }


    @Override
    public void onClick(View v) {
       switch(v.getId())
        {
            case R.id.adddeal: Intent i = new Intent(this,AddDeal.class);startActivity(i);break;
            case R.id.addannouncement: i = new Intent(this,AddAnnouncement.class);startActivity(i);break;

        }
    }
}