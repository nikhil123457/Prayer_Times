package com.vitcode.iprayertimes.quran.model;

public class SajdahModel {
    private int ayahNo;
    private int id;
    private String surahName;
    private int surahNo;

    public SajdahModel(int i, String str, int i2, int i3) {
        this.id = i;
        this.surahName = str;
        this.surahNo = i2;
        this.ayahNo = i3;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getSurahName() {
        return this.surahName;
    }

    public void setSurahName(String str) {
        this.surahName = str;
    }

    public int getSurahNo() {
        return this.surahNo;
    }

    public void setSurahNo(int i) {
        this.surahNo = i;
    }

    public int getAyahNo() {
        return this.ayahNo;
    }

    public void setAyahNo(int i) {
        this.ayahNo = i;
    }
}
