package com.vitcode.iprayertimes.calender.helper;

import android.content.Context;
import android.content.res.Resources;

import androidx.exifinterface.media.ExifInterface;

public class NumbersLocal {
    public static String convertNumberType(Context context, String str) {
        try {
            if (context.getResources().getConfiguration().locale.getDisplayLanguage().equals("العربية")) {
                return str.replaceAll("0", "٠").replaceAll("1", "١").replaceAll(ExifInterface.GPS_MEASUREMENT_2D, "٢").replaceAll(ExifInterface.GPS_MEASUREMENT_3D, "٣").replaceAll("4", "٤").replaceAll("5", "٥").replaceAll("6", "٦").replaceAll("7", "٧").replaceAll("8", "٨").replaceAll("9", "٩");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String convertToNumberTypeSystem(Context context, String str) {
        try {
            if (Resources.getSystem().getConfiguration().locale.getDisplayLanguage().equals("العربية")) {
                return str.replaceAll("0", "٠").replaceAll("1", "١").replaceAll(ExifInterface.GPS_MEASUREMENT_2D, "٢").replaceAll(ExifInterface.GPS_MEASUREMENT_3D, "٣").replaceAll("4", "٤").replaceAll("5", "٥").replaceAll("6", "٦").replaceAll("7", "٧").replaceAll("8", "٨").replaceAll("9", "٩");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String convertNumberTypeToEnglish(Context context, String str) {
        try {
            if (context.getResources().getConfiguration().locale.getDisplayLanguage().equals("العربية")) {
                return str.replaceAll("٠", "0").replaceAll("١", "1").replaceAll("٢", ExifInterface.GPS_MEASUREMENT_2D).replaceAll("٣", ExifInterface.GPS_MEASUREMENT_3D).replaceAll("٤", "4").replaceAll("٥", "5").replaceAll("٦", "6").replaceAll("٧", "7").replaceAll("٨", "8").replaceAll("٩", "9");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
