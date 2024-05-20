package com.vitcode.iprayertimes.dateconverter.abcd.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vitcode.iprayertimes.dateconverter.abcd.Fragments.JuzFragment;
import com.vitcode.iprayertimes.dateconverter.abcd.Fragments.SurahFragment;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the Fragment associated with the specified position
        switch (position) {
            case 0:
                return new SurahFragment(); // Replace OneFragment with your first fragment
            case 1:
                return new JuzFragment(); // Replace TwoFragment with your second fragment
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Return the number of tabs
        return 2; // Since we have two tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Return the title of the tab
        switch (position) {
            case 0:
                return "One";
            case 1:
                return "Two";
            default:
                return null;
        }
    }
}
