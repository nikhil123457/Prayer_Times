package com.vitcode.iprayertimes.quran.helper;

import android.content.Context;
import android.widget.Toast;


import com.vitcode.iprayertimes.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.util.ArrayList;

public class QuranHelper {
    public static String[] arabicCounting = {"٥", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩", "١٠", "١١", "١٢", "١٣", "١٤", "١٥", "١٦", "١٧", "١٨", "١٩", "٢٠", "٢١", "٢٢", "٢٣", "٢٤", "٢٥", "٢٦", "٢٧", "٢٨", "٢٩", "٣٠", "٣١", "٣٢", "٣٣", "٣٤", "٣٥", "٣٦", "٣٧", "٣٨", "٣٩", "٤٠", "٤١", "٤٢", "٤٣", "٤٤", "٤٥", "٤٦", "٤٧", "٤٨", "٤٩", "٥٠", "٥١", "٥٢", "٥٣", "٥٤", "٥٥", "٥٦", "٥٧", "٥٨", "٥٩", "٦٠", "٦١", "٦٢", "٦٣", "٦٤", "٦٥", "٦٦", "٦٧", "٦٨", "٦٩", "٧٠", "٧١", "٧٢", "٧٣", "٧٤", "٧٥", "٧٦", "٧٧", "٧٨", "٧٩", "٨٠", "٨١", "٨٢", "٨٣", "٨٤", "٨٥", "٨٦", "٨٧", "٨٨", "٨٩", "٩٠", "٩١", "٩٢", "٩٣", "٩٤", "٩٥", "٩٦", "٩٧", "٩٨", "٩٩", "١٠٠", "١٠١", "١٠٢", "١٠٣", "١٠٤", "١٠٥", "١٠٦", "١٠٧", "١٠٨", "١٠٩", "١١٠", "١١١", "١١٢", "١١٣", "١١٤", "١١٥", "١١٦", "١١٧", "١١٨", "١١٩", "١٢٠", "١٢١", "١٢٢", "١٢٣", "١٢٤", "١٢٥", "١٢٦", "١٢٧", "١٢٨", "١٢٩", "١٣٠", "١٣١", "١٣٢", "١٣٣", "١٣٤", "١٣٥", "١٣٦", "١٣٧", "١٣٨", "١٣٩", "١٤٠", "١٤١", "١٤٢", "١٤٣", "١٤٤", "١٤٥", "١٤٦", "١٤٧", "١٤٨", "١٤٩", "١٥٠", "١٥١", "١٥٢", "١٥٣", "١٥٤", "١٥٥", "١٥٦", "١٥٧", "١٥٨", "١٥٩", "١٦٠", "١٦١", "١٦٢", "١٦٣", "١٦٤", "١٦٥", "١٦٦", "١٦٧", "١٦٨", "١٦٩", "١٧٠", "١٧١", "١٧٢", "١٧٣", "١٧٤", "١٧٥", "١٧٦", "١٧٧", "١٧٨", "١٧٩", "١٨٠", "١٨١", "١٨٢", "١٨٣", "١٨٤", "١٨٥", "١٨٦", "١٨٧", "١٨٨", "١٨٩", "١٩٠", "١٩١", "١٩٢", "١٩٣", "١٩٤", "١٩٥", "١٩٦", "١٩٧", "١٩٨", "١٩٩", "٢٠٠", "٢٠١", "٢٠٢", "٢٠٣", "٢٠٤", "٢٠٥", "٢٠٦", "٢٠٧", "٢٠٨", "٢٠٩", "٢١٠", "٢١١", "٢١٢", "٢١٣", "٢١٤", "٢١٥", "٢١٦", "٢١٧", "٢١٨", "٢١٩", "٢٢٠", "٢٢١", "٢٢٢", "٢٢٣", "٢٢٤", "٢٢٥", "٢٢٦", "٢٢٧", "٢٢٨", "٢٢٩", "٢٣٠", "٢٣١", "٢٣٢", "٢٣٣", "٢٣٤", "٢٣٥", "٢٣٦", "٢٣٧", "٢٣٨", "٢٣٩", "٢٤٠", "٢٤١", "٢٤٢", "٢٤٣", "٢٤٤", "٢٤٥", "٢٤٦", "٢٤٧", "٢٤٨", "٢٤٩", "٢٥٠", "٢٥١", "٢٥٢", "٢٥٣", "٢٥٤", "٢٥٥", "٢٥٦", "٢٥٧", "٢٥٨", "٢٥٩", "٢٦٠", "٢٦١", "٢٦٢", "٢٦٣", "٢٦٤", "٢٦٥", "٢٦٦", "٢٦٧", "٢٦٨", "٢٦٩", "٢٧٠", "٢٧١", "٢٧٢", "٢٧٣", "٢٧٤", "٢٧٥", "٢٧٦", "٢٧٧", "٢٧٨", "٢٧٩", "٢٨٠", "٢٨١", "٢٨٢", "٢٨٣", "٢٨٤", "٢٨٥", "٢٨٦"};
    public static int[] juzAyahNumber = {0, 142, 253, 92, 24, 148, 83, 111, 88, 41, 94, 6, 53, 2, 0, 75, 0, 0, 21, 60, 45, 31, 22, 32, 47, 0, 31, 0, 0, 0};
    public static int[] juzSurrahNumber = {1, 2, 2, 3, 4, 4, 5, 6, 7, 8, 9, 11, 12, 15, 17, 18, 21, 23, 25, 27, 29, 33, 36, 39, 41, 46, 51, 58, 67, 78};
    public static int[][] juzzIndex = {new int[]{1, 0, 1}, new int[]{2, 142, 2}, new int[]{2, 253, 3}, new int[]{3, 92, 4}, new int[]{4, 24, 5}, new int[]{4, 148, 6}, new int[]{5, 83, 7}, new int[]{6, 111, 8}, new int[]{7, 88, 9}, new int[]{8, 41, 10}, new int[]{9, 94, 11}, new int[]{11, 6, 12}, new int[]{12, 53, 13}, new int[]{15, 2, 14}, new int[]{17, 0, 15}, new int[]{18, 75, 16}, new int[]{21, 0, 17}, new int[]{23, 0, 18}, new int[]{25, 21, 19}, new int[]{27, 60, 20}, new int[]{29, 45, 21}, new int[]{33, 31, 22}, new int[]{36, 22, 23}, new int[]{39, 32, 24}, new int[]{41, 47, 25}, new int[]{46, 0, 26}, new int[]{51, 31, 27}, new int[]{58, 0, 28}, new int[]{67, 0, 29}, new int[]{78, 0, 30}};
    public static String[] paraWithSurrahIndex = {"1", "1,2,3", "3,4", "4,5,6", "6,7", "7,8", "8,9", "9,10", "10,11", "11", "11,12", "12,13", "13", "13 ", "13,14", "14", "15", "15,16", "16", "16", "17", "17", "18", "18", "18,19", "19", "19,20", "20", "20,21", "21", "21", "21", "21,22", "22", "22", "22,23", "23", "23", "23,24", "24", "24,25", "25", "25", "25", "25", "26", "26", "26", "26", "26", "26,27", "27", "27", "27", "27", "27", "27", "28", "28", "28", "28", "28", "28", "28", "28", "28", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"};

    public static void setLastRead(Context context, int i) {
        context.getSharedPreferences("QuranHelper", 0).edit().putInt("LAST_READ", i).apply();
    }

    public static int getLastRead(Context context) {
        return context.getSharedPreferences("QuranHelper", 0).getInt("LAST_READ", -1);
    }

    public static class Translation {
        private static final int LANGUAGE_Bangla = 17;
        private static final int LANGUAGE_Chinese = 10;
        private static final int LANGUAGE_Dutch = 13;
        private static final int LANGUAGE_English_Daryabadi = 5;
        private static final int LANGUAGE_English_Maududi = 4;
        private static final int LANGUAGE_English_Pickthal = 2;
        private static final int LANGUAGE_English_Saheeh = 1;
        private static final int LANGUAGE_English_Shakir = 3;
        private static final int LANGUAGE_English_YusufAli = 6;
        private static final int LANGUAGE_French = 9;
        private static final int LANGUAGE_Hindi = 16;
        private static final int LANGUAGE_Indonesian = 14;
        private static final int LANGUAGE_Italian = 12;
        private static final int LANGUAGE_Melayu = 15;
        private static final int LANGUAGE_Off = 0;
        private static final int LANGUAGE_Persian = 11;
        private static final int LANGUAGE_Spanish = 8;
        private static final int LANGUAGE_TURKISH = 18;
        private static final int LANGUAGE_Urdu = 7;

        public static void setTranslationIndex(Context context, int i) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putInt("translation_index", i).apply();
        }

        public static int getTranslationIndex(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getInt("translation_index", 0);
        }

        public static int getLastSaveTranslation(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getInt("LAST_TRANSALATION_POS", 0);
        }

        public static void setLastSaveTranslation(Context context, int i) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putInt("LAST_TRANSALATION_POS", i).apply();
        }

        public static void setTranslationPhoneTic(Context context, boolean z) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putBoolean("translation_phone_tic", z).apply();
        }

        public static boolean getTranslationPhoneTic(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getBoolean("translation_phone_tic", true);
        }

        public static boolean getReadModeState(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getBoolean("READ_MODE_STATE", false);
        }

        public static void setReadModeState(Context context, boolean z) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putBoolean("READ_MODE_STATE", z).apply();
        }

        public static void setRepeatSurah(Context context, boolean z) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putBoolean("REPEAT_SURAH", z).apply();
        }

        public static boolean isRepeatSurah(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getBoolean("REPEAT_SURAH", false);
        }

        public static void setReciter(Context context, int i) {
            context.getSharedPreferences(Translation.class.getSimpleName(), 0).edit().putInt("RECITER", i).apply();
        }

        public static int getReciter(Context context) {
            return context.getSharedPreferences(Translation.class.getSimpleName(), 0).getInt("RECITER", 1);
        }

        public static String getTranslationBismillahText(Context context) {
            switch (getTranslationIndex(context)) {
                case 0:
                    return context.getResources().getString(R.string.bismillahTextEngSaheeh);
                case 1:
                    return context.getResources().getString(R.string.bismillahTextEngSaheeh);
                case 2:
                    return context.getResources().getString(R.string.bismillahTextEngPickthal);
                case 3:
                    return context.getResources().getString(R.string.bismillahTextEngShakir);
                case 4:
                    return context.getResources().getString(R.string.bismillahTextEngMadudi);
                case 5:
                    return context.getResources().getString(R.string.bismillahTextEngDarayabadi);
                case 6:
                    return context.getResources().getString(R.string.bismillahTextEngYusaf);
                case 7:
                    return context.getResources().getString(R.string.bismillahTextUrdu);
                case 8:
                    return context.getResources().getString(R.string.bismillahTextSpanishCortes);
                case 9:
                    return context.getResources().getString(R.string.bismillahTextFrench);
                case 10:
                    return context.getResources().getString(R.string.bismillahTextChinese);
                case 11:
                    return context.getResources().getString(R.string.bismillahTextPersianGhommshei);
                case 12:
                    return context.getResources().getString(R.string.bismillahTextItalian);
                case 13:
                    return context.getResources().getString(R.string.bismillahTextDutchKeyzer);
                case 14:
                    return context.getResources().getString(R.string.bismillahTextIndonesianBahasha);
                case 15:
                    return context.getResources().getString(R.string.bismillahTextIndonesianBahasha);
                case 16:
                    return context.getResources().getString(R.string.bismillahTextHindi);
                case 17:
                    return context.getResources().getString(R.string.bismillahTextBengali);
                case 18:
                    return context.getResources().getString(R.string.bismillahTextTurkish);
                default:
                    return context.getResources().getString(R.string.bismillahTextEngSaheeh);
            }
        }

        public static XmlPullParser getTranslationXpp(Context context) {
            switch (getTranslationIndex(context)) {
                case 0:
                    return context.getResources().getXml(R.xml.english_translation);
                case 1:
                    return context.getResources().getXml(R.xml.english_translation);
                case 2:
                    return context.getResources().getXml(R.xml.eng_translation_pickthal);
                case 3:
                    return context.getResources().getXml(R.xml.eng_translation_shakir);
                case 4:
                    return context.getResources().getXml(R.xml.eng_translation_maududi);
                case 5:
                    return context.getResources().getXml(R.xml.eng_translation_daryabadi);
                case 6:
                    return context.getResources().getXml(R.xml.eng_translation_yusufali);
                case 7:
                    return context.getResources().getXml(R.xml.urdu_translation_jhalandhry);
                case 8:
                    return context.getResources().getXml(R.xml.spanish_cortes_trans);
                case 9:
                    return context.getResources().getXml(R.xml.french_trans);
                case 10:
                    return context.getResources().getXml(R.xml.chinese_trans);
                case 11:
                    return context.getResources().getXml(R.xml.persian_ghoomshei_trans);
                case 12:
                    return context.getResources().getXml(R.xml.italian_trans);
                case 13:
                    return context.getResources().getXml(R.xml.dutch_trans_keyzer);
                case 14:
                    return context.getResources().getXml(R.xml.indonesian_bhasha_trans);
                case 15:
                    return context.getResources().getXml(R.xml.malay_basmeih);
                case 16:
                    return context.getResources().getXml(R.xml.hindi_suhel_farooq_khan_and_saifur_rahman_nadwi);
                case 17:
                    return context.getResources().getXml(R.xml.bangali_zohurul_hoque);
                case 18:
                    return context.getResources().getXml(R.xml.turkish_diyanet_isleri);
                default:
                    return context.getResources().getXml(R.xml.english_translation);
            }
        }
    }

    public static class FileUtils {
        public static boolean checkAudioFileSize(Context context, File file, int i) {
            Toast.makeText(context, String.valueOf(file), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, String.valueOf(file.length()), Toast.LENGTH_SHORT).show();
            if (((double) file.length()) == Double.parseDouble(context.getResources().getStringArray(R.array.surah_sizes_alfasay)[i - 1])) {
                return true;
            }
            file.deleteOnExit();
            return false;
        }
    }

    public static class AudioTime {
        public static ArrayList<Integer> getTranslatedAyaList(Context context, XmlPullParser xmlPullParser, int i) throws XmlPullParserException {
            ArrayList<Integer> arrayList = new ArrayList<>();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                if (xmlPullParser.getEventType() == 1) {
                    break;
                }
                try {
                    if (xmlPullParser.getEventType() == 2) {
                        if (xmlPullParser.getName().equals("sura")) {
                            String attributeValue = xmlPullParser.getAttributeValue(0);
                            if (attributeValue.equals(i + "")) {
                                xmlPullParser.nextTag();
                                z = true;
                                z2 = false;
                            } else if (z) {
                                z2 = true;
                            }
                        }
                        if (z2) {
                            break;
                        } else if (xmlPullParser.getName().equals("aya") && z) {
                            arrayList.add(Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue(1))));
                        }
                    }
                    xmlPullParser.next();
                } catch (Exception unused) {
                }
            }
            return arrayList;
        }
    }

    public static class XmlParser {
        public static ArrayList<String> getTranslatedAyaList(Context context, XmlPullParser xmlPullParser, int i, String str) {
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                arrayList.add(str);
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (xmlPullParser.getEventType() == 1) {
                        break;
                    }
                    if (xmlPullParser.getEventType() == 2) {
                        if (xmlPullParser.getName().equals("sura")) {
                            String attributeValue = xmlPullParser.getAttributeValue(0);
                            if (attributeValue.equals(i + "")) {
                                xmlPullParser.nextTag();
                                z = true;
                                z2 = false;
                            } else if (z) {
                                z2 = true;
                            }
                        }
                        if (z2) {
                            break;
                        } else if (xmlPullParser.getName().equals("aya") && z) {
                            String attributeValue2 = xmlPullParser.getAttributeValue(1);
                            attributeValue2.trim();
                            arrayList.add(attributeValue2);
                        }
                    }
                    xmlPullParser.next();
                }
            } catch (Exception unused) {
            }
            return arrayList;
        }
    }
}
