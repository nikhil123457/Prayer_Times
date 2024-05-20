package com.vitcode.iprayertimes.tasbeehcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.vitcode.iprayertimes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class MainActivity extends BaseActivity {
    int count = 0;
    Intent intent;
    int max;
    MediaPlayer mp;
    Vibrator myVibrator;
    String name;
    ProgressBar progressBarTasbih;
    int sets;
    SharedPreferences sharedPreferences;
    public boolean sound = true;
    int total_count = 0;
    TextView tvName;
    TextView tvProgress;
    TextView tvSets;
    TextView tvTotal;
    LinearLayout volume, refresh;
    ImageView bt_back;

    public boolean vib = true;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_tasbeeh);
        this.intent = getIntent();
        this.sharedPreferences = getSharedPreferences("tasbih", 0);
        this.progressBarTasbih = findViewById(R.id.progress_ring_tasbih);
        this.volume = findViewById(R.id.volume);
        this.refresh = findViewById(R.id.refresh);
        this.bt_back = findViewById(R.id.bt_back);


        this.tvProgress = findViewById(R.id.progress_tv_tasbih);
        this.myVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        this.tvSets = findViewById(R.id.tv_sets);
        this.tvTotal = findViewById(R.id.tv_total);
        this.tvName = findViewById(R.id.tasbihName);
        int i = this.sharedPreferences.getInt("imgID", 1);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetMethod();
            }
        });
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingMethod();

            }
        });
        try {
            this.mp = MediaPlayer.create(getApplicationContext(), R.raw.tick);
        } catch (Exception unused) {
            Toast.makeText(this, "Sound not Initialized!", Toast.LENGTH_SHORT).show();
        }
        this.progressBarTasbih.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.vibAndSound();
                MainActivity.this.total_count++;
                MainActivity.this.count++;
                MainActivity mainActivity = MainActivity.this;
                mainActivity.sets = mainActivity.total_count / MainActivity.this.max;
                if (MainActivity.this.count > MainActivity.this.max) {
                    MainActivity.this.count = 1;
                }
                MainActivity.this.updateProgress();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    public void updateProgress() {
        this.tvName.setText(this.sharedPreferences.getString("tasbihName", null));
        TextView textView = this.tvSets;
        textView.setText(this.count + "/" + "33");
        TextView textView2 = this.tvProgress;
        textView2.setText("" + this.count);
        this.progressBarTasbih.setProgress(this.count);
        TextView textView3 = this.tvTotal;
        textView3.setText("Total: " + this.total_count);
    }

    private void startProgress() {
        this.tvName.setText(this.sharedPreferences.getString("tasbihName", null));
        TextView textView = this.tvSets;
        textView.setText(this.count + "/" + "33");
        TextView textView2 = this.tvTotal;
        textView2.setText("Total: " + this.total_count);
        TextView textView3 = this.tvProgress;
        textView3.setText("" + this.count);
        ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(this.progressBarTasbih, 1.0f, (float) this.count);
        progressBarAnimation.setDuration(1000);
        this.progressBarTasbih.startAnimation(progressBarAnimation);
    }

    public void vibAndSound() {
        if (this.vib) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.myVibrator.vibrate(VibrationEffect.createOneShot(100, -1));
            }
            if (Build.VERSION.SDK_INT < 26) {
                this.myVibrator.vibrate(100);
            }
        }
        if (this.sound) {
            try {
                if (this.mp.isPlaying()) {
                    this.mp.stop();
                    this.mp.release();
                    this.mp = MediaPlayer.create(getApplicationContext(), R.raw.tick);
                }
                this.mp.start();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public String loadJSONFromAsset() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(getApplicationContext().getFilesDir(), "tasbih.json"));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onPause() {
        super.onPause();
        updatedata();
        this.sharedPreferences.edit().remove("max");
    }

    public void onResume() {
        super.onResume();
        this.vib = this.sharedPreferences.getBoolean("vib", true);
        this.sound = this.sharedPreferences.getBoolean("sound", true);
        load();
        this.sets = this.total_count / this.max;
        startProgress();
    }

    public void updatedata() {
        try {
            JSONObject jSONObject = new JSONObject(loadJSONFromAsset());
            JSONArray jSONArray = jSONObject.getJSONArray("Tasbeehat");
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject2.put("total_count", this.total_count);
            jSONObject2.put("max", this.max);
            jSONObject2.put("sets", this.sets);
            jSONObject3.put(this.sharedPreferences.getString("tasbihName", null), jSONObject2);
            for (int i = 0; i < jSONArray.length(); i++) {
                for (String equals : iterate(jSONArray.getJSONObject(i).keys())) {
                    if (equals.equals(this.sharedPreferences.getString("tasbihName", null)) && Build.VERSION.SDK_INT >= 19) {
                        jSONArray.remove(i);
                        jSONArray.put(jSONObject3);
                    }
                }
            }
            jSONObject.put("Tasbeehat", jSONArray);
            String jSONObject4 = jSONObject.toString();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getApplicationContext().getFilesDir(), "tasbih.json")));
            bufferedWriter.write(jSONObject4);
            bufferedWriter.close();
        } catch (IOException | JSONException e) {
            Context applicationContext = getApplicationContext();
            Toast.makeText(applicationContext, e.getLocalizedMessage() + ": update", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void load() {

        this.name = this.sharedPreferences.getString("tasbihName", null);
        try {
            JSONArray jSONArray = new JSONObject(loadJSONFromAsset()).getJSONArray("Tasbeehat");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                for (String equals : iterate(jSONObject.keys())) {
                    if (equals.equals(this.name)) {
                        this.total_count = jSONObject.getJSONObject(this.name).getInt("total_count");
                        this.max = jSONObject.getJSONObject(this.name).getInt("max");
                    }
                }
            }
            int i2 = this.sharedPreferences.getInt("max", this.max);
            this.max = i2;
            if (i2 == 0) {
                this.max = 33;
            }
            this.count = this.total_count % this.max;
            this.editor.remove("max");
            this.editor.commit();
            this.progressBarTasbih.setMax(this.max);
        } catch (JSONException e) {
            Context applicationContext = getApplicationContext();
            Toast.makeText(applicationContext, e.getLocalizedMessage() + ": load", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private <T> Iterable<T> iterate(final Iterator<T> it) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return it;
            }
        };
    }


    private void resetMethod() {
        if (this.vib) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.myVibrator.vibrate(VibrationEffect.createOneShot(100, -1));
            }
            if (Build.VERSION.SDK_INT < 26) {
                this.myVibrator.vibrate(100);
            }
        }
        this.count = 0;
        this.total_count = 0;
        this.sets = 0;
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putInt("total", 0);
        edit.commit();
        updateProgress();
    }

    private void settingMethod() {
        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), setting.class).putExtra("max", MainActivity.this.max));
    }
}
