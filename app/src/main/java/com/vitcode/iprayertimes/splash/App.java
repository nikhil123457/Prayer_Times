package com.vitcode.iprayertimes.splash;

import android.app.Application;
import android.util.Log;

public class App extends Application {



    private static App app;
    private static App instance;




    public static App get() {
        return app;
    }
    public void onCreate() {
        super.onCreate();
        Log.d("newtagg", "onCreate: app class boshi");
        app = this;
        instance = this;
    }

}
