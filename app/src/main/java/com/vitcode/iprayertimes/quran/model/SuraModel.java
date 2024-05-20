package com.vitcode.iprayertimes.quran.model;

public class SuraModel {
    private String arabicSurahName;
    private String engSurahName;
    private int itemPosition;
    private String paraIndex = "";
    private String placeOfRevelation;
    private int surahNo;
    private int totalVerses;

    public SuraModel(int i, String str, String str2, String str3, int i2, int i3) {
        this.surahNo = i;
        this.engSurahName = str;
        this.arabicSurahName = str2;
        this.placeOfRevelation = str3;
        this.totalVerses = i2;
        this.itemPosition = i3;
    }

    public int getSurahNo() {
        return this.surahNo;
    }

    public void setSurahNo(int i) {
        this.surahNo = i;
    }

    public int getTotalVerses() {
        return this.totalVerses;
    }

    public void setTotalVerses(int i) {
        this.totalVerses = i;
    }

    public String getEngSurahName() {
        return this.engSurahName;
    }

    public void setEngSurahName(String str) {
        this.engSurahName = str;
    }

    public String getArabicSurahName() {
        return this.arabicSurahName;
    }

    public void setArabicSurahName(String str) {
        this.arabicSurahName = str;
    }

    public String getPlaceOfRevelation() {
        return this.placeOfRevelation;
    }

    public void setPlaceOfRevelation(String str) {
        this.placeOfRevelation = str;
    }

    public int getItemPosition() {
        return this.itemPosition;
    }

    public void setItemPosition(int i) {
        this.itemPosition = i;
    }

    public String getParaIndex() {
        return this.paraIndex;
    }

    public void setParaIndex(String str) {
        this.paraIndex = str;
    }
}
