package com.vitcode.iprayertimes.quran.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.downloadmanager.DefaultRetryPolicy;
import com.vitcode.iprayertimes.downloadmanager.DownloadRequest;
import com.vitcode.iprayertimes.downloadmanager.DownloadStatusListenerV1;
import com.vitcode.iprayertimes.downloadmanager.ThinDownloadManager;
import com.vitcode.iprayertimes.quran.helper.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;



import java.io.File;
import java.util.Date;
import java.util.TimeZone;

public class DownloadQuranDialog extends BottomSheetDialog {
    public File audioFilePath;
    private String audioName = "";
    public ThinDownloadManager downloadManager = new ThinDownloadManager();
    public boolean downloadSuccess = false;
    private String name = "";
    public String url;

    public DownloadQuranDialog(Context context, int i) {
        super(context, i);
    }

    public void set(String str, String str2, File file) {
        this.name = str;
        this.audioName = str2;
        this.audioFilePath = file;
        init();
    }

    private void init() {
        setContentView((int) R.layout.dialog_quran_download);
        getLink();
        setCancelable(false);
        final TextView textView = (TextView) findViewById(R.id.tv_download);
        final TextView textView2 = (TextView) findViewById(R.id.bt_ok);
        final TextView textView3 = (TextView) findViewById(R.id.bt_cancel);
        textView.setText(getContext().getResources().getString(R.string.doYouWant) + this.name + "?");
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri parse = Uri.parse(DownloadQuranDialog.this.url);
                textView2.setVisibility(View.INVISIBLE);
                DownloadQuranDialog.this.downloadManager.add(new DownloadRequest(parse).addCustomHeader(DownloadQuranDialog.this.getContext().getResources().getString(R.string.auth_token), DownloadQuranDialog.this.getContext().getResources().getString(R.string.yourTokenApikey)).setRetryPolicy(new DefaultRetryPolicy()).setDestinationURI(Uri.parse(DownloadQuranDialog.this.audioFilePath.getAbsolutePath())).setPriority(DownloadRequest.Priority.HIGH).setDownloadContext(DownloadQuranDialog.this.getContext()).setStatusListener(new DownloadStatusListenerV1() {
                    public void onDownloadComplete(DownloadRequest downloadRequest) {
                        DownloadQuranDialog.this.downloadSuccess = true;
                        textView.post(new Runnable() {
                            @SuppressLint("ResourceAsColor")
                            public void run() {
                                textView.setText(R.string.succes);
                                textView3.setText(R.string.ok);
                                textView3.setTextColor(R.color.colorPrimary2);
                            }
                        });
                    }

                    public void onDownloadFailed(DownloadRequest downloadRequest, int i, String str) {
                        DownloadQuranDialog.this.downloadSuccess = false;
                        textView.post(new Runnable() {
                            public void run() {
                                DownloadQuranDialog.this.deleteFile();
                                textView.setText(R.string.FailPlaease);
                                textView3.setText(R.string.ok);
                            }
                        });
                    }

                    public void onProgress(DownloadRequest downloadRequest, long j, long j2, final int i) {
                        textView.post(new Runnable() {
                            public void run() {
                                TextView textView = null;
                                textView.setText(DownloadQuranDialog.this.getContext().getResources().getString(R.string.downloading) + "..( " + i + "% )");
                            }
                        });
                    }
                }));
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!DownloadQuranDialog.this.downloadSuccess) {
                    DownloadQuranDialog.this.deleteFile();
                }
                DownloadQuranDialog.this.downloadManager.cancelAll();
                DownloadQuranDialog.this.dismiss();
            }
        });
    }

    public void deleteFile() {
        new Thread() {
            public void run() {
                super.run();
                DownloadQuranDialog.this.audioFilePath.deleteOnExit();
            }
        };
    }

    private void getLink() {
        double offset = (double) ((TimeZone.getDefault().getOffset(new Date().getTime()) / 1000) / 60);
        Double.isNaN(offset);
        Double.isNaN(offset);
        double d = offset / 60.0d;
        if (d >= 4.0d && d <= 13.0d) {
            this.url = Constants.AS_BASE_URL;
        } else if (d < -13.0d || d > -4.0d) {
            this.url = Constants.EU_BASE_URL;
        } else {
            this.url = Constants.US_BASE_URL;
        }
        this.url += this.audioName;
    }
}
