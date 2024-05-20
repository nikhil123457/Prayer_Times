package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.databinding.ActivityTermsBinding;

public class TermsActivity extends AppCompatActivity {

    private ActivityTermsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNext.setOnClickListener(v -> {
            Pref.getInstance().setString(Constant.TERMS, "terms");
            Intent intent = new Intent(TermsActivity.this, LanguageActivity.class);
            startActivity(intent);
        });

    }
}