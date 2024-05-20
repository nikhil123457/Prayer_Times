package com.vitcode.iprayertimes.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    ActivityAboutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.AboutBackIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.btnTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Url = "https://appsviha.blogspot.com/p/terms-and-conditions.html";
                CustomTabsIntent.Builder customintent = new CustomTabsIntent.Builder();
                customintent.setToolbarColor(ContextCompat.getColor(AboutActivity.this, R.color.white));
                openCustamTab(AboutActivity.this, customintent.build(), Uri.parse(Url));
            }
        });


    }

    private void openCustamTab(AboutActivity webview1, CustomTabsIntent build, Uri parse) {

        String packageName = "com.android.chrome";
        if (packageName != null) {

            build.intent.setPackage(packageName);
            build.launchUrl(webview1, parse);

        } else {
            webview1.startActivity(new Intent(Intent.ACTION_VIEW, parse));
        }
    }
}