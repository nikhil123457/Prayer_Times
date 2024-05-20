package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;

public class SunniFragment extends Fragment {


    LinearLayout LinSN01, LinSN02, LinSN03, LinSN04, LinSN05, LinSN06, LinSN07, LinSN08, LinSN09, LinSN010, LinSN011, LinSN012, LinSN013, LinSN014, LinSN015;

    TextView TXTSN01, TXTSN02, TXTSN03, TXTSN04, TXTSN05, TXTSN06, TXTSN07, TXTSN08, TXTSN09, TXTSN010, TXTSN011, TXTSN012, TXTSN013, TXTSN014, TXTSN015;

    ImageView ImgNO1, ImgNO2, ImgNO3, ImgNO4, ImgNO5, ImgNO6, ImgNO7, ImgNO8, ImgNO9, ImgNO10, ImgNO11, ImgNO12, ImgNO13, ImgNO14, ImgNO15;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sunni, container, false);


        LinSN01 = v.findViewById(R.id.LinSN01);
        LinSN02 = v.findViewById(R.id.LinSN02);
        LinSN03 = v.findViewById(R.id.LinSN03);
        LinSN04 = v.findViewById(R.id.LinSN04);
        LinSN05 = v.findViewById(R.id.LinSN05);
        LinSN06 = v.findViewById(R.id.LinSN06);
        LinSN07 = v.findViewById(R.id.LinSN07);
        LinSN08 = v.findViewById(R.id.LinSN08);
        LinSN09 = v.findViewById(R.id.LinSN09);
        LinSN010 = v.findViewById(R.id.LinSN010);
        LinSN011 = v.findViewById(R.id.LinSN011);
        LinSN012 = v.findViewById(R.id.LinSN012);
        LinSN013 = v.findViewById(R.id.LinSN013);
        LinSN014 = v.findViewById(R.id.LinSN014);
        LinSN015 = v.findViewById(R.id.LinSN015);

        TXTSN01 = v.findViewById(R.id.TXTSN01);
        TXTSN02 = v.findViewById(R.id.TXTSN02);
        TXTSN03 = v.findViewById(R.id.TXTSN03);
        TXTSN04 = v.findViewById(R.id.TXTSN04);
        TXTSN05 = v.findViewById(R.id.TXTSN05);
        TXTSN06 = v.findViewById(R.id.TXTSN06);
        TXTSN07 = v.findViewById(R.id.TXTSN07);
        TXTSN08 = v.findViewById(R.id.TXTSN08);
        TXTSN09 = v.findViewById(R.id.TXTSN09);
        TXTSN010 = v.findViewById(R.id.TXTSN010);
        TXTSN011 = v.findViewById(R.id.TXTSN011);
        TXTSN012 = v.findViewById(R.id.TXTSN012);
        TXTSN013 = v.findViewById(R.id.TXTSN013);
        TXTSN014 = v.findViewById(R.id.TXTSN014);
        TXTSN015 = v.findViewById(R.id.TXTSN015);
        ImgNO1 = v.findViewById(R.id.ImgNO1);
        ImgNO2 = v.findViewById(R.id.ImgNO2);
        ImgNO3 = v.findViewById(R.id.ImgNO3);
        ImgNO4 = v.findViewById(R.id.ImgNO4);
        ImgNO5 = v.findViewById(R.id.ImgNO5);
        ImgNO6 = v.findViewById(R.id.ImgNO6);
        ImgNO7 = v.findViewById(R.id.ImgNO7);
        ImgNO8 = v.findViewById(R.id.ImgNO8);
        ImgNO9 = v.findViewById(R.id.ImgNO9);
        ImgNO10 = v.findViewById(R.id.ImgNO10);
        ImgNO11 = v.findViewById(R.id.ImgNO11);
        ImgNO12 = v.findViewById(R.id.ImgNO12);
        ImgNO13 = v.findViewById(R.id.ImgNO13);
        ImgNO14 = v.findViewById(R.id.ImgNO14);
        ImgNO15 = v.findViewById(R.id.ImgNO15);

        LinSN01.setOnClickListener(view -> {toggleVisibility(TXTSN01,ImgNO1);});
        LinSN02.setOnClickListener(view -> toggleVisibility(TXTSN02,ImgNO2));
        LinSN03.setOnClickListener(view -> toggleVisibility(TXTSN03,ImgNO3));
        LinSN04.setOnClickListener(view -> toggleVisibility(TXTSN04,ImgNO4));
        LinSN05.setOnClickListener(view -> toggleVisibility(TXTSN05,ImgNO5));
        LinSN06.setOnClickListener(view -> toggleVisibility(TXTSN06,ImgNO6));
        LinSN07.setOnClickListener(view -> toggleVisibility(TXTSN07,ImgNO7));
        LinSN08.setOnClickListener(view -> toggleVisibility(TXTSN08,ImgNO8));
        LinSN09.setOnClickListener(view -> toggleVisibility(TXTSN09,ImgNO9));
        LinSN010.setOnClickListener(view -> toggleVisibility(TXTSN010,ImgNO10));
        LinSN011.setOnClickListener(view -> toggleVisibility(TXTSN011,ImgNO11));
        LinSN012.setOnClickListener(view -> toggleVisibility(TXTSN012,ImgNO12));
        LinSN013.setOnClickListener(view -> toggleVisibility(TXTSN013,ImgNO13));
        LinSN014.setOnClickListener(view -> toggleVisibility(TXTSN014,ImgNO14));
        LinSN015.setOnClickListener(view -> toggleVisibility(TXTSN015,ImgNO15));


        return v;
    }

    private TextView currentTextView = null;

    ImageView cuurentImageView = null;

    private void toggleVisibility(TextView textView, ImageView imageView) {
        // Check if the clicked TextView is already visible
        if (textView.getVisibility() == View.VISIBLE) {
            // If yes, hide it along with its corresponding ImageView
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.down); // Set the down arrow image
            // Reset the currentTextView to null as it's no longer visible
            currentTextView = null;
        } else {
            // If not, hide the previously visible TextView and ImageView, if any
            if (currentTextView != null) {
                currentTextView.setVisibility(View.GONE);
                cuurentImageView.setImageResource(R.drawable.down); // Set the down arrow image
            }
            // Show the clicked TextView and its corresponding ImageView
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            // Update the currentTextView to the clicked TextView
            currentTextView = textView;
            cuurentImageView = imageView;
            // Check if TXTSN01 is visible after toggling
            if (textView.getVisibility() == View.VISIBLE) {
                imageView.setImageResource(R.drawable.up); // Set the up arrow image
            } else {
                imageView.setImageResource(R.drawable.down); // Set the down arrow image
            }
        }
    }


}