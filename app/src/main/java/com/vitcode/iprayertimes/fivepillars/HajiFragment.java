package com.vitcode.iprayertimes.fivepillars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vitcode.iprayertimes.R;


public class HajiFragment extends Fragment {
    TextView text1;
    TextView txt_title;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_shahadah_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String strtext = getArguments().getString("val");
        String text = "";
        String title = "";
        this.txt_title = getView().findViewById(R.id.txt_title);
        this.text1 = getView().findViewById(R.id.txt);
        this.text1.setTextIsSelectable(true);
        if (strtext.equals("0")) {
            title = getString(R.string.haji_title1);
            text = getString(R.string.haji_text1);
        } else if (strtext.equals("1")) {
            title = getString(R.string.haji_title2);
            text = getString(R.string.haji_text2);
        } else if (strtext.equals("2")) {
            title = getString(R.string.haji_title3);
            text = getString(R.string.haji_text3);
        } else if (strtext.equals("3")) {
            title = getString(R.string.haji_title4);
            text = getString(R.string.haji_text4);
        } else if (strtext.equals("4")) {
            title = getString(R.string.haji_title5);
            text = getString(R.string.haji_text5);
        } else if (strtext.equals("5")) {
            title = getString(R.string.haji_title6);
            text = getString(R.string.haji_text6);
        } else if (strtext.equals("6")) {
            title = getString(R.string.haji_title7);
            text = getString(R.string.haji_text7);
        } else if (strtext.equals("7")) {
            title = getString(R.string.haji_title8);
            text = getString(R.string.haji_text8);
        } else if (strtext.equals("8")) {
            title = getString(R.string.haji_title9);
            text = getString(R.string.haji_text9);
        } else if (strtext.equals("9")) {
            title = getString(R.string.haji_title10);
            text = getString(R.string.haji_text10);
        }
        this.txt_title.setText(title);
        this.text1.setText(text);
    }
}
