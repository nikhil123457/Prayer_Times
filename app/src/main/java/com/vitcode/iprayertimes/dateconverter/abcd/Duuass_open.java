package com.vitcode.iprayertimes.dateconverter.abcd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;

public class Duuass_open extends AppCompatActivity {


    TextView one1,twoo2,three3,title;

    ImageView backIMG;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duuass_open);

        title = findViewById(R.id.Title);
        one1 = findViewById(R.id.one1);
        twoo2 = findViewById(R.id.twoo2);
        three3 = findViewById(R.id.three3);

        backIMG=findViewById(R.id.backIMG);
        backIMG.setOnClickListener(v -> {onBackPressed();});

        // Get the data passed from MainActivity
        // Get the concatenated text passed from MainActivity
        String Title = getIntent().getStringExtra("title");
        String receivedData1 = getIntent().getStringExtra("textViewData1");
        String receivedData2 = getIntent().getStringExtra("textViewData2");
        String receivedData3 = getIntent().getStringExtra("textViewData3");

// Split the concatenated text into three separate strings


// Now you can use these three strings as needed

        // Set the received data to the TextView
        title.setText(Title);
        one1.setText(receivedData1);
        twoo2.setText(receivedData2);
        three3.setText(receivedData3);



    }
}