package com.vitcode.iprayertimes.compass.view;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;

import com.vitcode.iprayertimes.Mosques.LocationSave;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.compass.helper.CompassUtils;
import com.vitcode.iprayertimes.compass.helper.LocationMyMaker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMaker extends LocationMyMaker {
    private Activity context;
    private SensorEventListener mSensorEventListener;
    private SensorManager mSensorManager;
    private GoogleMap map;
    private Marker marker;
    private MarkerOptions myMaker;

    public MyMaker(Activity activity) {
        MarkerOptions markerOptions = new MarkerOptions();
        this.myMaker = markerOptions;
        this.context = activity;
        markerOptions.flat(true);
        this.myMaker.title("Your Location");
        this.myMaker.position(new LatLng(LocationSave.getLat(), LocationSave.getLon()));
        this.myMaker.anchor(0.5f, 0.5f);
        this.myMaker.icon(CompassUtils.getBitmapFromVectorDrawable(this.context, R.drawable.ic_navigation));
    }

    public void updateRotationMaker(Float f) {
        Marker marker2;
        if (this.map != null && (marker2 = this.marker) != null) {
            marker2.setRotation(f.floatValue());
        }
    }

    public void updateLocation(Location location) {
        GoogleMap googleMap = this.map;
        if (googleMap != null) {
            Marker marker2 = this.marker;
            if (marker2 != null) {
                marker2.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            } else {
                this.marker = googleMap.addMarker(this.myMaker);
            }
        }
    }

    public void enableSensor(GoogleMap googleMap) {
        this.map = googleMap;
        this.mSensorManager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
        SensorEventListener r4 = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public void onSensorChanged(SensorEvent sensorEvent) {
                MyMaker.this.updateRotationMaker(Float.valueOf(sensorEvent.values[0]));
            }
        };
        this.mSensorEventListener = r4;
        SensorManager sensorManager = this.mSensorManager;
        sensorManager.registerListener(r4, sensorManager.getDefaultSensor(3), 1);
    }

    public void removeSensor() {
        this.mSensorManager.unregisterListener(this.mSensorEventListener);
    }
}
