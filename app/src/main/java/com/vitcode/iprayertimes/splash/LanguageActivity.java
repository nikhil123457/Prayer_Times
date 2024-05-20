package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.databinding.ActivityLanguageBinding;

import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {
    private ActivityLanguageBinding binding;

    public static List<LanguageModel> languageModel = new ArrayList<>();
    String[] tvAge = {"English", "Hindi", "German", "French", "Portuguese", "Indonesian", "Italian", "Spanish", "Russian", "Chinese", "Japanese", "Turkish", "Korean", "Arabic"};
    Integer[] languageImage = {R.drawable.flag_usa, R.drawable.flag_india, R.drawable.flag_germany, R.drawable.flag_france,
            R.drawable.flag_portugal, R.drawable.flag_indonesia, R.drawable.flag_qitaly, R.drawable.flag_spain, R.drawable.flag_russia,
            R.drawable.flag_china, R.drawable.flag_japan, R.drawable.flag_turkey, R.drawable.flag_south_korea, R.drawable.flag_uae};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        for (int i = 0; i < tvAge.length; i++) {
            LanguageModel languageItem = new LanguageModel();
            languageItem.setTvName(tvAge[i]);
            languageItem.setIvImage(languageImage[i]);
            languageModel.add(languageItem);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        LanguageAdapter languageAdapter = new LanguageAdapter(this, languageModel);
        binding.rvLang.setLayoutManager(layoutManager);
        binding.rvLang.setAdapter(languageAdapter);

        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApp.selectedLanguagePosition == -1) {
                    Toast.makeText(LanguageActivity.this, "Please select your language.", Toast.LENGTH_SHORT).show();
                } else {
                    Pref.getInstance().setString(Constant.LANG, "lang");
                    IntentClickToNextStart();
                }
            }
        });
    }

    private void IntentClickToNextStart() {
        Intent intent = new Intent(LanguageActivity.this, StartActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}