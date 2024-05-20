package com.vitcode.iprayertimes.calender.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.calender.model.EventModel;


public class EventDetailActivity extends AppCompatActivity {
    private static EventModel _eventModel;
    ImageView bt_back;
    Context context = this;

    public static void setEvent(EventModel eventModel) {
        _eventModel = eventModel;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_event_detail);

        this.bt_back = findViewById(R.id.bt_back);
        ((TextView) findViewById(R.id.tv_title)).setText(_eventModel.getEventName());
        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(_eventModel.getUrl());
        this.bt_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EventDetailActivity.this.onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
