package com.vitcode.iprayertimes.abcd;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.abcd.morefrag.ForgivenessFragment;
import com.vitcode.iprayertimes.abcd.morefrag.Azan1Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.Dhikr9Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.Food2Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.HouseFragment;
import com.vitcode.iprayertimes.abcd.morefrag.Dress4Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.Prayer7Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.Travel3Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.protection5Fragment;
import com.vitcode.iprayertimes.abcd.morefrag.family10Fragment;

import java.util.ArrayList;
import java.util.List;

public class MoreduvaActivity extends AppCompatActivity {

    ViewPager viewPager;

    TabLayout tabLayout;

    ImageView bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreduva);

        viewPager = findViewById(R.id.MDviewPager);
        tabLayout = findViewById(R.id.MDtabLayout);
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(v->{
            onBackPressed();
        });

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }



    private void setupViewPager(ViewPager viewPager) {
        MoreduvaActivity.ViewPagerAdapter2 adapter = new MoreduvaActivity.ViewPagerAdapter2(getSupportFragmentManager());
        adapter.addFragment(new Azan1Fragment(), "Azan Azkar");
        adapter.addFragment(new Food2Fragment(), "Food Dhikr");
        adapter.addFragment(new Travel3Fragment(), "Travel Dhikr");
        adapter.addFragment(new Dress4Fragment(), "Dressing Dhikr");
        adapter.addFragment(new protection5Fragment(), "protection Dhikr");
        adapter.addFragment(new ForgivenessFragment(), "Forgiveness Dhikr");
        adapter.addFragment(new Prayer7Fragment(), "Prayer Dhikr");
        adapter.addFragment(new HouseFragment(), "House Dhikr");
        adapter.addFragment(new Dhikr9Fragment(), "hajj");
        adapter.addFragment(new family10Fragment(), "Family Dhikr");
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