package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.vitcode.iprayertimes.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class PrayerTimesFragment extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private AlertDialog locationDialog;
    TextView startOneTextView, endOneTextView, namajTXT, namajTwoTXT, startTwo, EndTwo, LoctaionTXT;
    private Timer timer;
    private Handler handler;

    ImageView vo1, vo2, vo3, vo4, vo5;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prayer_times, container, false);


        // Initialize TextViews
        startOneTextView = rootView.findViewById(R.id.startOne);
        namajTwoTXT = rootView.findViewById(R.id.namajTwoTXT);
        startTwo = rootView.findViewById(R.id.startTwo);
        EndTwo = rootView.findViewById(R.id.EndTwo);
        startOneTextView = rootView.findViewById(R.id.startOne);
        endOneTextView = rootView.findViewById(R.id.EndOne);
        namajTXT = rootView.findViewById(R.id.namajTXT);
        LoctaionTXT = rootView.findViewById(R.id.LoctaionTXT);
        vo1 = rootView.findViewById(R.id.vo1);
        vo2 = rootView.findViewById(R.id.vo2);
        vo3 = rootView.findViewById(R.id.vo3);
        vo4 = rootView.findViewById(R.id.vo4);
        vo5 = rootView.findViewById(R.id.vo5);

        vo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImage(vo1);
            }
        });

        vo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImage(vo2);
            }
        });
        vo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImage(vo3);
            }
        });
        vo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImage(vo4);
            }
        });
        vo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImage(vo5);
            }
        });

        // Check if location is enabled
        if (!isLocationEnabled()) {
            // Prompt user to enable location
            showEnableLocationDialog();
        } else {

            requestLocationPermission();
        }

        // Get current time and set Namaz timings
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentTime = timeFormat.format(calendar.getTime());
        setNamazTimings(currentTime);

        setupTimer();
        return rootView;
    }


    private void toggleImage(ImageView imageView) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String tag = (String) imageView.getTag();
        if (tag == null || tag.equals("close")) {
            // If the tag is null or close, change it to voice
            imageView.setTag("voice");
            imageView.setImageResource(R.drawable.voice);
            // Save the change to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("voiceEnabled", true);
            editor.apply(); // Use apply() to commit the changes asynchronously
        } else {

            imageView.setTag("close");
            imageView.setImageResource(R.drawable.cancel);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("voiceEnabled", false);
            editor.apply();
        }
    }

    private void setupTimer() {
        handler = new Handler(Looper.getMainLooper());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Get current time and update Namaz timings
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String currentTime = timeFormat.format(calendar.getTime());
                        setNamazTimings(currentTime);
                    }
                });
            }
        }, 0, 60000); // Update timings every minute
    }

    private void setNamazTimings(String currentTime) {
        // Convert current time to minutes for comparison
        int currentMinutes = convertToMinutes(currentTime);

        // Set Namaz timings based on current time
        if (currentMinutes >= convertToMinutes("04:57") && currentMinutes < convertToMinutes("12:42")) {
            namajTXT.setText("Fajr");
            startOneTextView.setText("04:57");
            endOneTextView.setText("End time - 12:42");

            namajTwoTXT.setText("Duhur");
            startTwo.setText("12:42");
            EndTwo.setText("End time - 16:07");

        } else if (currentMinutes >= convertToMinutes("12:42") && currentMinutes < convertToMinutes("16:07")) {
            namajTXT.setText("Duhur");
            startOneTextView.setText("12:42");
            endOneTextView.setText("End time - 16:07");

            namajTwoTXT.setText("Asr");
            startTwo.setText("16:07");
            EndTwo.setText("End time - 19:08");

        } else if (currentMinutes >= convertToMinutes("16:07") && currentMinutes < convertToMinutes("19:08")) {
            namajTXT.setText("Asr");
            startOneTextView.setText("16:07");
            endOneTextView.setText("End time - 19:08");

            namajTwoTXT.setText("Maghrib");
            startTwo.setText("19:08");
            EndTwo.setText("End time - 20:27");

        } else if (currentMinutes >= convertToMinutes("19:08") && currentMinutes < convertToMinutes("20:27")) {
            namajTXT.setText("Maghrib");
            startOneTextView.setText("19:08");
            endOneTextView.setText("End time - 20:27");

            namajTwoTXT.setText("isha");
            startTwo.setText("20:27");
            EndTwo.setText("End time - 04:57");

        } else {
            namajTXT.setText("isha");
            startOneTextView.setText("20:27");
            endOneTextView.setText("End time - 04:57");

            namajTwoTXT.setText("Fajr");
            startTwo.setText("04:57");
            EndTwo.setText("End time - 12:42");
        }
    }

    private int convertToMinutes(String time) {
        String[] timeSplit = time.split(":");
        int hours = Integer.parseInt(timeSplit[0]);
        int minutes = Integer.parseInt(timeSplit[1]);
        return hours * 60 + minutes;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void showEnableLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Please enable location to use this feature").setCancelable(false).setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Open location settings screen
                enableLocation();
            }
        });
        locationDialog = builder.create();
        locationDialog.show();

        locationDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isLocationEnabled()) {
                    // Location is still not enabled, show dialog again
                    showEnableLocationDialog();
                }
            }
        });
    }

    private void enableLocation() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            } else {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        }
    }

    private void getUserLocation() {
        // Check if location permission is granted
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            // Get last known location
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                // Get latitude and longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Get state and country name using Geocoder
                Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String stateName = address.getAdminArea();
                        String countryName = address.getCountryName();

                        // Print state and country name in TextView
                        String locationText = stateName + ", " + countryName;
                        LoctaionTXT.setText(locationText);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Cancel the timer when the fragment is destroyed
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
}