package com.vitcode.iprayertimes.quran.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.names.helper.Utils;
import com.vitcode.iprayertimes.permission_utils.Func;
import com.vitcode.iprayertimes.permission_utils.PermissionUtil;
import com.vitcode.iprayertimes.quran.adapter.QuranReadAdapter;
import com.vitcode.iprayertimes.quran.dialog.DownloadQuranDialog;
import com.vitcode.iprayertimes.quran.helper.Constants;
import com.vitcode.iprayertimes.quran.helper.QuranHelper;
import com.vitcode.iprayertimes.quran.model.SurahModel;


import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuranReadActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 4544;
    private static final String RECITER_MP3 = "_a.mp3";
    private static int ayahPos;
    public static int surahNumber;
    public String audioFile;
    public File audioFilePath;
    public String audioTotalTime = "00:00";
    private String bismillahText;
    private ImageView btnPlay;
    Context context = this;
    private QuranReadAdapter customAdapter;
    private DownloadQuranDialog downloadQuranDialog;
    public Handler handler = new Handler();
    public PermissionUtil.PermissionRequestObject mRequestObject;
    public MediaPlayer mp;
    public int play = 0;
    private RecyclerView rcvRead;
    public Runnable runnableTimeUpdate = new Runnable() {
        public void run() {
            if (QuranReadActivity.this.mp != null) {
                QuranReadActivity.this.handler.removeCallbacks(this);
                int currentPosition = QuranReadActivity.this.mp.getCurrentPosition();
                QuranReadActivity.this.seekBarAudio.setProgress(currentPosition);
                QuranReadActivity quranReadActivity = QuranReadActivity.this;
                quranReadActivity.audioTotalTime = "" + Utils.milliSecondsToTimer((long) (QuranReadActivity.this.totalDuration - currentPosition));
                QuranReadActivity.this.tvTotalTime.setText(QuranReadActivity.this.audioTotalTime);
                QuranReadActivity.this.handler.postDelayed(this, 1000);
            }
        }
    };
    public SeekBar seekBarAudio;
    private final List<SurahModel> surahList = new ArrayList();
    public String surahName;
    public String[] surahNames;
    public int totalDuration = 0;
    public int[] totalVerses;
    public TextView tvHeading;
    public TextView tvJuzNumber;
    public TextView tvTotalTime;
    private XmlPullParser xpp;

    public static void setSurahNumber(int i) {
        surahNumber = i;
    }

    public static void setAyahPos(int i) {
        ayahPos = i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_read_quran);
        findViewById(R.id.bt_share).setOnClickListener(view -> {
            QuranReadActivity quranReadActivity = QuranReadActivity.this;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" +  quranReadActivity.getPackageName());
            intent.setType("text/plain");
            context.startActivity(intent);
        });
        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QuranReadActivity.this.onBackPressed();
            }
        });
        this.mRequestObject = PermissionUtil.with((AppCompatActivity) this).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").onAllGranted(new Func() {
            public void call() {
                QuranReadActivity quranReadActivity = QuranReadActivity.this;
                quranReadActivity.surahNames = quranReadActivity.getResources().getStringArray(R.array.surah_names);
                QuranReadActivity quranReadActivity2 = QuranReadActivity.this;
                quranReadActivity2.totalVerses = quranReadActivity2.getResources().getIntArray(R.array.noOfVerses);
                QuranReadActivity quranReadActivity3 = QuranReadActivity.this;
                quranReadActivity3.audioFile = QuranReadActivity.surahNumber + QuranReadActivity.RECITER_MP3;
                QuranReadActivity.this.audioFilePath = new File(Constants.rootPathQuran.getAbsolutePath(), QuranReadActivity.this.audioFile);
                QuranReadActivity quranReadActivity4 = QuranReadActivity.this;
                quranReadActivity4.surahName = quranReadActivity4.surahNames[QuranReadActivity.surahNumber + -1];
                QuranReadActivity.this.initView();
                QuranReadActivity.this.initToolbar();
                QuranReadActivity.this.syn();
            }
        }).onAnyDenied(new Func() {
            public void call() {
                QuranReadActivity.this.getSupportFragmentManager().popBackStack();
            }
        }).ask(12);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionUtil.PermissionRequestObject permissionRequestObject = this.mRequestObject;
        if (permissionRequestObject != null) {
            permissionRequestObject.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void onBackPressed() {

        super.onBackPressed();
    }

    public void syn() {
        this.audioFile = surahNumber + RECITER_MP3;
        this.surahName = this.surahNames[surahNumber + -1];
        initSurahData();
        this.tvHeading.setText(this.surahName);
        this.customAdapter = new QuranReadAdapter(this, this, this.surahList, surahNumber) {
            public void ItemClick(int i) {
            }

            public void ItemLongClick(int i) {
            }
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_quran_read);
        this.rcvRead = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.rcvRead.setAdapter(this.customAdapter);
        this.rcvRead.scrollToPosition(ayahPos);
        synAudio();
    }

    public void synAudio() {
        if (this.audioFilePath.exists() && initAudio()) {
            initSb();
        }
    }

    private void initSurahData() {
        this.surahList.clear();
        QuranHelper.Translation.setLastSaveTranslation(this, surahNumber);
        XmlResourceParser xml = getResources().getXml(R.xml.quran_uthmani);
        this.xpp = xml;
        ArrayList<String> translatedAyaList = QuranHelper.XmlParser.getTranslatedAyaList(this, xml, surahNumber, getString(R.string.bismillah));
        this.xpp = QuranHelper.Translation.getTranslationXpp(this);
        String translationBismillahText = QuranHelper.Translation.getTranslationBismillahText(this);
        this.bismillahText = translationBismillahText;
        ArrayList<String> translatedAyaList2 = QuranHelper.XmlParser.getTranslatedAyaList(this, this.xpp, surahNumber, translationBismillahText);
        XmlResourceParser xml2 = getResources().getXml(R.xml.english_transliteration);
        this.xpp = xml2;
        ArrayList<String> translatedAyaList3 = QuranHelper.XmlParser.getTranslatedAyaList(this, xml2, surahNumber, "Bismi Allahi arrahmani arraheem");
        for (int i = 0; i < translatedAyaList.size(); i++) {
            SurahModel surahModel = new SurahModel(-1, translatedAyaList.get(i), translatedAyaList2.get(i), translatedAyaList3.get(i));
            for (int i2 = 0; i2 < 30; i2++) {
                if (QuranHelper.juzAyahNumber[i2] == i && QuranHelper.juzSurrahNumber[i2] == surahNumber) {
                    surahModel.setJuzzIndex(i);
                }
            }
            this.surahList.add(surahModel);
        }
        translatedAyaList.clear();
        translatedAyaList2.clear();
        translatedAyaList3.clear();
        if (surahNumber == 9) {
            this.surahList.remove(0);
        }
    }

    public void initToolbar() {
        this.btnPlay.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QuranReadActivity.this.onPlayClick();
            }
        });
    }

    public void onPlayClick() {
        MediaPlayer mediaPlayer;
        if (!this.audioFilePath.exists() || (mediaPlayer = this.mp) == null) {
            DownloadQuranDialog downloadQuranDialog2 = this.downloadQuranDialog;
            if (downloadQuranDialog2 == null || !downloadQuranDialog2.isShowing()) {
                DownloadQuranDialog downloadQuranDialog3 = new DownloadQuranDialog(this, R.style.SheetDialog);
                this.downloadQuranDialog = downloadQuranDialog3;
                downloadQuranDialog3.set(this.surahName, this.audioFile, this.audioFilePath);
                this.downloadQuranDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public final void onDismiss(DialogInterface dialogInterface) {
                        QuranReadActivity.this.synAudio();
                    }
                });
                this.downloadQuranDialog.show();
            }
        } else if (this.play == 0) {
            this.play = 1;
            mediaPlayer.start();
            this.seekBarAudio.setEnabled(true);
            this.btnPlay.setImageResource(R.drawable.ic_launcher_background);
            this.handler.removeCallbacks(this.runnableTimeUpdate);
            this.handler.post(this.runnableTimeUpdate);
        } else {
            this.handler.removeCallbacks(this.runnableTimeUpdate);
            this.mp.pause();
            this.play = 0;
            this.seekBarAudio.setEnabled(false);
            this.btnPlay.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    private void initSb() {
        this.seekBarAudio.setMax(this.mp.getDuration());
        this.seekBarAudio.setEnabled(false);
        this.seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (QuranReadActivity.this.play == 1 && z) {
                    QuranReadActivity.this.handler.removeCallbacks(QuranReadActivity.this.runnableTimeUpdate);
                    QuranReadActivity.this.handler.post(QuranReadActivity.this.runnableTimeUpdate);
                    QuranReadActivity.this.mp.pause();
                    QuranReadActivity.this.mp.seekTo(i);
                    QuranReadActivity.this.mp.start();
                    QuranReadActivity.this.seekBarAudio.setEnabled(true);
                    QuranReadActivity.this.handler.removeCallbacks(QuranReadActivity.this.runnableTimeUpdate);
                    QuranReadActivity.this.handler.post(QuranReadActivity.this.runnableTimeUpdate);
                }
            }
        });
    }

    private boolean initAudio() {
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
        MediaPlayer create = MediaPlayer.create(this, Uri.parse(this.audioFilePath.getAbsolutePath()));
        this.mp = create;
        if (create == null) {
            this.audioFilePath.deleteOnExit();
            return false;
        }
        create.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public final void onPrepared(MediaPlayer mediaPlayer) {
                QuranReadActivity.lambda$initAudio$3(QuranReadActivity.this, mediaPlayer);
            }
        });
        this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public final void onCompletion(MediaPlayer mediaPlayer) {
                QuranReadActivity.this.reset();
            }
        });
        return true;
    }

    public static void lambda$initAudio$3(QuranReadActivity quranReadActivity, MediaPlayer mediaPlayer) {
        quranReadActivity.totalDuration = quranReadActivity.mp.getDuration();
        String str = "" + Utils.milliSecondsToTimer((long) quranReadActivity.totalDuration);
        quranReadActivity.audioTotalTime = str;
        quranReadActivity.tvTotalTime.setText(str);
    }

    public void reset() {
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
        initAudio();
        this.tvTotalTime.setText(this.audioTotalTime);
        this.btnPlay.setImageResource(R.drawable.ic_launcher_background);
        this.play = 0;
        this.seekBarAudio.setProgress(0);
        this.seekBarAudio.setEnabled(false);
    }

    public void initView() {
        this.btnPlay = (ImageView) findViewById(R.id.bt_play);
        this.tvHeading = (TextView) findViewById(R.id.tv_quran_name);
        this.tvJuzNumber = (TextView) findViewById(R.id.tv_juz_page);
        this.tvTotalTime = (TextView) findViewById(R.id.tv_total_time);
        this.seekBarAudio = (SeekBar) findViewById(R.id.sb_audio);
    }

    public void onDestroy() {
        super.onDestroy();
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
            startActivityForResult(intent, 2296);
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISSION_REQUEST_CODE);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == PERMISSION_REQUEST_CODE && Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
        }
    }
}
