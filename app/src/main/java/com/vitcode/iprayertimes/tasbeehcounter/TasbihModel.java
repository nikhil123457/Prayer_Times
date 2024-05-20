package com.vitcode.iprayertimes.tasbeehcounter;

public class TasbihModel {
    int tasbihCount;
    int tasbihMax;
    String tasbihName;
    int tasbihSet;

    public String getTasbihName() {
        return this.tasbihName;
    }

    public void setTasbihName(String str) {
        this.tasbihName = str;
    }

    public int getTasbihCount() {
        return this.tasbihCount;
    }

    public void setTasbihCount(int i) {
        this.tasbihCount = i;
    }

    public int getTasbihSet() {
        return this.tasbihSet;
    }

    public void setTasbihSet(int i) {
        this.tasbihSet = i;
    }

    public int getTasbihMax() {
        return this.tasbihMax;
    }

    public void setTasbihMax(int i) {
        this.tasbihMax = i;
    }
}
