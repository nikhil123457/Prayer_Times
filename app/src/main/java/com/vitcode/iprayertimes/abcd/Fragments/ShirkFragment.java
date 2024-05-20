package com.vitcode.iprayertimes.abcd.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;


public class ShirkFragment extends Fragment {
    TextView TS01, TS02, TS03, TS04, TS05;

    ImageView IS01, IS02, IS03, IS04, IS05;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shirk, container, false);


        IS01 = rootView.findViewById(R.id.IS01);
        IS02 = rootView.findViewById(R.id.IS02);
        IS03 = rootView.findViewById(R.id.IS03);
        IS04 = rootView.findViewById(R.id.IS04);
        IS05 = rootView.findViewById(R.id.IS05);
        TS01 = rootView.findViewById(R.id.TS01);
        TS02 = rootView.findViewById(R.id.TS02);
        TS03 = rootView.findViewById(R.id.TS03);
        TS04 = rootView.findViewById(R.id.TS04);
        TS05 = rootView.findViewById(R.id.TS05);


        IS01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TS01.getText().toString();
                shareText(textToShare);
            }
        });
        IS02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TS02.getText().toString();
                shareText(textToShare);
            }
        });
        IS03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TS03.getText().toString();
                shareText(textToShare);
            }
        });
        IS04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TS04.getText().toString();
                shareText(textToShare);
            }
        });
        IS05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TS05.getText().toString();
                shareText(textToShare);
            }
        });
        return rootView;
    }

    private void shareText(String textToShare) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(intent, "Share Text"));
    }
}