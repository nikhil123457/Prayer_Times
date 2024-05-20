package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.vitcode.iprayertimes.Mosques.LocationSave;
import com.vitcode.iprayertimes.R;
//import com.nakatomizork.islamprayer.compass.adapter.CompassAdapter;
import com.vitcode.iprayertimes.compass.helper.AddressHelper;
import com.vitcode.iprayertimes.compass.helper.Compass;
import com.vitcode.iprayertimes.compass.helper.CompassUtils;
import com.vitcode.iprayertimes.compass.view.CalibrateCompassDialog;
import com.vitcode.iprayertimes.compass.view.MyMaker;
import com.vitcode.iprayertimes.permission_utils.Func;
import com.vitcode.iprayertimes.permission_utils.PermissionUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.TimeZone;

public class Qqibla extends Fragment implements OnMapReadyCallback {
    private Compass compass;
    private float currentAzimuth;
    private ImageView imgCompass;
    private ImageView imgCompassK;
    private PermissionUtil.PermissionRequestObject mRequestObject;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;
    private LocationRequest mLocationRequest;
    private GoogleMap mMap;
    private MapView mMapView;
    private MyMaker myMaker;
    private final LatLng qiblaLocation = new LatLng(21.42251d, 39.82616d);
    private double result;
    private TextView tvDistance;
    private ImageView bt_back;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qqibla, container, false);
        initLocation();
        initMapView(savedInstanceState, view);
        initView(view);
        setupCompass();
        initBottomCompass();
        return view;
    }

    @SuppressLint("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            this.mMap = googleMap;
            MyMaker myMaker2 = new MyMaker(getActivity());
            this.myMaker = myMaker2;
            myMaker2.enableSensor(this.mMap);
            this.mMap.setMyLocationEnabled(false);
            this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
            initQibla();
            mRequestObject = PermissionUtil.with((AppCompatActivity) getActivity()).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").onAllGranted(new Func() {
                public void call() {
                    Qqibla.this.getDeviceLocation(false);
                    Qqibla.this.listenLocationChange();
                }
            }).ask(12);
        }
    }

    private void initQibla() {
        this.mMap.addMarker(new MarkerOptions().position(this.qiblaLocation).anchor(0.5f, 0.5f).icon(CompassUtils.getBitmapFromVectorDrawable(getActivity(), R.drawable.ic_mosques)));
    }

    public void zoomPoints(LatLng latLng, LatLng latLng2) {
        try {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(latLng);
            builder.include(latLng2);
            LatLngBounds build = builder.build();
            int i = getResources().getDisplayMetrics().widthPixels;
            int i2 = getResources().getDisplayMetrics().heightPixels;
            double min = (double) Math.min(i, i2);
            Double.isNaN(min);
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(build, i, i2, (int) (min * 0.4d)));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void initMapView(Bundle bundle, View view) {
        MapView mapView = view.findViewById(R.id.map);
        this.mMapView = mapView;
        mapView.onCreate(bundle);
        this.mMapView.getMapAsync(this);
    }

    private void initLocation() {
        Location location = new Location("dummyprovider");
        this.mLocation = location;
        location.setLatitude(LocationSave.getLat());
        this.mLocation.setLongitude(LocationSave.getLon());
    }

    private void setupCompass() {
        Compass compass2 = new Compass(getActivity());
        this.compass = compass2;
        compass2.setListener(new Compass.CompassListener() {
            public void onNewAzimuth(float f) {
                Qqibla.this.adjustGambarDial(f);
                Qqibla.this.adjustArrowQiblat(f);
            }

            public void onAccuracyChanged(String str) {
                new CalibrateCompassDialog(getActivity(), str).show();
            }
        });
    }

    public void adjustGambarDial(float f) {
        RotateAnimation rotateAnimation = new

                RotateAnimation(-this.currentAzimuth, -f, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = f;
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setFillAfter(true);
        this.imgCompass.startAnimation(rotateAnimation);
    }

    public void adjustArrowQiblat(float f) {
        double d = (double) (-this.currentAzimuth);
        double d2 = this.result;
        Double.isNaN(d);
        RotateAnimation rotateAnimation = new RotateAnimation((float) (d + d2), -f, 1, 0.5f, 1, 0.5f);
        this.currentAzimuth = f;
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setFillAfter(true);
        this.imgCompassK.startAnimation(rotateAnimation);
        if (this.result > 0.0d) {
            this.imgCompassK.setVisibility(View.VISIBLE);
        } else {
            this.imgCompassK.setVisibility(View.INVISIBLE);
            this.imgCompassK.setVisibility(View.GONE);
        }
    }

    public void initBottomCompass() {
        // Assuming you have inflated the fragment's layout in onCreateView() and stored it in a variable named rootView
        View rootView = getView(); // If called after onCreateView(), getView() should return the inflated view

//        if (rootView != null) {
//            RecyclerView recyclerView = rootView.findViewById(R.id.rcv_compass);
////            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
////            recyclerView.setAdapter(new CompassAdapter(getActivity()) {
//                @Override
//                public void OnItemClick(int i, int i2) {
//                    CompassManager.setCompass(i);
//                    CompassManager.setCompassK(i2);
//                    imgCompass.setImageResource(i); // No need to use Qqibla.this here, as you are inside an anonymous inner class
//                    imgCompassK.setImageResource(i2); // Similarly, no need to use Qqibla.this here
//                }
//            });
//        } else {
//            // Handle case where rootView is null (fragment's layout not inflated yet)
//            Log.e("QqiblaFragment", "Root view is null");
//        }
    }


    private void initView(View view) {
        this.imgCompass = view.findViewById(R.id.compass);
        this.imgCompassK = view.findViewById(R.id.compass_k);
        this.bt_back = view.findViewById(R.id.bt_back);
        this.tvDistance = view.findViewById(R.id.tv_distance);
    }

    public void myLocationClick(final Boolean bool) {
        mRequestObject = PermissionUtil.with((AppCompatActivity) getActivity()).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").onAllGranted(new Func() {
            public void call() {
                Qqibla.this.getDeviceLocation(bool);
            }
        }).ask(12);
    }

    @SuppressLint("MissingPermission")
    public void getDeviceLocation(final Boolean bool) {
        try {
            LocationServices.getFusedLocationProviderClient(getActivity()).getLastLocation().addOnCompleteListener((Activity) getActivity(), (OnCompleteListener) task -> {
                if (task.isSuccessful() && task.getResult() != null && LocationSave.isAutoUpdate()) {
                    Qqibla.this.mLocation = (Location) task.getResult();
                    LocationSave.putLocation(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                    LocationSave.setTimeZone(String.valueOf(((TimeZone.getDefault().getOffset(new Date().getTime()) / 1000) / 60) / 60));
                    AddressHelper.getAddress(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                }
                Qqibla.this.fetchGps();
                Qqibla.this.myMaker.updateLocation(Qqibla.this.mLocation);
                LatLng latLng = new LatLng(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                Qqibla.this.addPolyline(latLng, Qqibla.this.qiblaLocation);
                if (bool) {
                    Qqibla.this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude()), 16.0f));
                } else {
                    Qqibla.this.zoomPoints(new LatLng(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude()), Qqibla.this.qiblaLocation);
                }

            });
        } catch (Exception e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void addPolyline(LatLng latLng, LatLng qiblaLocation) {
    }

    @SuppressLint("MissingPermission")
    public void listenLocationChange() {
        LocationRequest locationRequest = new LocationRequest();
        this.mLocationRequest = locationRequest;
        locationRequest.setInterval(120000);
        this.mLocationRequest.setFastestInterval(120000);
        this.mLocationRequest.setPriority(102);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        this.mFusedLocationClient = fusedLocationProviderClient;
        fusedLocationProviderClient.requestLocationUpdates(this.mLocationRequest, new LocationCallback() {
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult.getLastLocation() != null && LocationSave.isAutoUpdate()) {
                    Qqibla.this.mLocation = locationResult.getLastLocation();
                    AddressHelper.getAddress(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                }
                LocationSave.putLocation(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                LocationSave.setTimeZone(String.valueOf(((TimeZone.getDefault().getOffset(new Date().getTime()) / 1000) / 60) / 60));
                LatLng latLng = new LatLng(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude());
                Qqibla.this.fetchGps();
                Qqibla.this.myMaker.updateLocation(Qqibla.this.mLocation);
                Qqibla.this.zoomPoints(latLng, Qqibla.this.qiblaLocation);
                Qqibla.this.addPolyline(new LatLng(Qqibla.this.mLocation.getLatitude(), Qqibla.this.mLocation.getLongitude()), Qqibla.this.qiblaLocation);
            }
        }, Looper.myLooper());
    }

    public void fetchGps() {
        double radians = Math.toRadians(21.42251d);
        double radians2 = Math.toRadians(this.mLocation.getLatitude());
        double radians3 = Math.toRadians(39.82616d - this.mLocation.getLongitude());
        this.result = (Math.toDegrees(Math.atan2(Math.sin(radians3) * Math.cos(radians), (Math.cos(radians2) * Math.sin(radians)) - ((Math.sin(radians2) * Math.cos(radians)) * Math.cos(radians3)))) + 360.0d) % 360.0d;
        double atan2 = Math.atan2(1.0d, 1.0d) * 4.0d;
        double d = atan2 / 180.0d;
        double d2 = 180.0d / atan2;
        double latitude = this.mLocation.getLatitude() * d;
        double d3 = 21.42251d * d;
        double acos = Math.acos(Math.cos(latitude - d3) - (((1.0d - Math.cos((this.mLocation.getLongitude() * d) - (d * 39.82616d))) * Math.cos(latitude)) * Math.cos(d3))) * 60.0d * 1.852d * d2;
        double d4 = acos * 1000.0d;
        String[] split = String.valueOf(acos).split("\\.");
        String str = split[0] + "." + split[1].substring(0, 2);
        if (d4 < 1000.0d) {
            this.tvDistance.setText("You are within the range of Qibla");
        } else {
            this.tvDistance.setText("Distance to Kaaba " + str + " KM");
        }
    }

    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
        Compass compass2 = this.compass;
        if (compass2 != null) {
            compass2.start(getActivity());
        }
    }

    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
        Compass compass2 = this.compass;
        if (compass2 != null) {
            compass2.stop();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
        MyMaker myMaker2 = this.myMaker;
        if (myMaker2 != null) {
            myMaker2.removeSensor();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mMapView.onLowMemory();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mMapView.onSaveInstanceState(bundle);
    }
}