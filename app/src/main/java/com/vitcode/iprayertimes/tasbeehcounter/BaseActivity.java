package com.vitcode.iprayertimes.tasbeehcounter;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public void onCreate(Bundle bundle) {
        SharedPreferences sharedPreferences2 = getSharedPreferences("tasbih", 0);
        this.sharedPreferences = sharedPreferences2;
        this.editor = sharedPreferences2.edit();
        super.onCreate(bundle);
    }
}
