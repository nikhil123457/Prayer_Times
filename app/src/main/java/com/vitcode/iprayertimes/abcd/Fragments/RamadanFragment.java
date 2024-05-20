package com.vitcode.iprayertimes.abcd.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.abcd.Duuass_open;


public class RamadanFragment extends Fragment {

    LinearLayout RD0A1,
    RD0A2,
            RD0A3,
    RD0A4,
            RD0A5,
    RD0A6;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ramadan, container, false);

        RD0A1=v.findViewById(R.id.LinRD0A1);
                RD0A2=v.findViewById(R.id.LinRD0A2);
        RD0A3=v.findViewById(R.id.LinRD0A3);
                RD0A4=v.findViewById(R.id.LinRD0A4);
        RD0A5=v.findViewById(R.id.LinRD0A5);
                RD0A6=v.findViewById(R.id.LinRD0A6);


        RD0A1.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A1);
            String text1 = getString(R.string.RD0B1);
            String text2 = getString(R.string.RD0C1);
            String text3 = getString(R.string.RD0D1);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        RD0A2.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A2);
            String text1 = getString(R.string.RD0B2);
            String text2 = getString(R.string.RD0C2);
            String text3 = getString(R.string.RD0D2);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        RD0A3.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A3);
            String text1 = getString(R.string.RD0B3);
            String text2 = getString(R.string.RD0C3);
            String text3 = getString(R.string.RD0D3);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        RD0A4.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A4);
            String text1 = getString(R.string.RD0B4);
            String text2 = getString(R.string.RD0C4);
            String text3 = getString(R.string.RD0D4);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        RD0A5.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A5);
            String text1 = getString(R.string.RD0B5);
            String text2 = getString(R.string.RD0C5);
            String text3 = getString(R.string.RD0D5);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        RD0A6.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.RD0A6);
            String text1 = getString(R.string.RD0B6);
            String text2 = getString(R.string.RD0C6);
            String text3 = getString(R.string.RD0D6);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });

        return v;
    }
}