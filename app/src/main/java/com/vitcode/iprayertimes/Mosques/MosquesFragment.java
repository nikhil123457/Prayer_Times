package com.vitcode.iprayertimes.Mosques;

import static com.vitcode.iprayertimes.R.layout.fragment_mosques;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;


public class MosquesFragment extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) fragment_mosques);
        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.bt_map).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + "Mosques" + ""));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                } catch (Exception unused) {
                    Toast.makeText(MosquesFragment.this, "Google Maps not install!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, false);
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        String str = "https://www.google.com/maps/search/Mosques/@" + LocationSave.getLat() + "," + LocationSave.getLon() + ",17z";
        Log.e("hoang", str);
        webView.loadUrl(str);
    }
}
