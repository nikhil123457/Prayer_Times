package com.vitcode.iprayertimes.dateconverter;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.prayerTime.PrayTime;
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class Utils extends AppCompatActivity implements LocationListener {
    private static final int DAYS_UNTIL_PROMPT = 0;
    static final String EXTRA_MESSAGE = "message";
    public static final boolean IS_DEBUG = true;
    private static final int LAUNCHES_UNTIL_PROMPT = 5;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 5;
    private static final long MIN_TIME_BW_UPDATES = 30000;
    static int MODE_PRIVATE = 0;
    public static final boolean SHOULD_PRINT_LOG = true;
    public static String SharedPref_filename = "Prayer_pref";
    public static final String USER_COMPASS = "user_compass";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
//    private static GoogleAnalytics analytics;
    public static int startadInterval = 20000;
    public static int timer;
    public final String EXTRA_ACTIVITY = "activityFlag";
    public final String EXTRA_ACTIVITY_HOME = "activityHome";
    public String USER_CITY = "user_city";
    public String USER_COUNTRY = "user_country";
    public String USER_LAT = "user_lat";
    public String USER_LNG = "user_lng";
    public String USER_MLAT = "user_mlat";
    public String USER_MLNG = "user_mlng";
    public String USER_STATE = "user_state";
    public String USER_STREET = "user_street";
    public String USER_THEME = "user_theme";
    public final String UTILS_NOTIF = "notif";
    protected MenuItem action_qibla;
    protected int adInterval;
    protected int afont = 1;
    Intent alarmIntent_1;
    protected String ar_font = "1";
    boolean canGetLocation = false;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: protected */
    public int efont = 1;
    protected String en_font = "1";
    /* access modifiers changed from: protected */
    public Handler handler_ad = new Handler();
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    double latitude;
    Location location;
    protected LocationManager locationManager;
    double longitude;
    protected String notify = "0";
    String[] p_name_2 = {"Imsak", "Fajar", "Dhuhr", "Asr", "Maghrib", "Isha"};
    String[] p_time_2;
    private PendingIntent pendingIntent_1;
    public int pos = 0;
    SharedPreferences pref;
    public String pref_time = "0";
    public Runnable runnable_ad;
    int serverResponseCode = 0;
    SharedPreferences spref;
    public Typeface tf;
    public Typeface tf1;
    public Typeface tf2;
    protected Typeface tf3;
    public Typeface tf_arabic;

    public static void printException(Exception exception) {
        exception.printStackTrace();
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() == null || !manager.getActiveNetworkInfo().isAvailable() || !manager.getActiveNetworkInfo().isConnected()) {
            return false;
        }
        return true;
    }

    public void fullscreen() {
        getWindow().setFlags(1024, 1024);
    }

    public void typeface() {
        this.tf = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Thin.ttf");
        this.tf1 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        this.tf2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        this.tf3 = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.otf");
        this.tf_arabic = Typeface.createFromAsset(getAssets(), "fonts/Aceh-Darusalam.ttf");
    }


    public void font() {
//        this.alarmIntent_1 = new Intent(this, NotificationReceiver.class);
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.ar_font = this.pref.getString("arabicfont", "1");
        this.en_font = this.pref.getString("englishfont", "1");
        this.notify = this.pref.getString("notification", "0");
        if (this.en_font.equals("0")) {
            this.efont = 0;
        } else if (this.en_font.equals("1")) {
            this.efont = 1;
        } else if (this.en_font.equals("2")) {
            this.efont = 2;
        }
        if (this.ar_font.equals("0")) {
            this.afont = 0;
        } else if (this.ar_font.equals("1")) {
            this.afont = 1;
        } else if (this.ar_font.equals("2")) {
            this.afont = 2;
        }
        this.pref_time = this.pref.getString("timeformat", "0");
    }

//    public synchronized GoogleAnalytics getAnalytics() {
//        if (analytics == null) {
//            analytics = GoogleAnalytics.getInstance(this);
//        }
//        return analytics;
//    }

    public void loadlist() {
        new Thread(new Runnable() {
            public void run() {
            }
        }).start();
    }

    @SuppressLint("WrongViewCast")
    public void Actionbar(String title) {

//        SpannableString s = new SpannableString(title);
//        s.setSpan(new TypefaceSpan("MyTypeface.otf"), 0, s.length(), 33);
//        getSupportActionBar().setTitle((CharSequence) Html.fromHtml("<font color=\"#ffffff\">" + s + "</font>"));
//        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
    }



//    public void Analytics(String title) {
//        Tracker t = getAnalytics().newTracker((int) R.xml.global_tracker);
//        t.setScreenName(title);
//        t.send(new HitBuilders.ScreenViewBuilder().build());
//    }



    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            this.isGPSEnabled = this.locationManager.isProviderEnabled("gps");
            this.isNetworkEnabled = this.locationManager.isProviderEnabled("network");
            Log.d("Network", this.isNetworkEnabled + " Network " + this.isGPSEnabled);
            if (this.isNetworkEnabled) {
                this.canGetLocation = true;
                if (this.isNetworkEnabled) {
                    this.locationManager.requestLocationUpdates("network", MIN_TIME_BW_UPDATES, 5.0f, this);
                    if (this.locationManager != null) {
                        this.location = this.locationManager.getLastKnownLocation("network");
                        if (this.location != null) {
                            this.latitude = this.location.getLatitude();
                            this.longitude = this.location.getLongitude();
                        }
                    }
                }
            } else if (this.isGPSEnabled && this.location == null) {
                this.locationManager.requestLocationUpdates("gps", MIN_TIME_BW_UPDATES, 5.0f, this);
                Log.d("GPS Enabled", "GPS Enabled");
                if (this.locationManager != null) {
                    this.location = this.locationManager.getLastKnownLocation("gps");
                    if (this.location != null) {
                        this.latitude = this.location.getLatitude();
                        this.longitude = this.location.getLongitude();
                    }
                }
            }
            Log.d("Network", this.latitude + " Network " + this.longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.location;
    }


    public double getLatitude() {
        if (this.location != null) {
            this.latitude = this.location.getLatitude();
        }
        return this.latitude;
    }

    public double getLongitude() {
        if (this.location != null) {
            this.longitude = this.location.getLongitude();
        }
        return this.longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }



    public double getTimeZone(String selectedId) {
        int TimeZoneOffset = TimeZone.getTimeZone(selectedId).getRawOffset() / 60000;
        return Double.parseDouble((TimeZoneOffset / 60) + "." + (TimeZoneOffset % 60 == 30 ? 5 : 0));
    }

    public String getTime(int time) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse("June 18, 2015"));
        cal.add(5, time);
        return df.format(cal.getTime());
    }

    public String getTimeNow(int time) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String now_time = df.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(now_time));
        cal.add(5, time);
        return df.format(cal.getTime());
    }

    public void disableAllAlarm(Context context) {
        saveBoolean("alarm_fajar", false);
        saveBoolean("alarm_shorook", false);
        saveBoolean("alarm_zuheer", false);
        saveBoolean("alarm_asar", false);
        saveBoolean("alarm_maghrib", false);
        saveBoolean("alarm_isha", false);
    }



    public String changeTimeFormat(String time) throws ParseException {
        SimpleDateFormat displayFormat;
        SimpleDateFormat parseFormat;
        if (this.pref_time.equals("0")) {
            displayFormat = new SimpleDateFormat("HH:mm", Locale.US);
            parseFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        } else {
            displayFormat = new SimpleDateFormat("HH:mm", Locale.US);
            parseFormat = new SimpleDateFormat("hh:mm", Locale.US);
        }
        return displayFormat.format(parseFormat.parse(time));
    }

    public String addDayLight(String time, int daylight) throws ParseException {
        DateFormat df;
        if (this.pref_time.equals("0")) {
            df = new SimpleDateFormat("hh:mm a", Locale.US);
        } else {
            df = new SimpleDateFormat("HH:mm", Locale.US);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(time));
        cal.add(12, daylight);
        return df.format(cal.getTime());
    }

    public String addDay_Light(String time, int daylight) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(time));
        cal.add(12, daylight);
        return format.format(cal.getTime());
    }



    public String getTimeDate(int time) throws ParseException {
        DateFormat df = new SimpleDateFormat("hh:mm a MMMM d, yyyy", Locale.US);
        String now_time = df.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(now_time));
        cal.add(5, time);
        return df.format(cal.getTime());
    }

    public Calendar getTimeByCal(int time) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String now_time = df.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(now_time));
        cal.add(5, time);
        return cal;
    }

    public Calendar getCalender(int time) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String now_time = df.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(now_time));
        cal.add(5, time);
        return cal;
    }

    public boolean isPackageExists(String targetPackage) {
        for (ApplicationInfo packageInfo : getPackageManager().getInstalledApplications(0)) {
            if (packageInfo.packageName.equals(targetPackage)) {
                return true;
            }
        }
        return false;
    }

    public static double bearing(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLonRad = Math.toRadians(lon2 - lon1);
        return radToBearing(Math.atan2(Math.sin(deltaLonRad) * Math.cos(lat2Rad), (Math.cos(lat1Rad) * Math.sin(lat2Rad)) - ((Math.sin(lat1Rad) * Math.cos(lat2Rad)) * Math.cos(deltaLonRad))));
    }

    public static double radToBearing(double rad) {
        return (Math.toDegrees(rad) + 360.0d) % 360.0d;
    }

    public static int randInt(int min, int max) {
        return new Random(System.currentTimeMillis()).nextInt(20000) + 10000;
    }

    public void savePreferences(String param, int value) {
        SharedPreferences.Editor editor2 = getSharedPreferences("Prayer_pref", 0).edit();
        editor2.putInt(param, value);
        editor2.commit();
    }

    public void saveLong(String param, long value) {
        SharedPreferences.Editor editor2 = getSharedPreferences("Prayer_pref", 0).edit();
        editor2.putLong(param, value);
        editor2.commit();
    }

    public void saveDouble(String param, float value) {
        SharedPreferences.Editor editor2 = getSharedPreferences("Prayer_pref", 0).edit();
        editor2.putFloat(param, value);
        editor2.commit();
    }

    public void saveString(String param, String value) {
        SharedPreferences.Editor editor2 = getSharedPreferences("Prayer_pref", 0).edit();
        editor2.putString(param, value);
        editor2.commit();
    }

    public void saveBoolean(String param, boolean value) {
        SharedPreferences.Editor editor2 = getSharedPreferences("Prayer_pref", 0).edit();
        editor2.putBoolean(param, value);
        editor2.commit();
    }

    public int loadPreferences(String param) {
        return getSharedPreferences("Prayer_pref", 0).getInt(param, 0);
    }

    public Long loadLong(String param) {
        return Long.valueOf(getSharedPreferences("Prayer_pref", 0).getLong(param, 0));
    }

    public String loadString(String param) {
        return getSharedPreferences("Prayer_pref", 0).getString(param, "");
    }

    public String loadpref(String param) {
        return getSharedPreferences("Prayer_pref", 0).getString(param, "false");
    }

    public boolean loadBoolean(String param) {
        return getSharedPreferences("Prayer_pref", 0).getBoolean(param, false);
    }

    public void SavePref(String key, String value) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        this.editor = this.spref.edit();
        this.editor.putString(key, value);
        this.editor.commit();
    }

    public void SavePrefBool(String key, boolean value) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        this.editor = this.spref.edit();
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    public void SavePrefInt(String key, int value) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        this.editor = this.spref.edit();
        this.editor.putInt(key, value);
        this.editor.commit();
    }

    public String LoadPref(String key) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        return this.spref.getString(key, "");
    }

    public String LoadStringPref(String key) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        return this.spref.getString(key, "0");
    }

    public Boolean LoadPrefBool(String key) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        return Boolean.valueOf(this.spref.getBoolean(key, false));
    }

    public int LoadPrefInt(String key) {
        this.spref = getSharedPreferences(SharedPref_filename, MODE_PRIVATE);
        return this.spref.getInt(key, 0);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        String document_id2 = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        Cursor cursor2 = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[]) null, "_id = ? ", new String[]{document_id2}, (String) null);
        cursor2.moveToFirst();
        @SuppressLint("Range") String path = cursor2.getString(cursor2.getColumnIndex("_data"));
        cursor2.close();
        return path;
    }

    public static String getRealPathFromURI(Uri uri, Activity act) {
        if (uri == null || !"content".equals(uri.getScheme())) {
            return uri.getPath();
        }
        Cursor cursor = act.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        cursor.moveToFirst();
        String filePath = cursor.getString(0);
        cursor.close();
        return filePath;
    }

    public String uploadImage(String urlPath, String filePath) {
        String urlresult = null;
        if (!new File(filePath).isFile()) {
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                HttpURLConnection conn = (HttpURLConnection) new URL(urlPath).openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod( "POST");
                conn.setRequestProperty("Connection","Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "*****");
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                try {
                    dos.writeBytes("--" + "*****" + "\r\n");
                    dos.writeBytes("Content-Disposition: form-data; name=\"userfile\";filename=\"" + filePath + "\"" + "\r\n");
                    dos.writeBytes("\r\n");
                    int bufferSize = Math.min(fileInputStream.available(), 1048576);
                    byte[] buffer = new byte[bufferSize];
                    int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bufferSize = Math.min(fileInputStream.available(), 1048576);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }
                    dos.writeBytes("\r\n");
                    dos.writeBytes("--" + "*****" + "--" + "\r\n");
                    this.serverResponseCode = conn.getResponseCode();
                    if (this.serverResponseCode == 200) {
                    }
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while (true) {
                        String inputLine = in.readLine();
                        if (inputLine == null) {
                            break;
                        }
                        urlresult = inputLine;
                    }
                    fileInputStream.close();
                    dos.flush();
                    dos.close();
                    DataOutputStream dataOutputStream = dos;
                } catch (MalformedURLException e) {
                    DataOutputStream dataOutputStream2 = dos;
                    e.printStackTrace();
                    return urlresult;
                } catch (Exception e2) {
                    DataOutputStream dataOutputStream3 = dos;
                    e2.printStackTrace();
                    return urlresult;
                }
            } catch (MalformedURLException e3) {
                e3.printStackTrace();
                return urlresult;
            } catch (Exception e4) {
                e4.printStackTrace();
                return urlresult;
            }
        }
        return urlresult;
    }

    public void onLocationChanged(Location location2) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }

    /* access modifiers changed from: protected */
//    public void onStart() {
//        GoogleAnalytics.getInstance(this).reportActivityStart(this);
//        super.onStart();
//    }
//
//    /* access modifiers changed from: protected */
//    public void onStop() {
//        GoogleAnalytics.getInstance(this).reportActivityStop(this);
//        super.onStop();
//    }

    public void cancel() {
        if (this.p_time_2.length > 0) {
            for (int j = 0; j < this.p_time_2.length; j++) {
                this.pendingIntent_1 = PendingIntent.getBroadcast(this, j, this.alarmIntent_1,  PendingIntent.FLAG_IMMUTABLE);
                ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).cancel(this.pendingIntent_1);
            }
        }
    }

    public void initPrayerTimes(int time) throws ParseException {
        double timezone;
        if (!loadString(this.USER_LAT).equals("0")) {
            double latitude2 = Double.parseDouble(loadString(this.USER_LAT));
            double longitude2 = Double.parseDouble(loadString(this.USER_LNG));
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
            ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal, latitude2, longitude2, timezone);
            ArrayList<String> timeNames = prayers.getTimeNames();
            if (latitude2 != 0.0d && longitude2 != 0.0d) {
                int parseInt = Integer.parseInt(this.pref.getString("daylight", "0"));
                for (int i = 0; i < prayerTimes.size(); i++) {
                    this.p_time_2 = new String[]{addDayLight(prayerTimes.get(0), Integer.parseInt(this.pref.getString(this.p_name_2[0] + "daylight", "0")) - 15), addDayLight(prayerTimes.get(0), Integer.parseInt(this.pref.getString(this.p_name_2[1] + "daylight", "0"))), addDayLight(prayerTimes.get(2), Integer.parseInt(this.pref.getString(this.p_name_2[2] + "daylight", "0"))), addDayLight(prayerTimes.get(3), Integer.parseInt(this.pref.getString(this.p_name_2[3] + "daylight", "0")) - 66), addDayLight(prayerTimes.get(5), Integer.parseInt(this.pref.getString(this.p_name_2[4] + "daylight", "0"))), addDayLight(prayerTimes.get(6), Integer.parseInt(this.pref.getString(this.p_name_2[5] + "daylight", "0")))};
                }
                for (int j = 0; j < this.p_time_2.length; j++) {
                    this.alarmIntent_1.putExtra("type", this.p_name_2[j]);
                    this.alarmIntent_1.putExtra("time", this.p_time_2[j]);
                    this.alarmIntent_1.putExtra("id", j);
                    set_alarms(j, this.p_time_2[j], this.p_name_2[j]);
                }
            }
        }
    }

    public void set_alarms(int id, String stime, String name) {
        int adjust_time;
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        String now_date = df.format(new Date());
        int hour = 0;
        int minute = 0;
        try {
            String timenow = changeTimeFormat(stime);
            hour = Integer.parseInt(timenow.substring(0, 2));
            minute = Integer.parseInt(timenow.substring(3, 5));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(now_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(11, hour);
        if (id == 0) {
            adjust_time = Integer.parseInt(this.pref.getString(this.p_name_2[0] + "daylight", "0")) - 15;
        } else if (id == 3) {
            adjust_time = Integer.parseInt(this.pref.getString(this.p_name_2[3] + "daylight", "0")) - 66;
        } else {
            adjust_time = Integer.parseInt(this.pref.getString(this.p_name_2[id] + "daylight", "0"));
        }
        calendar.set(12, minute + adjust_time);
        Date now = new Date();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(now);
        if (!cal_now.after(calendar)) {
            this.pendingIntent_1 = PendingIntent.getBroadcast(this, id, this.alarmIntent_1,  PendingIntent.FLAG_IMMUTABLE);
            ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), this.pendingIntent_1);
        }
    }
}
