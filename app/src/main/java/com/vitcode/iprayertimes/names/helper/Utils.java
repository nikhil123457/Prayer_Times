package com.vitcode.iprayertimes.names.helper;

import android.content.Context;
import android.content.res.Resources;

public class Utils {
    public static String milliSecondsToTimer(long j) {
        String str;
        String str2;
        int i = (int) (j / 3600000);
        long j2 = j % 3600000;
        int i2 = ((int) j2) / 60000;
        int i3 = (int) ((j2 % 60000) / 1000);
        if (i > 0) {
            str = i + ":";
        } else {
            str = "";
        }
        if (i3 < 10) {
            str2 = "" + i3;
        } else {
            str2 = "" + i3;
        }
        return str + i2 + ":" + str2;
    }

    public static String milliSecondsToHourMinute(long j) {
        String str;
        String str2;
        int i = (int) (j / 3600000);
        int i2 = ((int) (j % 3600000)) / 60000;
        if (i > 1) {
            str = i + " hours ";
        } else if (i == 1) {
            str = i + " hour ";
        } else {
            str = "";
        }
        if (i2 > 1) {
            str2 = i2 + " minutes";
        } else {
            str2 = i2 + " minute";
        }
        return str + str2;
    }

    public static String milliSecondsToTimerDown(long j) {
        String str;
        String str2;
        String str3;
        int i = (int) (j / 3600000);
        long j2 = j % 3600000;
        int i2 = ((int) j2) / 60000;
        int i3 = (int) ((j2 % 60000) / 1000);
        if (i <= 0) {
            str = "";
        } else if (i < 10) {
            str = "" + i + ":";
        } else {
            str = "" + i + ":";
        }
        if (i2 < 10) {
            str2 = "" + i2;
        } else {
            str2 = "" + i2;
        }
        if (i3 < 10) {
            str3 = "" + i3;
        } else {
            str3 = "" + i3;
        }
        return str + str2 + ":" + str3;
    }

    public static String minutesToTimerDown(long j) {
        String str = "" + ((int) (j / 3600000));
        String str2 = "" + (((int) (j % 3600000)) / 60000);
        if (str.equals("")) {
            return str2 + " min";
        }
        return str + " hour " + str2 + " min";
    }

    public static int getName99Resource(Context context, int i) {
        Resources resources = context.getResources();
        return resources.getIdentifier("a_" + (i + 1), "raw", context.getPackageName());
    }
}
