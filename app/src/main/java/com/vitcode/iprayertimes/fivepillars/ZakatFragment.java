package com.vitcode.iprayertimes.fivepillars;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vitcode.iprayertimes.R;

@TargetApi(11)
public class ZakatFragment extends Fragment {
    TextView txt_text;
    TextView txt_title;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(    R.layout.activity_shahadah_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String strtext = getArguments().getString("val");
        String text = "";
        String title = "";
        this.txt_title = (TextView) getView().findViewById(R.id.txt_title);
        this.txt_text = (TextView) getView().findViewById(R.id.txt);
        this.txt_text.setTextIsSelectable(true);
        if (strtext.equals("0")) {
            title = getString(R.string.zakat_title1);
            text = getString(R.string.zakat_text1);
        } else if (strtext.equals("1")) {
            title = getString(R.string.zakat_title2);
            text = getString(R.string.zakat_text2);
        } else if (strtext.equals("2")) {
            title = getString(R.string.zakat_title3);
            text = getString(R.string.zakat_text3);
        } else if (strtext.equals("3")) {
            title = getString(R.string.zakat_title4);
            text = getString(R.string.zakat_text4);
        } else if (strtext.equals("4")) {
            title = getString(R.string.zakat_title5);
            text = getString(R.string.zakat_text5);
        } else if (strtext.equals("5")) {
            title = getString(R.string.zakat_title6);
            text = getString(R.string.zakat_text6);
        } else if (strtext.equals("6")) {
            title = getString(R.string.zakat_title7);
            text = getString(R.string.zakat_text7);
        } else if (strtext.equals("7")) {
            title = getString(R.string.zakat_title8);
            text = getString(R.string.zakat_text8);
        }
        this.txt_title.setText(title);
        this.txt_text.setText(text);
    }
}
