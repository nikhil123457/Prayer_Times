package com.vitcode.iprayertimes.hadith;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;

import java.io.IOException;
import java.io.InputStream;

public class ActivityHadith extends Utils  {
    int id = 1;
    ImageView img_slider_next;
    ImageView img_slider_previous;
    int interflag = 1;
    String lang = "arabic";
    LinearLayout lyt_content;

    String text;
    TextView txt_arabic;
    TextView txt_counter;
    TextView txt_english;
    TextView txt_title;
    ImageView back;
    ImageView btnLanguageSwitch;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith);

        this.txt_title = findViewById(R.id.txt_title);
        this.txt_arabic = findViewById(R.id.txt_arabic);
        this.txt_english = findViewById(R.id.txt_english);
        this.txt_counter = findViewById(R.id.txt_counter);
        this.back = findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnLanguageSwitch = findViewById(R.id.btn_language_switch);
        btnLanguageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle language switch when ImageButton is clicked
                switchLanguage();
            }
        });


        this.img_slider_next = findViewById(R.id.img_slider_next);
        this.img_slider_previous = findViewById(R.id.img_slider_previous);
        this.lyt_content = findViewById(R.id.lyt_content);
        this.txt_arabic.setTypeface(this.tf2);
        this.txt_counter.setTypeface(this.tf2, Typeface.BOLD);
        getContent(this.id);
        this.img_slider_previous.setVisibility(View.INVISIBLE);
        this.img_slider_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityHadith.this.lyt_content.startAnimation(AnimationUtils.loadAnimation(ActivityHadith.this, R.anim.push_left_out));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ActivityHadith.this.id++;
                        if (ActivityHadith.this.id > 42) {
                            ActivityHadith.this.id = 42;
                        }
                        ActivityHadith.this.getContent(ActivityHadith.this.id);
                        ActivityHadith.this.lyt_content.startAnimation(AnimationUtils.loadAnimation(ActivityHadith.this, R.anim.push_left_in));
                        ActivityHadith.this.changeSliderView();
                    }
                }, 150);
            }
        });
        this.img_slider_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityHadith.this.lyt_content.startAnimation(AnimationUtils.loadAnimation(ActivityHadith.this, R.anim.push_right_out));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ActivityHadith.this.id--;
                        if (ActivityHadith.this.id < 0) {
                            ActivityHadith.this.id = 1;
                        }
                        ActivityHadith.this.getContent(ActivityHadith.this.id);
                        ActivityHadith.this.lyt_content.startAnimation(AnimationUtils.loadAnimation(ActivityHadith.this, R.anim.push_right_in));
                        ActivityHadith.this.changeSliderView();
                    }
                }, 150);
            }
        });

    }

    private void switchLanguage() {
        // Toggle between Arabic and English languages
        if (lang.equals("arabic")) {
            lang = "english";
        } else {
            lang = "arabic";
        }
        // Update content based on new language
        getContent(id);
    }

    public void onResume() {
        font();
        super.onResume();
    }

    public void changeSliderView() {
        if (this.id < 0 || this.id == 1) {
            this.img_slider_previous.setVisibility(View.INVISIBLE);
        } else {
            this.img_slider_previous.setVisibility(View.VISIBLE);
        }
        if (this.id == 42) {
            this.img_slider_next.setVisibility(View.INVISIBLE);
        } else {
            this.img_slider_next.setVisibility(View.VISIBLE);
        }
    }

    public void getContent(int id2) {
        try {
            this.txt_title.setText("Hadith " + id2);
            this.txt_counter.setText(id2 + "/42");
            this.txt_title.setTextAppearance(this, this.styleheader[this.efont]);
            this.txt_counter.setTextAppearance(this, this.style[this.efont]);
            InputStream input = getAssets().open("hadith/Hadith " + id2 + ".txt");
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();
            String[] content = new String(buffer).split("English:");
            if (this.lang.equals("arabic")) {
                this.txt_arabic.setText(content[0].trim().replace("Arabic:", ""));
                this.txt_arabic.setTypeface(this.tf_arabic);
            } else if (this.lang.equals("english")) {
                this.txt_arabic.setTypeface(this.tf2);
                this.txt_arabic.setText(content[1].trim().replace("Arabic:", ""));
            }
        } catch (IOException e) {
        }
    }



}
