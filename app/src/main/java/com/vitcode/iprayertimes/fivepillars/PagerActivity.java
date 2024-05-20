package com.vitcode.iprayertimes.fivepillars;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.fivepillars.adapter.FastingAdapter;
import com.vitcode.iprayertimes.fivepillars.adapter.HajiAdapter;
import com.vitcode.iprayertimes.fivepillars.adapter.SalahAdapter;
import com.vitcode.iprayertimes.fivepillars.adapter.ShahadahAdapter;
import com.vitcode.iprayertimes.fivepillars.adapter.ZakatAdapter;
import com.vitcode.iprayertimes.hadith.Utils;


public class PagerActivity extends Utils {
    TextView title;
    ImageView back;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("ga", 0);
        String type = extra.getString("type");
        this.title = findViewById(R.id.tvtitle);
        this.back = findViewById(R.id.bt_back);
        back.setOnClickListener(view -> onBackPressed());
        title.setText(type);

        ViewPager viewPager = findViewById(R.id.pager);
        if (type.equals("Salah")) {
            viewPager.setAdapter(new SalahAdapter(getSupportFragmentManager()));
        } else if (type.equals("Fasting")) {
            viewPager.setAdapter(new FastingAdapter(getSupportFragmentManager()));
        } else if (type.equals("Shahadah")) {
            viewPager.setAdapter(new ShahadahAdapter(getSupportFragmentManager()));
        } else if (type.equals("Zakat")) {
            viewPager.setAdapter(new ZakatAdapter(getSupportFragmentManager()));
        } else if (type.equals("Haji")) {
            viewPager.setAdapter(new HajiAdapter(getSupportFragmentManager()));
        }
        viewPager.setCurrentItem(id);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
