package com.vitcode.iprayertimes.tasbeehcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.vitcode.iprayertimes.R;


public class setting extends AppCompatActivity {
    Intent intent;
    SharedPreferences sharedPreferences;
    SwitchCompat switchSound;
    SwitchCompat switchVibration;
    String tasbihName;
    ImageView bt_back;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        SharedPreferences sharedPreferences2 = getSharedPreferences("tasbih", 0);
        this.sharedPreferences = sharedPreferences2;
        final SharedPreferences.Editor edit = sharedPreferences2.edit();
        this.switchVibration = findViewById(R.id.switch_vibration);
        this.switchSound = findViewById(R.id.switch_sound);
        this.bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent2 = getIntent();
        this.intent = intent2;
        this.tasbihName = intent2.getStringExtra("tasbihName");
        int i = this.sharedPreferences.getInt("imgID", 1);
        this.switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                edit.putBoolean("sound", z);
                edit.commit();
            }
        });
        this.switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                edit.putBoolean("vib", z);
                edit.commit();
            }
        });

    }

    public void onResume() {
        super.onResume();
        this.switchSound.setChecked(this.sharedPreferences.getBoolean("sound", true));
        this.switchVibration.setChecked(this.sharedPreferences.getBoolean("vib", true));
    }

    public void onPause() {
        super.onPause();
    }

}
