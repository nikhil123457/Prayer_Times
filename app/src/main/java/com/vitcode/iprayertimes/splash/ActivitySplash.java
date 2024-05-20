package com.vitcode.iprayertimes.splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.dateconverter.Utils;

public class ActivitySplash extends Utils {
    ProgressDialog showProgressDialog;

    private SeekBar progressBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_splash_screen);

        Pref.getInstance().init(getApplicationContext()); // Initialize Pref instance with a Context

        progressBar = findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulate loading process
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(30); // Adjust delay as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setProgress(i);
                }
                if (Pref.getInstance().getString(Constant.INTRO).equals("intro") &&
                        Pref.getInstance().getString(Constant.TERMS).equals("terms") &&
                        Pref.getInstance().getString(Constant.LANG).equals("lang")) {
                    Intent intent = new Intent(ActivitySplash.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                } else if (Pref.getInstance().getString(Constant.INTRO).equals("intro") &&
                        Pref.getInstance().getString(Constant.TERMS).equals("terms")) {
                    Intent intent = new Intent(ActivitySplash.this, LanguageActivity.class);
                    startActivity(intent);
                    finish();
                } else if (Pref.getInstance().getString(Constant.INTRO).equals("intro")) {
                    Intent intent = new Intent(ActivitySplash.this, TermsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ActivitySplash.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }
}