package com.vitcode.iprayertimes.splash;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;

public class PrivacyPolicyActivity extends AppCompatActivity {
    ImageView iv_back_arrow;
    WebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_policy);
        initView();
        this.iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new MyBrowser());
        this.webView.loadUrl("https://nakatomizork.blogspot.com/p/privacy-policy.html");
    }


    private void initView() {
        this.iv_back_arrow = findViewById(R.id.iv_back_arrow);
        this.webView = findViewById(R.id.webView);
    }

    class MyBrowser extends WebViewClient {
        MyBrowser() {
        }


    }
}
