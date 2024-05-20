package com.vitcode.iprayertimes.prayerTime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.dateconverter.Utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityAlarm extends Utils {
    Intent alarmIntent;
    SharedPreferences.Editor editor;
    int id = 0;
    public MediaPlayer mMediaPlayer;
    private PendingIntent pendingIntent;
    SharedPreferences pref;
    ImageView stopAlarm;
    int[] tones_array = {R.raw.azan0, R.raw.azan1, R.raw.azan2, R.raw.azan3, R.raw.azan4, R.raw.azan5, R.raw.azan6};
    TextView txt_date;
    TextView txt_location;
    TextView txt_name;
    TextView txt_type;
    String type_nawaz;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_alarm);
        Actionbar(getString(R.string.app_name));
//        Analytics(getString(R.string.app_name));
        typeface();
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        Bundle extras = getIntent().getExtras();
        this.txt_date = (TextView) findViewById(R.id.txt_date);
        this.txt_date.setTypeface(this.tf1);
        try {
            this.txt_date.setText(getTimeDate(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.alarmIntent = new Intent(this, AlarmReceiver.class);
        this.txt_type = (TextView) findViewById(R.id.txt_type);
        this.txt_type.setTypeface(this.tf1);
        this.txt_location = (TextView) findViewById(R.id.txt_location);
        this.txt_location.setTypeface(this.tf1);
        String city = loadString(this.USER_CITY);
        String country = loadString(this.USER_COUNTRY);
        this.txt_location.setText(getString(R.string.lblcitycountry, new Object[]{city, country}));
        if (extras != null) {
            this.type_nawaz = extras.getString("type");
            String time = extras.getString("time");
            if (!(time == null || this.type_nawaz == null)) {
                this.txt_type.setText(time + ", " + this.type_nawaz);
                SavePref(this.type_nawaz, "fals");
//                generateNotification(this, time, this.type_nawaz);
            }
        }
        this.stopAlarm = (ImageView) findViewById(R.id.stopAlarm);
        this.stopAlarm.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                ActivityAlarm.this.mMediaPlayer.stop();
                ActivityAlarm.this.finish();
                return false;
            }
        });
        String alarms = this.pref.getString("ringtone", "content://settings/system/alarm_alert");
        if (alarms.equals("content://settings/system/alarm_alert")) {
            playSound(this);
        } else {
            playSoundAlarm(this, getPrefAlarmUri());
        }
    }



//    private static void generateNotification(Context context, String time, String type) {
//        String title = context.getString(R.string.app_name);
//        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher)).setAutoCancel(false).setContentTitle(title).setContentText(time + "," + type).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, ActivityAlarm.class), PendingIntent.FLAG_CANCEL_CURRENT)).build());
//    }

    private void playSound(Context context) {
        this.mMediaPlayer = new MediaPlayer();
        String selected_azan = this.pref.getString("azan", "0");
        this.mMediaPlayer = MediaPlayer.create(this, this.tones_array[Integer.parseInt(selected_azan)]);
        if (((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(3) != 0) {
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.start();
        }
    }

    private void playSoundAlarm(Context context, Uri alert) {
        this.mMediaPlayer = new MediaPlayer();
        try {
            this.mMediaPlayer.setDataSource(context, alert);
            if (((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(4) != 0) {
                this.mMediaPlayer.setAudioStreamType(4);
                this.mMediaPlayer.prepare();
                this.mMediaPlayer.start();
            }
        } catch (IOException e) {
            System.out.println("OOPS");
        }
    }

    private Uri getPrefAlarmUri() {
        return Uri.parse(PreferenceManager.getDefaultSharedPreferences(this).getString("ringtone", "content://settings/system/alarm_alert"));
    }

    public void onBackPressed() {
        this.mMediaPlayer.stop();
        finish();
        super.onBackPressed();
    }

    public void set_alarm(String time) {
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy");
        String now_date = df.format(new Date());
        int hour = 0;
        int minute = 0;
        try {
            String timenow = changeTimeFormat(time);
            hour = Integer.parseInt(timenow.substring(0, 2));
            minute = Integer.parseInt(timenow.substring(3, 5));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        this.pendingIntent = PendingIntent.getBroadcast(this, this.id, this.alarmIntent,  PendingIntent.FLAG_IMMUTABLE);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(now_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(5, 1);
        calendar.set(11, hour);
        calendar.set(12, minute + Integer.parseInt(this.pref.getString("adjust_alarm", "-5")));
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), this.pendingIntent);
    }

    public void initPrayerTime(int time) throws ParseException {
        double timezone;
        double latitude = Double.parseDouble(loadString(this.USER_LAT));
        double longitude = Double.parseDouble(loadString(this.USER_LNG));
        String timeZone = this.pref.getString("timezone", "");
        PrayTime prayers = new PrayTime();
        prayers.setTimeFormat(prayers.Time12);
        prayers.setCalcMethod(Integer.parseInt(this.pref.getString("method", "1")));
        prayers.setAsrJuristic(Integer.parseInt(this.pref.getString("juristic", "1")));
        prayers.setAdjustHighLats(Integer.parseInt(this.pref.getString("higherLatitudes", "1")));
        prayers.tune(new int[]{0, 0, 0, 0, 0, 0, 0});
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(5, time);
        if (timeZone.equals("")) {
            timezone = getTimeZone(cal.getTimeZone().getID().toString());
            this.editor = this.pref.edit();
            this.editor.putString("timezone", timeZone);
            this.editor.commit();
        } else {
            timezone = getTimeZone(timeZone);
        }
        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal, latitude, longitude, timezone);
        ArrayList<String> prayerNames = prayers.getTimeNames();
        if (latitude != 0.0d && longitude != 0.0d) {
            int dayLightTime = Integer.parseInt(this.pref.getString("daylight", "0"));
            for (int i = 0; i < prayerTimes.size(); i++) {
                if (prayerNames.get(i).equals("Fajr")) {
                    this.id = 0;
                    if (this.type_nawaz.equals("Fajar")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                } else if (prayerNames.get(i).equals("Sunrise")) {
                    this.id = 1;
                    if (this.type_nawaz.equals("Shorook")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                } else if (prayerNames.get(i).equals("Dhuhr")) {
                    this.id = 2;
                    if (this.type_nawaz.equals("Zuher")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                } else if (prayerNames.get(i).equals("Asr")) {
                    this.id = 3;
                    if (this.type_nawaz.equals("Asar")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                } else if (prayerNames.get(i).equals("Maghrib")) {
                    this.id = 4;
                    if (this.type_nawaz.equals("Maghrib")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                } else if (prayerNames.get(i).equals("Isha")) {
                    this.id = 5;
                    if (this.type_nawaz.equals("Isha")) {
                        set_alarm(addDayLight(prayerTimes.get(i), dayLightTime));
                    }
                }
            }
        }
    }
}
