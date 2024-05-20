package com.vitcode.iprayertimes.dateconverter.abcd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.dateconverter.abcd.Fragments.FemalePGFragment;
import com.vitcode.iprayertimes.dateconverter.abcd.Fragments.MalePGFragment;

import java.util.ArrayList;
import java.util.List;

public class PrayerGuideActivity extends AppCompatActivity {

    ImageView bt_back;

    ViewPager viewPager;

    TabLayout tabLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_guide);

        viewPager = findViewById(R.id.PGviewPager);
        setupViewPager(viewPager);

        // TabLayout ke saath ViewPager ko synchronize karna
        tabLayout = findViewById(R.id.PGtabLayout);
        tabLayout.setupWithViewPager(viewPager);

        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        PrayerGuideActivity.ViewPagerAdapter2 adapter = new PrayerGuideActivity.ViewPagerAdapter2(getSupportFragmentManager());
        adapter.addFragment(new MalePGFragment(), "Male");
        adapter.addFragment(new FemalePGFragment(), "Female");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter2 extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter2(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }
    }
}