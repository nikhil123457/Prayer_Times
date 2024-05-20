package com.vitcode.iprayertimes.permission_utils;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class    PermissionUtil {
    public static PermissionObject with(AppCompatActivity appCompatActivity) {
        return new PermissionObject(appCompatActivity);
    }

    public static PermissionObject with(Fragment fragment) {
        return new PermissionObject(fragment);
    }

    public static class PermissionObject {
        private AppCompatActivity mActivity;
        private Fragment mFragment;

        PermissionObject(AppCompatActivity appCompatActivity) {
            this.mActivity = appCompatActivity;
        }

        PermissionObject(Fragment fragment) {
            this.mFragment = fragment;
        }

        public boolean has(String str) {
            int i;
            AppCompatActivity appCompatActivity = this.mActivity;
            if (appCompatActivity != null) {
                i = ContextCompat.checkSelfPermission(appCompatActivity, str);
            } else {
                i = ContextCompat.checkSelfPermission(this.mFragment.getContext(), str);
            }
            return i == 0;
        }

        public PermissionRequestObject request(String str) {
            if (this.mActivity != null) {
                return new PermissionRequestObject(this.mActivity, new String[]{str});
            }
            return new PermissionRequestObject(this.mFragment, new String[]{str});
        }

        public PermissionRequestObject request(String... strArr) {
            if (this.mActivity != null) {
                return new PermissionRequestObject(this.mActivity, strArr);
            }
            return new PermissionRequestObject(this.mFragment, strArr);
        }
    }

    public static class PermissionRequestObject {
        private static final String TAG = PermissionObject.class.getSimpleName();
        private AppCompatActivity mActivity;
        private Func mDenyFunc;
        private Fragment mFragment;
        private Func mGrantFunc;
        private String[] mPermissionNames;
        private ArrayList<SinglePermission> mPermissionsWeDontHave;
        private Func3 mRationalFunc;
        private int mRequestCode;
        private Func2 mResultFunc;

        public PermissionRequestObject(AppCompatActivity appCompatActivity, String[] strArr) {
            this.mActivity = appCompatActivity;
            this.mPermissionNames = strArr;
        }

        public PermissionRequestObject(Fragment fragment, String[] strArr) {
            this.mFragment = fragment;
            this.mPermissionNames = strArr;
        }

        public PermissionRequestObject ask(int i) {
            this.mRequestCode = i;
            this.mPermissionsWeDontHave = new ArrayList<>(this.mPermissionNames.length);
            for (String singlePermission : this.mPermissionNames) {
                this.mPermissionsWeDontHave.add(new SinglePermission(singlePermission));
            }
            if (needToAsk()) {
                Log.i(TAG, "Asking for permission");
                AppCompatActivity appCompatActivity = this.mActivity;
                if (appCompatActivity != null) {
                    ActivityCompat.requestPermissions(appCompatActivity, this.mPermissionNames, i);
                } else {
                    this.mFragment.requestPermissions(this.mPermissionNames, i);
                }
            } else {
                Log.i(TAG, "No need to ask for permission");
                Func func = this.mGrantFunc;
                if (func != null) {
                    func.call();
                }
            }
            return this;
        }

        private boolean needToAsk() {
            int i;
            boolean z;
            ArrayList<SinglePermission> arrayList = new ArrayList<>(this.mPermissionsWeDontHave);
            for (int i2 = 0; i2 < this.mPermissionsWeDontHave.size(); i2++) {
                SinglePermission singlePermission = this.mPermissionsWeDontHave.get(i2);
                AppCompatActivity appCompatActivity = this.mActivity;
                if (appCompatActivity != null) {
                    i = ContextCompat.checkSelfPermission(appCompatActivity, singlePermission.getPermissionName());
                } else {
                    i = ContextCompat.checkSelfPermission(this.mFragment.getContext(), singlePermission.getPermissionName());
                }
                if (i == 0) {
                    arrayList.remove(singlePermission);
                } else {
                    AppCompatActivity appCompatActivity2 = this.mActivity;
                    if (appCompatActivity2 != null) {
                        z = ActivityCompat.shouldShowRequestPermissionRationale(appCompatActivity2, singlePermission.getPermissionName());
                    } else {
                        z = this.mFragment.shouldShowRequestPermissionRationale(singlePermission.getPermissionName());
                    }
                    if (z) {
                        singlePermission.setRationalNeeded(true);
                    }
                }
            }
            this.mPermissionsWeDontHave = arrayList;
            this.mPermissionNames = new String[arrayList.size()];
            for (int i3 = 0; i3 < this.mPermissionsWeDontHave.size(); i3++) {
                this.mPermissionNames[i3] = this.mPermissionsWeDontHave.get(i3).getPermissionName();
            }
            if (this.mPermissionsWeDontHave.size() != 0) {
                return true;
            }
            return false;
        }

        public PermissionRequestObject onRational(Func3 func3) {
            this.mRationalFunc = func3;
            return this;
        }

        public PermissionRequestObject onAllGranted(Func func) {
            this.mGrantFunc = func;
            return this;
        }

        public PermissionRequestObject onAnyDenied(Func func) {
            this.mDenyFunc = func;
            return this;
        }

        public PermissionRequestObject onResult(Func2 func2) {
            this.mResultFunc = func2;
            return this;
        }

        public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (this.mRequestCode != i) {
                return;
            }
            if (this.mResultFunc != null) {
                Log.i(TAG, "Calling Results Func");
                this.mResultFunc.call(i, strArr, iArr);
                return;
            }
            int i2 = 0;
            while (i2 < strArr.length) {
                if (iArr[i2] != -1) {
                    i2++;
                } else if (this.mPermissionsWeDontHave.get(i2).isRationalNeeded() && this.mRationalFunc != null) {
                    Log.i(TAG, "Calling Rational Func");
                    this.mRationalFunc.call(this.mPermissionsWeDontHave.get(i2).getPermissionName());
                    return;
                } else if (this.mDenyFunc != null) {
                    Log.i(TAG, "Calling Deny Func");
                    this.mDenyFunc.call();
                    return;
                } else {
                    Log.e(TAG, "NUll DENY FUNCTIONS");
                    return;
                }
            }
            if (this.mGrantFunc != null) {
                Log.i(TAG, "Calling Grant Func");
                this.mGrantFunc.call();
                return;
            }
            Log.e(TAG, "NUll GRANT FUNCTIONS");
        }
    }
}
