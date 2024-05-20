package com.vitcode.iprayertimes.fivepillars.adapter;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vitcode.iprayertimes.fivepillars.ZakatFragment;


public class ZakatAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 8;
    Bundle bundle;
    Context context;
    ZakatFragment fragment;
    private String[] tabtitles = {"Tab1", "Tab2", "Tab3"};
    TextView tv;

    public ZakatAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getCount() {
        return 8;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "0");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 1:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "1");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 2:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "2");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 3:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "3");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 4:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "4");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 5:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "5");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 6:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "6");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            case 7:
                this.fragment = new ZakatFragment();
                this.bundle = new Bundle();
                this.bundle.putString("val", "7");
                this.fragment.setArguments(this.bundle);
                return this.fragment;
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        return this.tabtitles[position];
    }
}
