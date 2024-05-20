package com.vitcode.iprayertimes.fivepillars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.hadith.Utils;


public class FivePillarsActivity extends Utils {
    LinearLayout fasting;
    LinearLayout hajj;
    LinearLayout salah;
    LinearLayout shahadah;
    LinearLayout zakat;
    TextView title;
    ImageView back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivepillars);
        this.shahadah = findViewById(R.id.Shahadah);
        this.title = findViewById(R.id.tvtitle);
        title.setText(getString(R.string.lbl_pillar_title));
        this.back = findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.salah = findViewById(R.id.Salah);
        this.zakat = findViewById(R.id.Zakat);
        this.fasting = findViewById(R.id.Fasting);
        this.hajj = findViewById(R.id.Hajj);
        this.shahadah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FivePillarsActivity.this.startActivity(new Intent(FivePillarsActivity.this, PillarsListActivity.class).putExtra("name", "shahadah"));
            }
        });
        this.salah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FivePillarsActivity.this.startActivity(new Intent(FivePillarsActivity.this, PillarsListActivity.class).putExtra("name", "salah"));
            }
        });
        this.zakat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FivePillarsActivity.this.startActivity(new Intent(FivePillarsActivity.this, PillarsListActivity.class).putExtra("name", "zakat"));
            }
        });
        this.fasting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FivePillarsActivity.this.startActivity(new Intent(FivePillarsActivity.this, PillarsListActivity.class).putExtra("name", "fasting"));
            }
        });
        this.hajj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FivePillarsActivity.this.startActivity(new Intent(FivePillarsActivity.this, PillarsListActivity.class).putExtra("name", "haji"));
            }
        });
    }


}
