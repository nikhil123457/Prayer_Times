package com.vitcode.iprayertimes.dateconverter;


import java.util.Calendar;

public class APC_Time {
    public static double Mjd(int Year, int Month, int Day, int Hour, int Min, double Sec) {
        int b;
        if (Month <= 2) {
            Month += 12;
            Year--;
        }
        if ((10000 * ((long) Year)) + (100 * ((long) Month)) + ((long) Day) <= 15821004) {
            b = (((Year + 4716) / 4) - 2) - 1179;
        } else {
            b = ((Year / 400) - (Year / 100)) + (Year / 4);
        }
        return ((double) (((365 * ((long) Year)) - 679004) + ((long) b) + ((long) ((int) (30.6001d * ((double) (Month + 1))))) + ((long) Day))) + (APC_Math.Ddd(Hour, Min, Sec) / 24.0d);
    }

    public static Calendar CalDat(double Mjd) {
        long c;
        Calendar cal = Calendar.getInstance();
        long a = (long) (2400001.0d + Mjd);
        if (a < 2299161) {
            c = a + 1524;
        } else {
            long b = (long) ((((double) a) - 1867216.25d) / 36524.25d);
            c = ((a + b) - (b / 4)) + 1525;
        }
        long d = (long) ((((double) c) - 122.1d) / 365.25d);
        long e = (365 * d) + (d / 4);
        long f = (long) (((double) (c - e)) / 30.6001d);
        int Month = (int) ((f - 1) - (12 * (f / 14)));
        double hour = 24.0d * (Mjd - Math.floor(Mjd));
        int Minute = (int) Math.round((hour - ((double) ((int) hour))) * 60.0d);
        cal.set(1, (int) ((d - 4715) - ((long) ((Month + 7) / 10))));
        cal.set(2, Month - 1);
        cal.set(5, (int) ((c - e) - ((long) ((int) (30.6001d * ((double) f))))));
        cal.set(11, (int) hour);
        cal.set(12, Minute);
        return cal;
    }
}
