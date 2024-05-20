package com.vitcode.iprayertimes.compass.helper;

import android.location.Address;
import android.location.Geocoder;

import com.vitcode.iprayertimes.splash.App;
import com.vitcode.iprayertimes.Mosques.LocationSave;

import java.util.List;
import java.util.Locale;

public class AddressHelper {
    public static void getAddress(final double d, final double d2) {
        new Thread() {
            public void run() {
                super.run();
                try {
                    List<Address> fromLocation = new Geocoder(App.get(), Locale.getDefault()).getFromLocation(d, d2, 1);
                    if (fromLocation.size() > 0) {
                        LocationSave.setCity(fromLocation.get(0).getLocality());
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }.start();
    }
}
