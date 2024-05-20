package com.vitcode.iprayertimes.prayerTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class AlarmReceiver extends BroadcastReceiver {
    String time;
    String type;

    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle extras = intent.getExtras();
        try {
            if (extras.getString("type") != null || extras.getString("time") != null) {
                this.type = extras.getString("type");
                this.time = extras.getString("time");
                Intent intent1 = new Intent(context, ActivityAlarm.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("type", this.type);
                intent1.putExtra("time", this.time);
                context.startActivity(intent1);
            }
        } catch (NullPointerException e) {
        }
    }
}
