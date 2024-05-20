package com.vitcode.iprayertimes.sqliteasset;

import android.util.Log;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VersionComparator implements Comparator<String> {
    private static final String TAG = "SQLiteAssetHelper";
    private Pattern pattern = Pattern.compile(".*_upgrade_([0-9]+)-([0-9]+).*");

    VersionComparator() {
    }

    static {
        Class<SQLiteAssetHelper> cls = SQLiteAssetHelper.class;
    }

    public int compare(String str, String str2) {
        Matcher matcher = this.pattern.matcher(str);
        Matcher matcher2 = this.pattern.matcher(str2);
        if (!matcher.matches()) {
            String str3 = TAG;
            Log.w(str3, "could not parse upgrade script file: " + str);
            throw new SQLiteAssetHelper.SQLiteAssetException("Invalid upgrade script file");
        } else if (matcher2.matches()) {
            int intValue = Integer.valueOf(matcher.group(1)).intValue();
            int intValue2 = Integer.valueOf(matcher2.group(1)).intValue();
            int intValue3 = Integer.valueOf(matcher.group(2)).intValue();
            int intValue4 = Integer.valueOf(matcher2.group(2)).intValue();
            if (intValue == intValue2) {
                if (intValue3 == intValue4) {
                    return 0;
                }
                if (intValue3 < intValue4) {
                    return -1;
                }
                return 1;
            } else if (intValue < intValue2) {
                return -1;
            } else {
                return 1;
            }
        } else {
            String str4 = TAG;
            Log.w(str4, "could not parse upgrade script file: " + str2);
            throw new SQLiteAssetHelper.SQLiteAssetException("Invalid upgrade script file");
        }
    }
}
