package com.vitcode.iprayertimes.dateconverter;


public class GregorianCalendar {
    /* access modifiers changed from: package-private */
    public int intPart(int floatNum) {
        if (((double) ((float) floatNum)) < -1.0E-7d) {
            return (int) Math.ceil(((double) floatNum) - 1.0E-7d);
        }
        return (int) Math.floor(((double) floatNum) + 1.0E-7d);
    }

    /* access modifiers changed from: package-private */
    public int[] chrToIsl(int y, int m, int d, int diff) {
        int jd;
        if (y > 1582 || ((y == 1582 && m > 10) || (y == 1582 && m == 10 && d > 14))) {
            jd = (((intPart((((y + 4800) + intPart((m - 14) / 12)) * 1461) / 4) + intPart((((m - 2) - (intPart((m - 14) / 12) * 12)) * 367) / 12)) - intPart((intPart(((y + 4900) + intPart((m - 14) / 12)) / 100) * 3) / 4)) + d) - 32075;
        } else {
            jd = ((y * 367) - intPart((((y +5001) + intPart((m - 9) / 7)) * 7) / 4)) + intPart((m * 275) / 9) + d + 1729777;
        }
        int l = (jd - 1948440) + 10632;
        int n = intPart((l - 1) / 10631);
        int l2 = (l - (n * 10631)) + 354 + diff;
        int j = (intPart((10985 - l2) / 5316) * intPart((l2 * 50) / 17719)) + (intPart(l2 / 5670) * intPart((l2 * 43) / 15238));
        int l3 = ((l2 - (intPart((30 - j) / 15) * intPart((j * 17719) / 50))) - (intPart(j / 16) * intPart((j * 15238) / 43))) + 29;
        int m2 = intPart((l3 * 24) / 709);
        return new int[]{l3 - intPart((m2 * 709) / 24), m2, ((n * 30) + j) - 30};
    }

    /* access modifiers changed from: package-private */
    public int[] islToChr(int y, int m, int d, int diff) {
        int d2;
        int m2;
        int y2;
        int jd = ((((((intPart(((y * 11) + 3) / 30) + (y * 354)) + (m * 30)) - intPart((m - 1) / 2)) + d) + 1948440) - 385) - diff;
        if (jd > 2299160) {
            int l = jd + 68569;
            int n = intPart((l * 4) / 146097);
            int l2 = l - intPart(((146097 * n) + 3) / 4);
            int i = intPart(((l2 + 1) * 4000) / 1461001);
            int l3 = (l2 - intPart((i * 1461) / 4)) + 31;
            int j = intPart((l3 * 80) / 2447);
            d2 = l3 - intPart((j * 2447) / 80);
            int l4 = intPart(j / 11);
            m2 = (j + 2) - (l4 * 12);
            y2 = ((n - 49) * 100) + i + l4;
        } else {
            int j2 = jd + 1402;
            int k = intPart((j2 - 1) / 1461);
            int l5 = j2 - (k * 1461);
            int n2 = intPart((l5 - 1) / 365) - intPart(l5 / 1461);
            int i2 = (l5 - (n2 * 365)) + 30;
            int j3 = intPart((i2 * 80) / 2447);
            d2 = i2 - intPart((j3 * 2447) / 80);
            int i3 = intPart(j3 / 11);
            m2 = (j3 + 2) - (i3 * 12);
            y2 = (((k * 4) + n2) + i3) - 4716;
        }
        return new int[]{d2, m2, y2};
    }

    /* access modifiers changed from: package-private */
    public String[] isToString(int y, int m, int d, int diff) {
        int[] iArr = new int[3];
        int[] res = chrToIsl(y, m, d, diff);
        return new String[]{Integer.toString(res[0]), new String[]{"Muharram", "Safar", "Rabi-al Awwal", "Rabi-al Thani", "Jumada al-Ula", "Jumada al-Thani", "Rajab", "Sha'ban", "Ramadhan", "Shawwal", "Dhul Qa'dah", "Dhul Hijjah"}[res[1] - 1], new String[]{"ÃâŠÃÂ­ÃÂ±ÃâÃâŠ", "ÃÂµÃÂ?ÃÂ±", "ÃÂ±ÃÂšÃÅ ÃÂ¹ ÃÂ§ÃâÃÂ£ÃËÃâ", "ÃÂ±ÃÂšÃÅ ÃÂ¹ ÃÂ§ÃâÃÂ«ÃÂ§Ãâ ÃÅ ", "ÃÂ¬ÃâŠÃÂ§ÃÂ¯Ãâ° ÃÂ§ÃâÃÂ£ÃËÃâÃâ°", "ÃÂ¬ÃâŠÃÂ§ÃÂ¯Ãâ° ÃÂ§ÃâÃÂ«ÃÂ§Ãâ ÃÅ ", "ÃÂ±ÃÂ¬ÃÂš", "ÃÂŽÃÂ¹ÃÂšÃÂ§Ãâ ", "ÃÂ±ÃâŠÃÂ¶ÃÂ§Ãâ ", "ÃÂŽÃËÃÂ§Ãâ", "ÃÂ°ÃË ÃÂ§ÃâÃâÃÂ¹ÃÂ¯ÃÂ©", "ÃÂ°ÃË ÃÂ§ÃâÃÂ­ÃÂ¬ÃÂ©"}[res[1] - 1], Integer.toString(res[2])};
    }

    /* access modifiers changed from: package-private */
    public String[] chrToString(int y, int m, int d, int diff) {
        int[] iArr = new int[3];
        int[] res = islToChr(y, m, d, diff);
        return new String[]{Integer.toString(res[0]), new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}[res[1] - 1], Integer.toString(res[2])};
    }

    /* access modifiers changed from: package-private */
    public String getMonthName(int index) {
        return new String[]{"ÙØ§ÙÙÙ Ø§ÙØ«Ø§ÙÙ", "ØŽØšØ§Ø·", "Ø¢Ø°Ø§Ø±", "ÙÙØ³Ø§Ù", "Ø£ÙØ§Ø±", "Ø­Ø²ÙØ±Ø§Ù", "ØªÙÙØ²", "Ø¢Øš", "Ø£ÙÙÙÙ", "ØªØŽØ±ÙÙ Ø§ÙØ£ÙÙ", "ØªØŽØ±ÙÙ Ø§ÙØ«Ø§ÙÙ", "ÙØ§ÙÙÙ Ø§ÙØ£ÙÙ"}[index];
    }

    /* access modifiers changed from: package-private */
    public String getDayName(int index) {
        return new String[]{"Ø§ÙØ³ØšØª", "Ø§ÙØ£Ø­Ø¯", "Ø§ÙØ£Ø«ÙÙÙ", "Ø§ÙØ«ÙØ§Ø«Ø§Ø¡", "Ø§ÙØ£Ø±ØšØ¹Ø§Ø¡", "Ø§ÙØ®ÙÙØ³", "Ø§ÙØ¬ÙØ¹Ø©"}[index];
    }
}
