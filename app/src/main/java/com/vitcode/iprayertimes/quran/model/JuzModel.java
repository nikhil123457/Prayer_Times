package com.vitcode.iprayertimes.quran.model;

public class JuzModel {
    private int ayatId;
    private int ayatNo;
    private int paraId;
    private int suratId;

    public int getAyatId() {
        return this.ayatId;
    }

    public void setAyatId(int i) {
        this.ayatId = i;
    }

    public int getSuratId() {
        return this.suratId;
    }

    public void setSuratId(int i) {
        this.suratId = i;
    }

    public int getParaId() {
        return this.paraId;
    }

    public void setParaId(int i) {
        this.paraId = i;
    }

    public int getAyatNo() {
        return this.ayatNo;
    }

    public void setAyatNo(int i) {
        this.ayatNo = i;
    }
}
