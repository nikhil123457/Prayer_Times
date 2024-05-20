package com.vitcode.iprayertimes.names.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.names.adapter.NamesListAdapter;
import com.vitcode.iprayertimes.names.data.NamesData;
import com.vitcode.iprayertimes.names.helper.Utils;
import com.vitcode.iprayertimes.names.model.NamesModel;

import java.util.ArrayList;
public class Name99Activity extends AppCompatActivity {
    FrameLayout adContainerView;
    public String audioTotalTime = "00:00";
    private ImageView btnAudio;
    Context context = this;
    public int currentPosition = -1;
    public int delayIndex = 0;
    public Handler handler = new Handler();
    public LinearLayoutManager layoutManager;
    public MediaPlayer mp;
    public int[] nameTiming;
    public View nameView;
    public ArrayList<NamesModel> namesData = new ArrayList<>();
    public NamesListAdapter namesListAdapter;
    public int play = 0;
    private RecyclerView rcvName;
    public Runnable runnableTimeUpdate = new Runnable() {
        public void run() {
            if (Name99Activity.this.mp != null) {
                Name99Activity.this.handler.removeCallbacks(this);
                int currentPosition = Name99Activity.this.mp.getCurrentPosition();
                Name99Activity name99Activity = Name99Activity.this;
                name99Activity.audioTotalTime = "" + Utils.milliSecondsToTimer(Name99Activity.this.totalDuration - currentPosition);
                Name99Activity.this.tvTotalTime.setText(Name99Activity.this.audioTotalTime);
                Name99Activity.this.handler.postDelayed(this, 1000);
            }
        }
    };
    public SeekBar seekBarNames;
    public Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            Name99Activity.this.focusOnView();
            Name99Activity.this.handler.postDelayed(this, 0);
        }
    };
    public int totalDuration = 0;
    public TextView tvTotalTime;

    static int access$908(Name99Activity name99Activity) {
        int i = name99Activity.delayIndex;
        name99Activity.delayIndex = i + 1;
        return i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_99names);


        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Name99Activity.this.onBackPressed();
            }
        });
        initView();
        this.nameView.setTranslationY((float) getResources().getDisplayMetrics().heightPixels);
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                Name99Activity.this.initData();
                Name99Activity.this.initSb();
                Name99Activity.this.initAudio();
                Name99Activity.this.initPlay();
                Name99Activity.this.initList();
                Name99Activity.this.nameView.animate().translationY(0.0f).setDuration(250).start();
            }
        }, 400);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initPlay() {
        this.btnAudio.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Name99Activity.lambda$initPlay$2(Name99Activity.this, view);
            }
        });
    }

    public static void lambda$initPlay$2(Name99Activity name99Activity, View view) {
        if (name99Activity.play == 0) {
            name99Activity.play = 1;
            name99Activity.handler.removeCallbacks(name99Activity.sendUpdatesToUI);
            name99Activity.handler.postDelayed(name99Activity.sendUpdatesToUI, 0);
            name99Activity.handler.removeCallbacks(name99Activity.runnableTimeUpdate);
            name99Activity.handler.post(name99Activity.runnableTimeUpdate);
            name99Activity.mp.start();
            name99Activity.seekBarNames.setEnabled(true);
            name99Activity.btnAudio.setImageResource(R.drawable.posse);
            return;
        }
        name99Activity.handler.removeCallbacks(name99Activity.sendUpdatesToUI);
        name99Activity.handler.removeCallbacks(name99Activity.runnableTimeUpdate);
        name99Activity.mp.pause();
        name99Activity.play = 0;
        name99Activity.seekBarNames.setEnabled(false);
        name99Activity.btnAudio.setImageResource(R.drawable.play);
    }

    public void initAudio() {
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
        this.mp = new MediaPlayer();
        MediaPlayer create = MediaPlayer.create(this, R.raw.allah_full);
        this.mp = create;
        create.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public final void onPrepared(MediaPlayer mediaPlayer) {
                Name99Activity.lambda$initAudio$3(Name99Activity.this, mediaPlayer);
            }
        });
        this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public final void onCompletion(MediaPlayer mediaPlayer) {
                Name99Activity.this.reset();
            }
        });
    }

    public static void lambda$initAudio$3(Name99Activity name99Activity, MediaPlayer mediaPlayer) {
        name99Activity.totalDuration = name99Activity.mp.getDuration();
        String str = "" + Utils.milliSecondsToTimer(name99Activity.totalDuration);
        name99Activity.audioTotalTime = str;
        name99Activity.tvTotalTime.setText(str);
    }

    public void reset() {
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
        initAudio();
        this.tvTotalTime.setText(this.audioTotalTime);
        this.btnAudio.setImageResource(R.drawable.play);
        this.play = 0;
        this.delayIndex = 0;
        this.namesListAdapter.movePosition(0);
        this.layoutManager.scrollToPosition(0);
        this.delayIndex++;
        this.seekBarNames.setProgress(0);
        this.seekBarNames.setEnabled(false);
        this.handler.removeCallbacks(this.runnableTimeUpdate);
        this.handler.removeCallbacks(this.sendUpdatesToUI);
    }

    public void initList() {
        RecyclerView recyclerView = this.rcvName;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        NamesListAdapter r0 = new NamesListAdapter(this, this.namesData) {
            public void OnItemClick(int i) {
                if (Name99Activity.this.play == 1) {
                    Name99Activity.this.currentPosition = i;
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.post(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.mp.pause();
                    Name99Activity name99Activity = Name99Activity.this;
                    name99Activity.delayIndex = name99Activity.currentPosition;
                    Name99Activity.this.namesListAdapter.movePosition(Name99Activity.this.currentPosition);
                    Name99Activity.this.layoutManager.scrollToPosition(Name99Activity.this.currentPosition);
                    Name99Activity.access$908(Name99Activity.this);
                    Name99Activity.this.mp.seekTo(Name99Activity.this.nameTiming[Name99Activity.this.currentPosition]);
                    Name99Activity.this.mp.start();
                    Name99Activity.this.seekBarNames.setEnabled(true);
                    Name99Activity.this.seekBarNames.setProgress(Name99Activity.this.currentPosition);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.handler.post(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.postDelayed(Name99Activity.this.sendUpdatesToUI, 0);
                    return;
                }

            }
        };
        this.namesListAdapter = r0;
        this.rcvName.setAdapter(r0);
    }

    public void initSb() {
        this.seekBarNames.setMax(99);
        this.seekBarNames.setEnabled(false);
        this.seekBarNames.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (Name99Activity.this.play == 1 && z) {
                    Name99Activity.this.currentPosition = i;
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.post(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.handler.post(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.mp.pause();
                    Name99Activity name99Activity = Name99Activity.this;
                    name99Activity.delayIndex = name99Activity.currentPosition;
                    Name99Activity.this.namesListAdapter.movePosition(Name99Activity.this.currentPosition);
                    Name99Activity.this.layoutManager.scrollToPosition(Name99Activity.this.currentPosition);
                    Name99Activity.access$908(Name99Activity.this);
                    Name99Activity.this.mp.seekTo(Name99Activity.this.nameTiming[Name99Activity.this.currentPosition]);
                    Name99Activity.this.mp.start();
                    Name99Activity.this.seekBarNames.setEnabled(true);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.handler.post(Name99Activity.this.runnableTimeUpdate);
                    Name99Activity.this.handler.removeCallbacks(Name99Activity.this.sendUpdatesToUI);
                    Name99Activity.this.handler.postDelayed(Name99Activity.this.sendUpdatesToUI, 0);
                }
            }
        });
    }

    public void initData() {
        NamesData namesData2 = new NamesData(this);
        this.namesData = namesData2.getNamesData();
        this.nameTiming = namesData2.getNameTiming();
    }

    private void initView() {
        this.tvTotalTime = findViewById(R.id.tv_names_total_time);
        this.btnAudio = findViewById(R.id.btn_play_names_full);
        this.rcvName = findViewById(R.id.rcv_99_name);
        this.seekBarNames = findViewById(R.id.seekBarNames);
        this.nameView = findViewById(R.id.view_name);
    }

    public void focusOnView() {
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            int currentPosition2 = mediaPlayer.getCurrentPosition();
            int[] iArr = this.nameTiming;
            int i = this.delayIndex;
            if (currentPosition2 >= iArr[i]) {
                this.currentPosition = i;
                this.namesListAdapter.movePosition(i);
                this.layoutManager.scrollToPosition(this.currentPosition);
                this.seekBarNames.setProgress(this.currentPosition);
                int i2 = this.delayIndex;
                if (i2 < this.nameTiming.length - 1) {
                    this.delayIndex = i2 + 1;
                    return;
                }
                return;
            }
            return;
        }
        this.handler.removeCallbacks(this.sendUpdatesToUI);
    }

    public void onDestroy() {
        super.onDestroy();
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mp = null;
        }
    }
}
