package com.vitcode.iprayertimes.prayerTime;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class AppLocationService extends Service implements LocationListener {
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 120000;
    String bestProvider;
    Location location;
    protected LocationManager locationManager;

    public AppLocationService() {
    }

    public AppLocationService(Context context) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        this.bestProvider = this.locationManager.getBestProvider(criteria, false);
        if (this.bestProvider == null) {
            return null;
        }
        this.location = this.locationManager.getLastKnownLocation(this.bestProvider);
        this.locationManager.requestLocationUpdates(this.bestProvider, MIN_TIME_FOR_UPDATE, 10.0f, this);
        return this.location;
    }

    public void stopUsingGPS() {
        if (this.locationManager != null) {
            this.locationManager.removeUpdates(this);
        }
    }

    public void onLocationChanged(Location location2) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
