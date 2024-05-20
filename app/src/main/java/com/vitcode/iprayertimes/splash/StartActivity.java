package com.vitcode.iprayertimes.splash;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.dateconverter.abcd.Homee;
import com.vitcode.iprayertimes.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, Homee.class);
                startActivity(intent);
            }
        });

        binding.imgShare.setOnClickListener(v -> {
            IntentClickToNextShare();
        });
        binding.imgRate.setOnClickListener(v -> {
            IntentClickToNextRate();
        });
        binding.imgPrivacy.setOnClickListener(v -> {
            IntentClickToNextPrivacy();
        });

    }


    private void IntentClickToNextShare() {
        String textToShare = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void IntentClickToNextRate() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.rate_dialog);

        ImageView btnClose = dialog.findViewById(R.id.imgClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // If Play Store app is not available, open the app's page on the browser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void IntentClickToNextPrivacy() {
        String link = "https://appsviha.blogspot.com/p/privacy-policy.html";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(link));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}