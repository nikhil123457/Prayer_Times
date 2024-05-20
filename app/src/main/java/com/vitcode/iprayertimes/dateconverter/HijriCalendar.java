package com.vitcode.iprayertimes.dateconverter;

import java.util.Calendar;

public class HijriCalendar {
    final double Acc = 9.506426344208685E-9d;
    private int Lunation;
    private double MJD;
    final double MJD_J2000 = 51544.5d;
    final double MLunatBase = 23435.90347d;
    private Calendar cal;
    private double crescentMoonMoment;
    final double dT = 1.916495550992471E-4d;
    final double dTc = 8.213552361396303E-5d;
    private int hijriDay;
    private int hijriMonth;
    private int hijriYear;
    private boolean[] isFound;
    private String[] ismiSuhiri = {"ÙØ­Ø±Ù", "ØµÙ?Ø±", "Ø±ØšÙØ¹ Ø§ÙØ£ÙÙ", "Ø±ØšÙØ¹ Ø§ÙØ¢Ø®Ø±", "Ø¬ÙØ§Ø¯Ù Ø§ÙØ£ÙÙÙ", "Ø¬ÙØ§Ø¯Ù Ø§ÙØ¢Ø®Ø±Ø©", "Ø±Ø¬Øš", "ØŽØ¹ØšØ§Ù", "Ø±ÙØ¶Ø§Ù", "ØŽÙØ§Ù", "Ø°Ù Ø§ÙÙØ¹Ø¯Ø©", "Ø°Ù Ø§ÙØ­Ø¬Ø©"};
    private double newMoonMoment;
    final double synmonth = 29.530588861d;

    public HijriCalendar(int Year, int Month, int Day) {
        this.MJD = APC_Time.Mjd(Year, Month, Day, 0, 0, 0.0d);
        this.cal = APC_Time.CalDat(this.MJD);
        double T1 = (this.MJD - 51544.5d) / 36525.0d;
        double T0 = T1 - 1.916495550992471E-4d;
        this.isFound = new boolean[1];
        this.isFound[0] = false;
        MoonPhases newMoon = new NewMoon();
        CrescentMoon crescentMoon = new CrescentMoon();
        double D1 = newMoon.calculatePhase(T1);
        double D0 = newMoon.calculatePhase(T0);
        while (true) {
            if (D0 * D1 <= 0.0d && D1 >= D0) {
                break;
            }
            T1 = T0;
            D1 = D0;
            T0 -= 1.916495550992471E-4d;
            D0 = newMoon.calculatePhase(T0);
        }
        double TNewMoon = APC_Math.Pegasus(newMoon, T0, T1, 9.506426344208685E-9d, this.isFound);
        this.newMoonMoment = (36525.0d * TNewMoon) + 51544.5d;
        this.Lunation = ((int) Math.floor(((this.newMoonMoment + 7.0d) - 23435.90347d) / 29.530588861d)) + 1;
        this.hijriYear = ((this.Lunation + 4) / 12) + 1341;
        this.hijriMonth = ((this.Lunation + 4) % 12) + 1;
        if (this.isFound[0]) {
            this.crescentMoonMoment = (36525.0d * APC_Math.Pegasus(crescentMoon, TNewMoon, TNewMoon + 8.213552361396303E-5d, 9.506426344208685E-9d, this.isFound)) + 51544.5d;
        }
        this.hijriDay = ((int) (this.MJD - ((double) Math.round(this.crescentMoonMoment + 0.279166666666667d)))) + 1;
        if (this.hijriDay == 0) {
            this.hijriDay = 30;
            this.hijriMonth--;
            if (this.hijriMonth == 0) {
                this.hijriMonth = 12;
            }
        }
    }

    public int getHijriYear() {
        return this.hijriYear;
    }

    public String getHijriMonthName() {
        return this.ismiSuhiri[this.hijriMonth - 1];
    }

    public int getHijriMonth() {
        return this.hijriMonth;
    }

    public int getHijriDay() {
        return this.hijriDay;
    }

    public String getHicriTakvim() {
        return getHijriDay() + " " + getHijriMonthName() + " " + getHijriYear();
    }

    public String checkIfHolyDay() {
        String holyDay = "";
        switch (this.hijriMonth) {
            case 1:
                if (this.hijriDay == 1) {
                    return "NEWYEAR";
                }
                if (this.hijriDay == 10) {
                    return "ASHURA";
                }
                return holyDay;
            case 3:
                if (this.hijriDay == 11 || this.hijriDay == 12) {
                    return "MAWLID";
                }
                return holyDay;
            case 7:
                if (this.hijriDay == 1 && this.hijriMonth == 7) {
                    holyDay = "HOLYMONTHS";
                }
                if (this.cal.get(7) == 6 && this.hijriDay < 7) {
                    holyDay = "RAGHAIB";
                }
                if (this.hijriDay == 27) {
                    return "MERAC";
                }
                return holyDay;
            case 8:
                if (this.hijriDay == 15) {
                    return "BARAAT";
                }
                return holyDay;
            case 9:
                if (this.hijriDay == 27) {
                    return "QADR";
                }
                return holyDay;
            case 10:
                if (this.hijriDay == 1 || this.hijriDay == 2 || this.hijriDay == 3) {
                    return this.hijriDay + "DAYOFEIDFITR";
                }
                return holyDay;
            case 12:
                if (this.hijriDay == 9) {
                    holyDay = "AREFE";
                }
                if (this.hijriDay == 10 || this.hijriDay == 11 || this.hijriDay == 12 || this.hijriDay == 13) {
                    return (this.hijriDay - 9) + "DAYOFEIDAHDA";
                }
                return holyDay;
            default:
                return holyDay;
        }
    }

    public String getDay() {
        return new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"}[this.cal.get(7) - 1];
    }
}
