package com.vitcode.iprayertimes.fivepillars;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vitcode.iprayertimes.R;


public class ShahadahFragment extends Fragment {
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
        if (strtext.equals("0")) {
            title = getString(R.string.shahadah_title1);
            text = "<body><p>" + getString(R.string.shahadah_text1) + "</p></body>";
        } else if (strtext.equals("1")) {
            title = getString(R.string.shahadah_title2);
            text = "<body><p>" + getString(R.string.shahadah_text2) + "</p></body>";
        } else if (strtext.equals("2")) {
            title = getString(R.string.shahadah_title3);
            text = "<body><p>" + getString(R.string.shahadah_text3) + "</p></body>";
        } else if (strtext.equals("3")) {
            title = getString(R.string.shahadah_title4);
            text = "<body><p>" + getString(R.string.shahadah_text4) + "</p></body>";
        } else if (strtext.equals("4")) {
            title = getString(R.string.shahadah_title5);
            text = "<body><p>" + getString(R.string.shahadah_text5) + "</p></body>";
        } else if (strtext.equals("5")) {
            title = getString(R.string.shahadah_title6);
            text = "<body><p>" + getString(R.string.shahadah_text6) + "</p></body>";
        } else if (strtext.equals("6")) {
            title = getString(R.string.shahadah_title7);
            text = "<body><p>" + getString(R.string.shahadah_text7) + "</p></body>";
        }
        this.txt_title.setText(title);
        this.text1.setText(Html.fromHtml(text));
    }
}
