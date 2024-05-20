package com.vitcode.iprayertimes.abcd.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;

public class ShiaFragment extends Fragment {
    LinearLayout LinSU01, LinSU02, LinSU03, LinSU04, LinSU05, LinSU06, LinSU07, LinSU08, LinSU09, LinSU010, LinSU011, LinSU012, LinSU013, LinSU014, LinSU015;

    TextView TXTSU01, TXTSU02, TXTSU03, TXTSU04, TXTSU05, TXTSU06, TXTSU07, TXTSU08, TXTSU09, TXTSU010, TXTSU011, TXTSU012, TXTSU013, TXTSU014, TXTSU015;

    ImageView ImgNs1, ImgNs2, ImgNs3, ImgNs4, ImgNs5, ImgNs6, ImgNs7, ImgNs8, ImgNs9, ImgNs10, ImgNs11, ImgNs12, ImgNs13, ImgNs14, ImgNs15;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shia, container, false);

        LinSU01 = v.findViewById(R.id.LinSU01);
        LinSU02 = v.findViewById(R.id.LinSU02);
        LinSU03 = v.findViewById(R.id.LinSU03);
        LinSU04 = v.findViewById(R.id.LinSU04);
        LinSU05 = v.findViewById(R.id.LinSU05);
        LinSU06 = v.findViewById(R.id.LinSU06);
        LinSU07 = v.findViewById(R.id.LinSU07);
        LinSU08 = v.findViewById(R.id.LinSU08);
        LinSU09 = v.findViewById(R.id.LinSU09);
        LinSU010 = v.findViewById(R.id.LinSU010);
        LinSU011 = v.findViewById(R.id.LinSU011);
        LinSU012 = v.findViewById(R.id.LinSU012);
        LinSU013 = v.findViewById(R.id.LinSU013);
        LinSU014 = v.findViewById(R.id.LinSU014);
        LinSU015 = v.findViewById(R.id.LinSU015);

        TXTSU01 = v.findViewById(R.id.TXTSU01);
        TXTSU02 = v.findViewById(R.id.TXTSU02);
        TXTSU03 = v.findViewById(R.id.TXTSU03);
        TXTSU04 = v.findViewById(R.id.TXTSU04);
        TXTSU05 = v.findViewById(R.id.TXTSU05);
        TXTSU06 = v.findViewById(R.id.TXTSU06);
        TXTSU07 = v.findViewById(R.id.TXTSU07);
        TXTSU08 = v.findViewById(R.id.TXTSU08);
        TXTSU09 = v.findViewById(R.id.TXTSU09);
        TXTSU010 = v.findViewById(R.id.TXTSU010);
        TXTSU011 = v.findViewById(R.id.TXTSU011);
        TXTSU012 = v.findViewById(R.id.TXTSU012);
        TXTSU013 = v.findViewById(R.id.TXTSU013);
        TXTSU014 = v.findViewById(R.id.TXTSU014);
        TXTSU015 = v.findViewById(R.id.TXTSU015);

        ImgNs1 = v.findViewById(R.id.ImgNs1);
        ImgNs2 = v.findViewById(R.id.ImgNs2);
        ImgNs3 = v.findViewById(R.id.ImgNs3);
        ImgNs4 = v.findViewById(R.id.ImgNs4);
        ImgNs5 = v.findViewById(R.id.ImgNs5);
        ImgNs6 = v.findViewById(R.id.ImgNs6);
        ImgNs7 = v.findViewById(R.id.ImgNs7);
        ImgNs8 = v.findViewById(R.id.ImgNs8);
        ImgNs9 = v.findViewById(R.id.ImgNs9);
        ImgNs10 = v.findViewById(R.id.ImgNs10);
        ImgNs11 = v.findViewById(R.id.ImgNs11);
        ImgNs12 = v.findViewById(R.id.ImgNs12);
        ImgNs13 = v.findViewById(R.id.ImgNs13);
        ImgNs14 = v.findViewById(R.id.ImgNs14);
        ImgNs15 = v.findViewById(R.id.ImgNs15);


        LinSU01.setOnClickListener(view -> toggleVisibility(TXTSU01,ImgNs1));
        LinSU02.setOnClickListener(view -> toggleVisibility(TXTSU02,ImgNs2));
        LinSU03.setOnClickListener(view -> toggleVisibility(TXTSU03,ImgNs3));
        LinSU04.setOnClickListener(view -> toggleVisibility(TXTSU04,ImgNs4));
        LinSU05.setOnClickListener(view -> toggleVisibility(TXTSU05,ImgNs5));
        LinSU06.setOnClickListener(view -> toggleVisibility(TXTSU06,ImgNs6));
        LinSU07.setOnClickListener(view -> toggleVisibility(TXTSU07,ImgNs7));
        LinSU08.setOnClickListener(view -> toggleVisibility(TXTSU08,ImgNs8));
        LinSU09.setOnClickListener(view -> toggleVisibility(TXTSU09,ImgNs9));
        LinSU010.setOnClickListener(view -> toggleVisibility(TXTSU010,ImgNs10));
        LinSU011.setOnClickListener(view -> toggleVisibility(TXTSU011,ImgNs11));
        LinSU012.setOnClickListener(view -> toggleVisibility(TXTSU012,ImgNs12));
        LinSU013.setOnClickListener(view -> toggleVisibility(TXTSU013,ImgNs13));
        LinSU014.setOnClickListener(view -> toggleVisibility(TXTSU014,ImgNs14));
        LinSU015.setOnClickListener(view -> toggleVisibility(TXTSU015,ImgNs15));



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