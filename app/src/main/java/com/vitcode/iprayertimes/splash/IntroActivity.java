package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.databinding.ActivityIntroBinding;


public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    index--;
                    updateIntroScreen();
                }
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    binding.btnBack.setVisibility(View.VISIBLE);
                    binding.ivMainImage.setImageResource(R.drawable.intro_img2);
                    binding.ivDots.setImageResource(R.drawable.intro_dot2);
                    binding.tvIntroTitle.setText(getString(R.string.intro_title2));
                    binding.tvSubTitle.setText(getString(R.string.intro_sub2));
                } else if (index == 1) {
                    binding.btnBack.setVisibility(View.VISIBLE);
                    binding.ivMainImage.setImageResource(R.drawable.intro_img3);
                    binding.ivDots.setImageResource(R.drawable.intro_dot3);
                    binding.tvIntroTitle.setText(getString(R.string.intro_title3));
                    binding.tvSubTitle.setText(getString(R.string.intro_sub3));
                } else if (index == 2) {
                    binding.btnBack.setVisibility(View.GONE);
                    binding.btnNext.setText("Get Started");
                    binding.ivMainImage.setImageResource(R.drawable.intro_img4);
                    binding.ivDots.setImageResource(R.drawable.intro_dot4);
                    binding.tvIntroTitle.setText(getString(R.string.intro_title4));
                    binding.tvSubTitle.setText(getString(R.string.intro_sub4));
                } else if (index == 3) {
                    nextActivity();
                }
                index++;
            }
        });
    }

    private void updateIntroScreen() {
        if (index == 0) {
            binding.btnBack.setVisibility(View.GONE);
            binding.ivMainImage.setImageResource(R.drawable.intro_img1);
            binding.ivDots.setImageResource(R.drawable.intro_dot1);
            binding.tvIntroTitle.setText(getString(R.string.intro_title1));
            binding.tvSubTitle.setText(getString(R.string.intro_sub1));
        } else if (index == 1) {
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.ivMainImage.setImageResource(R.drawable.intro_img2);
            binding.ivDots.setImageResource(R.drawable.intro_dot2);
            binding.tvIntroTitle.setText(getString(R.string.intro_title2));
            binding.tvSubTitle.setText(getString(R.string.intro_sub2));
        } else if (index == 2) {
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.ivMainImage.setImageResource(R.drawable.intro_img3);
            binding.ivDots.setImageResource(R.drawable.intro_dot3);
            binding.tvIntroTitle.setText(getString(R.string.intro_title3));
            binding.tvSubTitle.setText(getString(R.string.intro_sub3));
        }
    }

    private void nextActivity() {
        Pref.getInstance().setString(Constant.INTRO, "intro");
        Intent intent = new Intent(IntroActivity.this, TermsActivity.class);
        startActivity(intent);
    }
}