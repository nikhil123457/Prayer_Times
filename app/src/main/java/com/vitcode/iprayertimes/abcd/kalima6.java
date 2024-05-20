package com.vitcode.iprayertimes.abcd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;

public class kalima6 extends AppCompatActivity {

    ImageView Ik1,IK11,Ik111,bt_back;
    ImageView Ik2,IK22,Ik222;
    ImageView Ik3,IK33,Ik333;
    ImageView Ik4,IK44,Ik444;
    ImageView Ik5,IK55,Ik555;
    ImageView Ik6,IK66,Ik666;
    MediaPlayer mediaPlayerK1, mediaPlayerK2, mediaPlayerK3, mediaPlayerK4, mediaPlayerK5, mediaPlayerK6;

    TextView Tk01,Tk1,Tk11,Tk111;
    TextView Tk02,Tk2,Tk22,Tk222;
    TextView Tk03,Tk3,Tk33,Tk333;
    TextView Tk04,Tk4,Tk44,Tk444;
    TextView Tk05,Tk5,Tk55,Tk555;
    TextView Tk06,Tk6,Tk66,Tk666;

    boolean isPlayingK1 = false;
    boolean isPlayingK2 = false;
    boolean isPlayingK3 = false;
    boolean isPlayingK4 = false;
    boolean isPlayingK5 = false;
    boolean isPlayingK6 = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalima6);

        Ik1 = findViewById(R.id.Ik1);
        IK11 = findViewById(R.id.IK11);
        Ik111 = findViewById(R.id.Ik111);
        Tk01 = findViewById(R.id.Tk01);
        Tk1 = findViewById(R.id.Tk1);
        Tk11 = findViewById(R.id.Tk11);
        Tk111 = findViewById(R.id.Tk111);
        bt_back = findViewById(R.id.bt_back);


        bt_back.setOnClickListener(v->{
            onBackPressed();
        });

        mediaPlayerK1 = MediaPlayer.create(this, R.raw.k1);
        mediaPlayerK2 = MediaPlayer.create(this, R.raw.k2);
        mediaPlayerK3 = MediaPlayer.create(this, R.raw.k3);
        mediaPlayerK4 = MediaPlayer.create(this, R.raw.k4);
        mediaPlayerK5 = MediaPlayer.create(this, R.raw.k5);
        mediaPlayerK6 = MediaPlayer.create(this, R.raw.k6);

        Ik1.setOnClickListener(v -> {
            if (isPlayingK1) {
                mediaPlayerK1.pause();
                Ik1.setImageResource(R.drawable.play);
                isPlayingK1 = false;
            } else {
                mediaPlayerK1.start();
                Ik1.setImageResource(R.drawable.posse);
                isPlayingK1 = true;
            }
        });

        IK11.setOnClickListener(v -> {
            // Agar Ik1 mein music chal raha hai, to use clear karein
            if (isPlayingK1) {
                mediaPlayerK1.stop();
                mediaPlayerK1.release();
                mediaPlayerK1 = MediaPlayer.create(this, R.raw.k1);
                isPlayingK1 = false;
            }
        });

        Ik111.setOnClickListener(v -> {
            // Ik111 par click karne par Tk1, Tk11, aur Tk111 mein jo bhi text hai, use share karein
            String textToShare = Tk01.getText().toString() + "\n\n"+Tk1.getText().toString() + "\n\n" + Tk11.getText().toString() + "\n\n" + Tk111.getText().toString();
            shareText(textToShare);
        });

        Ik2 = findViewById(R.id.Ik2);
        IK22 = findViewById(R.id.Ik22);
        Ik222 = findViewById(R.id.Ik222);
        Tk02 = findViewById(R.id.Tk02);
        Tk2 = findViewById(R.id.Tk2);
        Tk22 = findViewById(R.id.Tk22);
        Tk222 = findViewById(R.id.Tk222);

        Ik2.setOnClickListener(v -> {
            if (isPlayingK2) {
                mediaPlayerK2.pause();
                Ik2.setImageResource(R.drawable.play);
                isPlayingK2 = false;
            } else {
                mediaPlayerK2.start();
                Ik2.setImageResource(R.drawable.posse);
                isPlayingK2 = true;
            }
        });

        IK22.setOnClickListener(v -> {
            // Agar Ik2 mein music chal raha hai, to use clear karein
            if (isPlayingK2) {
                mediaPlayerK2.stop();
                mediaPlayerK2.release();
                mediaPlayerK2 = MediaPlayer.create(this, R.raw.k2);
                isPlayingK2 = false;
            }
        });

        Ik222.setOnClickListener(v -> {
            // Ik222 par click karne par Tk2, Tk22, aur Tk222 mein jo bhi text hai, use share karein
            String textToShare = Tk02.getText().toString() + "\n\n"+Tk2.getText().toString() + "\n\n" + Tk22.getText().toString() + "\n\n" + Tk222.getText().toString();
            shareText(textToShare);
        });

        Ik3 = findViewById(R.id.Ik3);
        IK33 = findViewById(R.id.Ik33);
        Ik333 = findViewById(R.id.Ik333);
        Tk03 = findViewById(R.id.Tk03);
        Tk3 = findViewById(R.id.Tk3);
        Tk33 = findViewById(R.id.Tk33);
        Tk333 = findViewById(R.id.Tk333);

        Ik3.setOnClickListener(v -> {
            if (isPlayingK3) {
                mediaPlayerK3.pause();
                Ik3.setImageResource(R.drawable.play);
                isPlayingK3 = false;
            } else {
                mediaPlayerK3.start();
                Ik3.setImageResource(R.drawable.posse);
                isPlayingK3 = true;
            }
        });

        IK33.setOnClickListener(v -> {
            // Agar Ik3 mein music chal raha hai, to use clear karein
            if (isPlayingK3) {
                mediaPlayerK3.stop();
                mediaPlayerK3.release();
                mediaPlayerK3 = MediaPlayer.create(this, R.raw.k3);
                isPlayingK3 = false;
            }
        });

        Ik333.setOnClickListener(v -> {
            // Ik333 par click karne par Tk3, Tk33, aur Tk333 mein jo bhi text hai, use share karein
            String textToShare = Tk03.getText().toString() + "\n\n"+Tk3.getText().toString() + "\n\n" + Tk33.getText().toString() + "\n\n" + Tk333.getText().toString();
            shareText(textToShare);
        });



        Ik4 = findViewById(R.id.Ik4);
        IK44 = findViewById(R.id.Ik44);
        Ik444 = findViewById(R.id.Ik444);
        Tk04 = findViewById(R.id.Tk04);
        Tk4 = findViewById(R.id.Tk4);
        Tk44 = findViewById(R.id.Tk44);
        Tk444 = findViewById(R.id.Tk444);

        Ik4.setOnClickListener(v -> {
            if (isPlayingK4) {
                mediaPlayerK4.pause();
                Ik4.setImageResource(R.drawable.play);
                isPlayingK4 = false;
            } else {
                mediaPlayerK4.start();
                Ik4.setImageResource(R.drawable.posse);
                isPlayingK4 = true;
            }
        });

        IK44.setOnClickListener(v -> {
            // Agar Ik4 mein music chal raha hai, to use clear karein
            if (isPlayingK4) {
                mediaPlayerK4.stop();
                mediaPlayerK4.release();
                mediaPlayerK4 = MediaPlayer.create(this, R.raw.k4);
                isPlayingK4 = false;
            }
        });

        Ik444.setOnClickListener(v -> {
            // Ik444 par click karne par Tk4, Tk44, aur Tk444 mein jo bhi text hai, use share karein
            String textToShare = Tk04.getText().toString() + "\n\n"+Tk4.getText().toString() + "\n\n" + Tk44.getText().toString() + "\n\n" + Tk444.getText().toString();
            shareText(textToShare);
        });


        Ik5 = findViewById(R.id.Ik5);
        IK55 = findViewById(R.id.Ik55);
        Ik555 = findViewById(R.id.Ik555);
        Tk05 = findViewById(R.id.Tk05);
        Tk5 = findViewById(R.id.Tk5);
        Tk55 = findViewById(R.id.Tk55);
        Tk555 = findViewById(R.id.Tk555);

        Ik5.setOnClickListener(v -> {
            if (isPlayingK5) {
                mediaPlayerK5.pause();
                Ik5.setImageResource(R.drawable.play);
                isPlayingK5 = false;
            } else {
                mediaPlayerK5.start();
                Ik5.setImageResource(R.drawable.posse);
                isPlayingK5 = true;
            }
        });

        IK55.setOnClickListener(v -> {
            // Agar Ik5 mein music chal raha hai, to use clear karein
            if (isPlayingK5) {
                mediaPlayerK5.stop();
                mediaPlayerK5.release();
                mediaPlayerK5 = MediaPlayer.create(this, R.raw.k5);
                isPlayingK5 = false;
            }
        });

        Ik555.setOnClickListener(v -> {
            // Ik555 par click karne par Tk5, Tk55, aur Tk555 mein jo bhi text hai, use share karein
            String textToShare = Tk05.getText().toString() + "\n\n"+Tk5.getText().toString() + "\n\n" + Tk55.getText().toString() + "\n\n" + Tk555.getText().toString();
            shareText(textToShare);
        });


        Ik6 = findViewById(R.id.Ik6);
        IK66 = findViewById(R.id.Ik66);
        Ik666 = findViewById(R.id.Ik666);
        Tk06 = findViewById(R.id.Tk06);
        Tk6 = findViewById(R.id.Tk6);
        Tk66 = findViewById(R.id.Tk66);
        Tk666 = findViewById(R.id.Tk666);

        Ik6.setOnClickListener(v -> {
            if (isPlayingK6) {
                mediaPlayerK6.pause();
                Ik6.setImageResource(R.drawable.play);
                isPlayingK6 = false;
            } else {
                mediaPlayerK6.start();
                Ik6.setImageResource(R.drawable.posse);
                isPlayingK6 = true;
            }
        });

        IK66.setOnClickListener(v -> {
            // Agar Ik6 mein music chal raha hai, to use clear karein
            if (isPlayingK6) {
                mediaPlayerK6.stop();
                mediaPlayerK6.release();
                mediaPlayerK6 = MediaPlayer.create(this, R.raw.k6);
                isPlayingK6 = false;
            }
        });

        Ik666.setOnClickListener(v -> {
            // Ik666 par click karne par Tk6, Tk66, aur Tk666 mein jo bhi text hai, use share karein
            String textToShare = Tk06.getText().toString() + "\n\n"+Tk6.getText().toString() + "\n\n" + Tk66.getText().toString() + "\n\n" + Tk666.getText().toString();
            shareText(textToShare);
        });

    }



    private void shareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "Share Text"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerK1 != null) {
            mediaPlayerK1.release();
            mediaPlayerK1 = null;
        }
        if (mediaPlayerK2 != null) {
            mediaPlayerK2.release();
            mediaPlayerK2 = null;
        }
        if (mediaPlayerK3 != null) {
            mediaPlayerK3.release();
            mediaPlayerK3 = null;
        }
        if (mediaPlayerK4 != null) {
            mediaPlayerK4.release();
            mediaPlayerK4 = null;
        }
        if (mediaPlayerK5 != null) {
            mediaPlayerK5.release();
            mediaPlayerK5 = null;
        }
        if (mediaPlayerK6 != null) {
            mediaPlayerK6.release();
            mediaPlayerK6 = null;
        }
    }
}
