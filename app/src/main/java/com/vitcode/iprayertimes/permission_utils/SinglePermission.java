package com.vitcode.iprayertimes.permission_utils;

public class SinglePermission {
    private String mPermissionName;
    private boolean mRationalNeeded = false;
    private String mReason;

    public SinglePermission(String str) {
        this.mPermissionName = str;
    }

    public SinglePermission(String str, String str2) {
        this.mPermissionName = str;
        this.mReason = str2;
    }

    public boolean isRationalNeeded() {
        return this.mRationalNeeded;
    }

    public void setRationalNeeded(boolean z) {
        this.mRationalNeeded = z;
    }

    public String getReason() {
        String str = this.mReason;
        return str == null ? "" : str;
    }

    public void setReason(String str) {
        this.mReason = str;
    }

    public String getPermissionName() {
        return this.mPermissionName;
    }

    public void setPermissionName(String str) {
        this.mPermissionName = str;
    }
}
