package com.vitcode.iprayertimes.compass.helper;


import com.vitcode.iprayertimes.splash.App;
import com.vitcode.iprayertimes.Mosques.LocationSave;
import com.vitcode.iprayertimes.R;

public class CompassManager {
    public static int getCompass() {
        int i = App.get().getSharedPreferences(LocationSave.class.getSimpleName(), 0).getInt("compass", R.drawable.compass_1);
        return (i == R.drawable.compass_1 || i == R.drawable.compass_2 || i == R.drawable.compass_3 || i == R.drawable.compass_4 || i == R.drawable.compass_5) ? i : R.drawable.compass_1;
    }

    public static int getCompassK() {
        int i = App.get().getSharedPreferences(LocationSave.class.getSimpleName(), 0).getInt("compass_k", R.drawable.compass_1);
        return (i == R.drawable.compass_1_k || i == R.drawable.compass_2_k || i == R.drawable.compass_3_k || i == R.drawable.compass_4_k || i == R.drawable.compass_5_k) ? i : R.drawable.compass_1_k;
    }

    public static void setCompass(int i) {
        App.get().getSharedPreferences(LocationSave.class.getSimpleName(), 0).edit().putInt("compass", i).apply();
    }

    public static void setCompassK(int i) {
        App.get().getSharedPreferences(LocationSave.class.getSimpleName(), 0).edit().putInt("compass_k", i).apply();
    }
}
