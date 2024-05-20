package com.vitcode.iprayertimes.compass.helper;

import android.os.IBinder;
import android.os.Parcel;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.zzh;

public class LocationMyMaker extends AbstractSafeParcelable {
    public static final Creator<MarkerOptions> CREATOR = new zzh();
    private float alpha = 1.0f;
    private LatLng position;
    private float zzcr;
    private boolean zzcs = true;
    private float zzda = 0.5f;
    private float zzdb = 1.0f;
    private String zzdm;
    private String zzdn;
    private BitmapDescriptor zzdo;
    private boolean zzdp;
    private boolean zzdq = false;
    private float zzdr = 0.0f;
    private float zzds = 0.5f;
    private float zzdt = 0.0f;

    public LocationMyMaker() {
    }

    LocationMyMaker(LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6, float f7) {
        this.position = latLng;
        this.zzdm = str;
        this.zzdn = str2;
        if (iBinder == null) {
            this.zzdo = null;
        } else {
            this.zzdo = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        }
        this.zzda = f;
        this.zzdb = f2;
        this.zzdp = z;
        this.zzcs = z2;
        this.zzdq = z3;
        this.zzdr = f3;
        this.zzds = f4;
        this.zzdt = f5;
        this.alpha = f6;
        this.zzcr = f7;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPosition(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeString(parcel, 4, getSnippet(), false);
        BitmapDescriptor bitmapDescriptor = this.zzdo;
        if (bitmapDescriptor == null) {
            iBinder = null;
        } else {
            iBinder = bitmapDescriptor.zza().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, iBinder, false);
        SafeParcelWriter.writeFloat(parcel, 6, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 8, isDraggable());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 10, isFlat());
        SafeParcelWriter.writeFloat(parcel, 11, getRotation());
        SafeParcelWriter.writeFloat(parcel, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(parcel, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(parcel, 14, getAlpha());
        SafeParcelWriter.writeFloat(parcel, 15, getZIndex());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final LocationMyMaker position(LatLng latLng) {
        if (latLng != null) {
            this.position = latLng;
            return this;
        }
        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    public final LocationMyMaker zIndex(float f) {
        this.zzcr = f;
        return this;
    }

    public final LocationMyMaker icon(BitmapDescriptor bitmapDescriptor) {
        this.zzdo = bitmapDescriptor;
        return this;
    }

    public final LocationMyMaker anchor(float f, float f2) {
        this.zzda = f;
        this.zzdb = f2;
        return this;
    }

    public final LocationMyMaker infoWindowAnchor(float f, float f2) {
        this.zzds = f;
        this.zzdt = f2;
        return this;
    }

    public final LocationMyMaker title(String str) {
        this.zzdm = str;
        return this;
    }

    public final LocationMyMaker snippet(String str) {
        this.zzdn = str;
        return this;
    }

    public final LocationMyMaker draggable(boolean z) {
        this.zzdp = z;
        return this;
    }

    public final LocationMyMaker visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final LocationMyMaker flat(boolean z) {
        this.zzdq = z;
        return this;
    }

    public final LocationMyMaker rotation(float f) {
        this.zzdr = f;
        return this;
    }

    public final LocationMyMaker alpha(float f) {
        this.alpha = f;
        return this;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final String getTitle() {
        return this.zzdm;
    }

    public final String getSnippet() {
        return this.zzdn;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzdo;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final boolean isDraggable() {
        return this.zzdp;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final boolean isFlat() {
        return this.zzdq;
    }

    public final float getRotation() {
        return this.zzdr;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzds;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzdt;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getZIndex() {
        return this.zzcr;
    }
}
