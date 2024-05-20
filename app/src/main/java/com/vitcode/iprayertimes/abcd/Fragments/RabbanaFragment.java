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


public class RabbanaFragment extends Fragment {


    LinearLayout LinRabbRb0A01, LinRabbRb0A02, LinRabbRb0A03, LinRabbRb0A04, LinRabbRb0A05, LinRabbRb0A06, LinRabbRb0A07, LinRabbRb0A08, LinRabbRb0A09, LinRabbRb0A010, LinRabbRb0A011, LinRabbRb0A012, LinRabbRb0A013, LinRabbRb0A014, LinRabbRb0A015, LinRabbRb0A016, LinRabbRb0A017, LinRabbRb0A018, LinRabbRb0A019, LinRabbRb0A020, LinRabbRb0A021, LinRabbRb0A022, LinRabbRb0A023, LinRabbRb0A024, LinRabbRb0A025, LinRabbRb0A026, LinRabbRb0A027, LinRabbRb0A028, LinRabbRb0A029, LinRabbRb0A030, LinRabbRb0A031, LinRabbRb0A032, LinRabbRb0A033, LinRabbRb0A034, LinRabbRb0A035, LinRabbRb0A036, LinRabbRb0A037, LinRabbRb0A038, LinRabbRb0A039, LinRabbRb0A040;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rabbana, container, false);


        LinRabbRb0A01 = v.findViewById(R.id.LinRabbRb0A01);
        LinRabbRb0A02 = v.findViewById(R.id.LinRabbRb0A02);
        LinRabbRb0A03 = v.findViewById(R.id.LinRabbRb0A03);
        LinRabbRb0A04 = v.findViewById(R.id.LinRabbRb0A04);
        LinRabbRb0A05 = v.findViewById(R.id.LinRabbRb0A05);
        LinRabbRb0A06 = v.findViewById(R.id.LinRabbRb0A06);
        LinRabbRb0A07 = v.findViewById(R.id.LinRabbRb0A07);
        LinRabbRb0A08 = v.findViewById(R.id.LinRabbRb0A08);
        LinRabbRb0A09 = v.findViewById(R.id.LinRabbRb0A09);
        LinRabbRb0A010 = v.findViewById(R.id.LinRabbRb0A010);
        LinRabbRb0A011 = v.findViewById(R.id.LinRabbRb0A011);
        LinRabbRb0A012 = v.findViewById(R.id.LinRabbRb0A012);
        LinRabbRb0A013 = v.findViewById(R.id.LinRabbRb0A013);
        LinRabbRb0A014 = v.findViewById(R.id.LinRabbRb0A014);
        LinRabbRb0A015 = v.findViewById(R.id.LinRabbRb0A015);
        LinRabbRb0A016 = v.findViewById(R.id.LinRabbRb0A016);
        LinRabbRb0A017 = v.findViewById(R.id.LinRabbRb0A017);
        LinRabbRb0A018 = v.findViewById(R.id.LinRabbRb0A018);
        LinRabbRb0A019 = v.findViewById(R.id.LinRabbRb0A019);
        LinRabbRb0A020 = v.findViewById(R.id.LinRabbRb0A020);
        LinRabbRb0A021 = v.findViewById(R.id.LinRabbRb0A021);
        LinRabbRb0A022 = v.findViewById(R.id.LinRabbRb0A022);
        LinRabbRb0A023 = v.findViewById(R.id.LinRabbRb0A023);
        LinRabbRb0A024 = v.findViewById(R.id.LinRabbRb0A024);
        LinRabbRb0A025 = v.findViewById(R.id.LinRabbRb0A025);
        LinRabbRb0A026 = v.findViewById(R.id.LinRabbRb0A026);
        LinRabbRb0A027 = v.findViewById(R.id.LinRabbRb0A027);
        LinRabbRb0A028 = v.findViewById(R.id.LinRabbRb0A028);
        LinRabbRb0A029 = v.findViewById(R.id.LinRabbRb0A029);
        LinRabbRb0A030 = v.findViewById(R.id.LinRabbRb0A030);
        LinRabbRb0A031 = v.findViewById(R.id.LinRabbRb0A031);
        LinRabbRb0A032 = v.findViewById(R.id.LinRabbRb0A032);
        LinRabbRb0A033 = v.findViewById(R.id.LinRabbRb0A033);
        LinRabbRb0A034 = v.findViewById(R.id.LinRabbRb0A034);
        LinRabbRb0A035 = v.findViewById(R.id.LinRabbRb0A035);
        LinRabbRb0A036 = v.findViewById(R.id.LinRabbRb0A036);
        LinRabbRb0A037 = v.findViewById(R.id.LinRabbRb0A037);
        LinRabbRb0A038 = v.findViewById(R.id.LinRabbRb0A038);
        LinRabbRb0A039 = v.findViewById(R.id.LinRabbRb0A039);
        LinRabbRb0A040 = v.findViewById(R.id.LinRabbRb0A040);




        LinRabbRb0A01.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A01);
            String text1 = getString(R.string.Rb0A1);
            String text2 = getString(R.string.Rb0B1);
            String text3 = getString(R.string.Rb0C1);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A02.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A02);
            String text1 = getString(R.string.Rb0A2);
            String text2 = getString(R.string.Rb0B2);
            String text3 = getString(R.string.Rb0C2);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A03.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A03);
            String text1 = getString(R.string.Rb0A3);
            String text2 = getString(R.string.Rb0B3);
            String text3 = getString(R.string.Rb0C3);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A04.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A04);
            String text1 = getString(R.string.Rb0A4);
            String text2 = getString(R.string.Rb0B4);
            String text3 = getString(R.string.Rb0C4);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A05.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A05);
            String text1 = getString(R.string.Rb0A5);
            String text2 = getString(R.string.Rb0B5);
            String text3 = getString(R.string.Rb0C5);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A06.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A06);
            String text1 = getString(R.string.Rb0A6);
            String text2 = getString(R.string.Rb0B6);
            String text3 = getString(R.string.Rb0C6);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A07.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A07);
            String text1 = getString(R.string.Rb0A7);
            String text2 = getString(R.string.Rb0B7);
            String text3 = getString(R.string.Rb0C7);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A08.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A08);
            String text1 = getString(R.string.Rb0A8);
            String text2 = getString(R.string.Rb0B8);
            String text3 = getString(R.string.Rb0C8);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A09.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A09);
            String text1 = getString(R.string.Rb0A9);
            String text2 = getString(R.string.Rb0B9);
            String text3 = getString(R.string.Rb0C9);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A010.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A010);
            String text1 = getString(R.string.Rb0A10);
            String text2 = getString(R.string.Rb0B10);
            String text3 = getString(R.string.Rb0C10);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A011.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A011);
            String text1 = getString(R.string.Rb0A11);
            String text2 = getString(R.string.Rb0B11);
            String text3 = getString(R.string.Rb0C11);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A012.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A012);
            String text1 = getString(R.string.Rb0A12);
            String text2 = getString(R.string.Rb0B12);
            String text3 = getString(R.string.Rb0C12);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A013.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A013);
            String text1 = getString(R.string.Rb0A13);
            String text2 = getString(R.string.Rb0B13);
            String text3 = getString(R.string.Rb0C13);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A014.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A014);
            String text1 = getString(R.string.Rb0A14);
            String text2 = getString(R.string.Rb0B14);
            String text3 = getString(R.string.Rb0C14);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A015.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A015);
            String text1 = getString(R.string.Rb0A15);
            String text2 = getString(R.string.Rb0B15);
            String text3 = getString(R.string.Rb0C15);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A016.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A016);
            String text1 = getString(R.string.Rb0A16);
            String text2 = getString(R.string.Rb0B16);
            String text3 = getString(R.string.Rb0C16);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A017.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A017);
            String text1 = getString(R.string.Rb0A17);
            String text2 = getString(R.string.Rb0B17);
            String text3 = getString(R.string.Rb0C17);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A018.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A018);
            String text1 = getString(R.string.Rb0A18);
            String text2 = getString(R.string.Rb0B18);
            String text3 = getString(R.string.Rb0C18);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A019.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A019);
            String text1 = getString(R.string.Rb0A19);
            String text2 = getString(R.string.Rb0B19);
            String text3 = getString(R.string.Rb0C19);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A020.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A020);
            String text1 = getString(R.string.Rb0A20);
            String text2 = getString(R.string.Rb0B20);
            String text3 = getString(R.string.Rb0C20);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A021.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A021);
            String text1 = getString(R.string.Rb0A21);
            String text2 = getString(R.string.Rb0B21);
            String text3 = getString(R.string.Rb0C21);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A022.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A022);
            String text1 = getString(R.string.Rb0A22);
            String text2 = getString(R.string.Rb0B22);
            String text3 = getString(R.string.Rb0C22);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A023.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A023);
            String text1 = getString(R.string.Rb0A23);
            String text2 = getString(R.string.Rb0B23);
            String text3 = getString(R.string.Rb0C23);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A024.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A024);
            String text1 = getString(R.string.Rb0A24);
            String text2 = getString(R.string.Rb0B24);
            String text3 = getString(R.string.Rb0C24);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A025.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A025);
            String text1 = getString(R.string.Rb0A25);
            String text2 = getString(R.string.Rb0B25);
            String text3 = getString(R.string.Rb0C25);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A026.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A026);
            String text1 = getString(R.string.Rb0A26);
            String text2 = getString(R.string.Rb0B26);
            String text3 = getString(R.string.Rb0C26);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A027.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A027);
            String text1 = getString(R.string.Rb0A27);
            String text2 = getString(R.string.Rb0B27);
            String text3 = getString(R.string.Rb0C27);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A028.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A028);
            String text1 = getString(R.string.Rb0A28);
            String text2 = getString(R.string.Rb0B28);
            String text3 = getString(R.string.Rb0C28);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A029.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A029);
            String text1 = getString(R.string.Rb0A29);
            String text2 = getString(R.string.Rb0B29);
            String text3 = getString(R.string.Rb0C29);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A030.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A030);
            String text1 = getString(R.string.Rb0A30);
            String text2 = getString(R.string.Rb0B30);
            String text3 = getString(R.string.Rb0C30);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A031.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A031);
            String text1 = getString(R.string.Rb0A31);
            String text2 = getString(R.string.Rb0B31);
            String text3 = getString(R.string.Rb0C31);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A032.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A032);
            String text1 = getString(R.string.Rb0A32);
            String text2 = getString(R.string.Rb0B32);
            String text3 = getString(R.string.Rb0C32);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A033.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A033);
            String text1 = getString(R.string.Rb0A33);
            String text2 = getString(R.string.Rb0B33);
            String text3 = getString(R.string.Rb0C33);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A034.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A034);
            String text1 = getString(R.string.Rb0A34);
            String text2 = getString(R.string.Rb0B34);
            String text3 = getString(R.string.Rb0C34);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A035.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A035);
            String text1 = getString(R.string.Rb0A35);
            String text2 = getString(R.string.Rb0B35);
            String text3 = getString(R.string.Rb0C35);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A036.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A036);
            String text1 = getString(R.string.Rb0A36);
            String text2 = getString(R.string.Rb0B36);
            String text3 = getString(R.string.Rb0C36);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A037.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A037);
            String text1 = getString(R.string.Rb0A37);
            String text2 = getString(R.string.Rb0B37);
            String text3 = getString(R.string.Rb0C37);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A038.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A038);
            String text1 = getString(R.string.Rb0A38);
            String text2 = getString(R.string.Rb0B38);
            String text3 = getString(R.string.Rb0C38);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A039.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A039);
            String text1 = getString(R.string.Rb0A39);
            String text2 = getString(R.string.Rb0B39);
            String text3 = getString(R.string.Rb0C39);

            // Pass the concatenated text to MainActivity2 using Intent
            Intent intent = new Intent(getActivity(), Duuass_open.class);
            intent.putExtra("title", title);
            intent.putExtra("textViewData1", text1);
            intent.putExtra("textViewData2", text2);
            intent.putExtra("textViewData3", text3);
            startActivity(intent);
        });
        LinRabbRb0A040.setOnClickListener(View -> {
            // Extract text from the TextView
            String title = getString(R.string.Rb0A040);
            String text1 = getString(R.string.Rb0A40);
            String text2 = getString(R.string.Rb0B40);
            String text3 = getString(R.string.Rb0C40);

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