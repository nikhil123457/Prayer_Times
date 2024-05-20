package com.vitcode.iprayertimes.splash;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;


public class MyApp extends Application {

    public static final String f21148r = MyApp.class.getSimpleName();
    public static MyApp f21149s;
    public static int selectedAgePosition = -1;
    public static int selectedLanguagePosition = -1;

    public static MyApp f21150t;

    public Context f21151q;

    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        getApplicationContext();
        Pref.getInstance().init(getApplicationContext());
        f21151q = this;
        f21149s = this;
        f21150t = this;
    }
}
