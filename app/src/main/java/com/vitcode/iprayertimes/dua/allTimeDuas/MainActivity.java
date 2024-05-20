package com.vitcode.iprayertimes.dua.allTimeDuas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.other_fragment);


    }
}
