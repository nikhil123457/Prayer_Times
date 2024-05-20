package com.vitcode.iprayertimes.compass.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.internal.view.SupportMenu;

import com.bumptech.glide.Glide;
import com.vitcode.iprayertimes.R;


public class CalibrateCompassDialog extends Dialog {
    private String status;

    public CalibrateCompassDialog(Context context, String str) {
        super(context, R.style.Translucent);
        this.status = str.toUpperCase();
        init();
    }

    @SuppressLint("RestrictedApi")
    private void init() {
        setContentView(R.layout.dialog_calibrate_compass);
        findViewById(R.id.bt_done).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CalibrateCompassDialog.this.dismiss();
            }
        });
        Glide.with(getContext()).asGif().load(Integer.valueOf(R.drawable.compassfix)).into((ImageView) findViewById(R.id.img_calibrate));
        TextView textView = (TextView) findViewById(R.id.tv_compass_status);
        textView.setText(this.status);
        if (this.status.equals("LOW")) {
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            textView.setTextColor(-16776961);
        }
    }
}
