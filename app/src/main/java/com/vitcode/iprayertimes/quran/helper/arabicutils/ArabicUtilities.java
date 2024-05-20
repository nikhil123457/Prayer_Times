package com.vitcode.iprayertimes.quran.helper.arabicutils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;

public class ArabicUtilities {
    private static final String FONTS_LOCATION_PATH = "AnjaliOldLipi.ttf";
    static Typeface face;

    private static boolean isArabicCharacter(char c) {
        for (char[] cArr : ArabicListReshaper.ARABIC_GLPHIES) {
            if (cArr[0] == c) {
                return true;
            }
        }
        for (char c2 : ArabicListReshaper.HARAKATE) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private static String[] getWords(String str) {
        return str != null ? str.split("\\s") : new String[0];
    }

    public static boolean hasArabicLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isArabicCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArabicWord(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isArabicCharacter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String[] getWordsFromMixedWord(String str) {
        ArrayList arrayList = new ArrayList();
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (isArabicCharacter(str.charAt(i))) {
                if (str2.equals("") || isArabicWord(str2)) {
                    str2 = str2 + str.charAt(i);
                } else {
                    arrayList.add(str2);
                    str2 = "" + str.charAt(i);
                }
            } else if (str2.equals("") || !isArabicWord(str2)) {
                str2 = str2 + str.charAt(i);
            } else {
                arrayList.add(str2);
                str2 = "" + str.charAt(i);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String reshape(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String reshapeSentence : str.split("\n")) {
            stringBuffer.append(reshapeSentence(reshapeSentence));
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public static String reshapeSentence(String str) {
        String[] words = getWords(str);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < words.length; i++) {
            if (!hasArabicLetters(words[i])) {
                stringBuffer.append(words[i]);
            } else if (isArabicWord(words[i])) {
                stringBuffer.append(new ArabicListReshaper(words[i]).getReshapedWord());
            } else {
                for (String arabicListReshaper : getWordsFromMixedWord(words[i])) {
                    stringBuffer.append(new ArabicListReshaper(arabicListReshaper).getReshapedWord());
                }
            }
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    public static TextView getArabicEnabledTextView(Context context, TextView textView) {
        if (face == null) {
            face = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION_PATH);
        }
        textView.setTypeface(face);
        textView.setGravity(5);
        return textView;
    }
}
