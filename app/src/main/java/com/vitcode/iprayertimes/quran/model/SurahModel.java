package com.vitcode.iprayertimes.quran.model;

public class SurahModel {
    private String arabicAyah = "";
    private int bookMarkId = -1;
    private int juzzIndex = -1;
    private int paraIndex = 0;
    private String translation = "";
    private String transliteration = "";

    public SurahModel(int i, String str, String str2, String str3) {
        this.bookMarkId = i;
        this.arabicAyah = str;
        this.translation = str2;
        this.transliteration = str3;
    }

    public int getBookMarkId() {
        return this.bookMarkId;
    }

    public void setBookMarkId(int i) {
        this.bookMarkId = i;
    }

    public String getArabicAyah() {
        return this.arabicAyah;
    }

    public void setArabicAyah(String str) {
        this.arabicAyah = str;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String str) {
        this.translation = str;
    }

    public String getTransliteration() {
        return this.transliteration;
    }

    public void setTransliteration(String str) {
        this.transliteration = str;
    }

    public int getJuzzIndex() {
        return this.juzzIndex;
    }

    public void setJuzzIndex(int i) {
        this.juzzIndex = i;
    }

    public int getParaIndex() {
        return this.paraIndex;
    }

    public void setParaIndex(int i) {
        this.paraIndex = i;
    }
}
