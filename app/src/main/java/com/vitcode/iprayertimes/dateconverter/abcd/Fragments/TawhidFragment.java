package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

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


public class TawhidFragment extends Fragment {


    ImageView IT01, IT02, IT03;

    TextView TT01, TT02, TT03;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tawhid, container, false);


        IT01 = rootView.findViewById(R.id.IT01);
        TT01 = rootView.findViewById(R.id.TT01);

        IT01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TT01.getText().toString();
                shareText(textToShare);
            }
        });
        IT02 = rootView.findViewById(R.id.IT02);
        TT02 = rootView.findViewById(R.id.TT02);

        IT02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TT02.getText().toString();
                shareText(textToShare);
            }
        });
        IT03 = rootView.findViewById(R.id.IT03);
        TT03 = rootView.findViewById(R.id.TT03);

        IT03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = TT03.getText().toString();
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

