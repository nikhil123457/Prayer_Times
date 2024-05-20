package com.vitcode.iprayertimes.hadith;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;

public class Utils extends AppCompatActivity   {
    public Typeface tf;
    SharedPreferences pref;
    protected String ar_font = "1";
    protected String en_font = "1";
    protected String notify = "0";
    public int efont = 1;
    protected int afont = 1;
    public String pref_time = "0";

    public Typeface tf1;

    public Typeface tf2;
    protected Typeface tf3;
    public int[] style = {
            R.style.FontStyle_Small, R.style.FontStyle_Medium, R.style.FontStyle_Large};
    /* access modifiers changed from: protected */
    public int[] styleheader = {R.style.FontStyle_title_small, R.style.FontStyle_title_medium, R.style.FontStyle_title_large};
    /* access modifiers changed from: protected */
    public Typeface tf_arabic;

    public void font() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.ar_font = this.pref.getString("arabicfont", "1");
        this.en_font = this.pref.getString("englishfont", "1");
        this.notify = this.pref.getString("notification", "0");
        if (this.en_font.equals("0")) {
            this.efont = 0;
        } else if (this.en_font.equals("1")) {
            this.efont = 1;
        } else if (this.en_font.equals("2")) {
            this.efont = 2;
        }
        if (this.ar_font.equals("0")) {
            this.afont = 0;
        } else if (this.ar_font.equals("1")) {
            this.afont = 1;
        } else if (this.ar_font.equals("2")) {
            this.afont = 2;
        }
        this.pref_time = this.pref.getString("timeformat", "0");
    }
    public void fullscreen() {
        getWindow().setFlags(1024, 1024);
    }




}
