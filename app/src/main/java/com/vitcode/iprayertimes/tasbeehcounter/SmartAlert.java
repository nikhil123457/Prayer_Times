package com.vitcode.iprayertimes.tasbeehcounter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;

public class SmartAlert extends Dialog {
    public static final int BUTTON_BACK = 3;
    public static final int BUTTON_CANCEL = 2;
    public static final int BUTTON_OK = 1;
    private static EditText editText_tasbihMax;
    private static EditText editText_tasbihName;
    private String mAction1Title;
    private String mAction2Title;
    private String mAlertTitle;
    private TextView mBtnAction1;
    private TextView mBtnAction2;
    /* access modifiers changed from: private */
    public SmartAlertListener mListener;
    private ProgressBar mLoading;

    public interface SmartAlertListener {
        void result(int i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.alert_smart_layout);
        ((TextView) findViewById(R.id.txtTitle)).setText(this.mAlertTitle);
        this.mLoading = (ProgressBar) findViewById(R.id.pbLoading);
        editText_tasbihName = (EditText) findViewById(R.id.tasbih_name);
        editText_tasbihMax = (EditText) findViewById(R.id.tasbih_max);
        this.mBtnAction1 = (TextView) findViewById(R.id.btnAction1);
        this.mBtnAction2 = (TextView) findViewById(R.id.btnAction2);
        activeResultButton();
        this.mBtnAction1.setText(this.mAction1Title);
        this.mBtnAction1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SmartAlert.this.mListener != null) {
                    SmartAlert.this.mListener.result(1);
                }
                SmartAlert.this.dismiss();
            }
        });
        String str = this.mAction2Title;
        if (str != null) {
            this.mBtnAction2.setText(str);
            this.mBtnAction2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SmartAlert.this.mListener != null) {
                        SmartAlert.this.mListener.result(2);
                    }
                    SmartAlert.this.dismiss();
                }
            });
        } else {
            this.mBtnAction2.setVisibility(View.GONE);
        }
        setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        setCancelable(false);
    }

    public static String getTasbihName() {
        return editText_tasbihName.getText().toString();
    }

    public static int getTasbihMax() {
        if (editText_tasbihMax.getText().toString().length() > 0) {
            return Integer.parseInt(editText_tasbihMax.getText().toString());
        }
        return 100;
    }

    public void onBackPressed() {
        SmartAlertListener smartAlertListener = this.mListener;
        if (smartAlertListener != null) {
            smartAlertListener.result(3);
        }
        super.onBackPressed();
    }

    private void activeResultButton() {
        this.mLoading.setVisibility(View.GONE);
        this.mBtnAction1.setEnabled(true);
        this.mBtnAction2.setEnabled(true);
    }

    @SuppressLint("ResourceType")
    public SmartAlert(Context context, String str, String str2, String str3, SmartAlertListener smartAlertListener) {
        super(context, 16974128);
        this.mAlertTitle = str;
        this.mAction1Title = str2;
        this.mAction2Title = str3;
        this.mListener = smartAlertListener;
    }

    public static void select(Context context, String str, String str2, String str3, SmartAlertListener smartAlertListener) {
        new SmartAlert(context, str, str2, str3, smartAlertListener).show();
    }

    public static SmartAlert getSelectDialog(Context context, String str, String str2, String str3, SmartAlertListener smartAlertListener) {
        return new SmartAlert(context, str, str2, str3, smartAlertListener);
    }

    @SuppressLint("ResourceType")
    public static void confirm(Context context, String str, SmartAlertListener smartAlertListener) {
        select(context, str, context.getString(17039370), context.getString(17039360), smartAlertListener);
    }

    @SuppressLint("ResourceType")
    public static void alert(Context context, String str, SmartAlertListener smartAlertListener) {
        select(context, str, context.getString(17039370), (String) null, smartAlertListener);
    }

    public static void alert(Context context, String str, String str2, SmartAlertListener smartAlertListener) {
        select(context, str, str2, (String) null, smartAlertListener);
    }
}
