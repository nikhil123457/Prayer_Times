package com.vitcode.iprayertimes.splash;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.vitcode.iprayertimes.R;

public class NavigationActivity extends AppCompatActivity {

    LinearLayout LinShre, LinRateus, LIinContctUS, LinPrivacyp, LinAboutus, Linhelp, LinGames;
    ImageView bt_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        LinShre = findViewById(R.id.LinShre);
        LinShre.setOnClickListener(v -> {IntentClickToNextShare();});

        LinRateus = findViewById(R.id.LinRateus);
        LinRateus.setOnClickListener(v -> {IntentClickToNextRate();});

        LinPrivacyp = findViewById(R.id.LinPrivacyp);
        LinPrivacyp.setOnClickListener(v -> {IntentClickToNextPrivacy();});

        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(v -> {onBackPressed();});

        LIinContctUS = findViewById(R.id.LIinContctUS);
        LIinContctUS.setOnClickListener(v->{
            startActivity(new Intent(NavigationActivity.this, ContactActivity.class));
        });

        LinAboutus = findViewById(R.id.LinAboutus);
        LinAboutus.setOnClickListener(v->{
            startActivity(new Intent(NavigationActivity.this, AboutActivity.class));
        });

        Linhelp = findViewById(R.id.Linhelp);
        Linhelp.setOnClickListener(v->{
            startActivity(new Intent(NavigationActivity.this, FeedBackActivity.class));
        });

        LinGames = findViewById(R.id.LinGames);
        LinGames.setOnClickListener(v -> {IntentClickTogame();});
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

    private void IntentClickTogame() {
        String link = "https://appsviha.blogspot.com/p/privacy-policy.html";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(link));
    }
}