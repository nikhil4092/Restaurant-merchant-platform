package com.example.hp.waverrmerchant;


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.v7.app.ActionBarActivity;
        import android.view.Window;
        import android.view.WindowManager;


public class Splash extends Activity {


    private int SPLASH_TIME = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(SPLASH_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                Intent c1 = new Intent(Splash.this,Main.class);
                startActivity(c1);
            }
        }.start();


    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}