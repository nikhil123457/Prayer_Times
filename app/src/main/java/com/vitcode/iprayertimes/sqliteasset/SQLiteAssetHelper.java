package com.vitcode.iprayertimes.sqliteasset;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.ZipInputStream;

public class SQLiteAssetHelper extends SQLiteOpenHelper {
    private static final String ASSET_DB_PATH = "databases";
    private static final String TAG = "SQLiteAssetHelper";
    private String mAssetPath;
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private String mDatabasePath;
    private final SQLiteDatabase.CursorFactory mFactory;
    private int mForcedUpgradeVersion;
    private boolean mIsInitializing;
    private final String mName;
    private final int mNewVersion;
    private String mUpgradePathFormat;

    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public SQLiteAssetHelper(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.mDatabase = null;
        this.mIsInitializing = false;
        this.mForcedUpgradeVersion = 0;
        if (i < 1) {
            throw new IllegalArgumentException("Version must be >= 1, was " + i);
        } else if (str != null) {
            this.mContext = context;
            this.mName = str;
            this.mFactory = cursorFactory;
            this.mNewVersion = i;
            this.mAssetPath = "databases/" + str;
            if (str2 != null) {
                this.mDatabasePath = str2;
            } else {
                this.mDatabasePath = context.getApplicationInfo().dataDir + "/databases";
            }
            this.mUpgradePathFormat = "databases/" + str + "_upgrade_%s-%s.sql";
        } else {
            throw new IllegalArgumentException("Database name cannot be null");
        }
    }

    public SQLiteAssetHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(context, str, (String) null, cursorFactory, i);
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen() && !this.mDatabase.isReadOnly()) {
            return this.mDatabase;
        } else if (this.mIsInitializing) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        } else {
            this.mIsInitializing = true;
            SQLiteDatabase createOrOpenDatabase = null;
            try {
                createOrOpenDatabase = createOrOpenDatabase(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int version = createOrOpenDatabase.getVersion();
            if (version != 0 && version < this.mForcedUpgradeVersion) {
                try {
                    createOrOpenDatabase = createOrOpenDatabase(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                createOrOpenDatabase.setVersion(this.mNewVersion);
                version = createOrOpenDatabase.getVersion();
            }
            if (version != this.mNewVersion) {
                createOrOpenDatabase.beginTransaction();
                if (version == 0) {
                    onCreate(createOrOpenDatabase);
                } else {
                    if (version > this.mNewVersion) {
                        String str = TAG;
                        Log.w(str, "Can't downgrade read-only database from version " + version + " to " + this.mNewVersion + ": " + createOrOpenDatabase.getPath());
                    }
                    onUpgrade(createOrOpenDatabase, version, this.mNewVersion);
                }
                createOrOpenDatabase.setVersion(this.mNewVersion);
                createOrOpenDatabase.setTransactionSuccessful();
                createOrOpenDatabase.endTransaction();
            }
            onOpen(createOrOpenDatabase);
            this.mIsInitializing = false;
            SQLiteDatabase sQLiteDatabase2 = this.mDatabase;
            if (sQLiteDatabase2 != null) {
                try {
                    sQLiteDatabase2.close();
                } catch (Exception unused) {
                }
            }
            this.mDatabase = createOrOpenDatabase;
            return createOrOpenDatabase;
        }
    }



    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase = null;
        SQLiteDatabase sQLiteDatabase2 = this.mDatabase;
        if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
            return this.mDatabase;
        } else if (!this.mIsInitializing) {
            try {
                return getWritableDatabase();
            } catch (SQLiteException e) {
                if (this.mName != null) {
                    String str = TAG;
                    Log.e(str, "Couldn't open " + this.mName + " for writing (will try read-only):", e);
                    sQLiteDatabase = null;
                    this.mIsInitializing = true;
                    String path = this.mContext.getDatabasePath(this.mName).getPath();
                    sQLiteDatabase = SQLiteDatabase.openDatabase(path, this.mFactory, SQLiteDatabase.OPEN_READONLY);
                    if (sQLiteDatabase.getVersion() == this.mNewVersion) {
                        onOpen(sQLiteDatabase);
                        Log.w(str, "Opened " + this.mName + " in read-only mode");
                        this.mDatabase = sQLiteDatabase;
                        this.mIsInitializing = false;
                        return sQLiteDatabase;
                    }
                    throw new SQLiteException("Can't upgrade read-only database from version " + sQLiteDatabase.getVersion() + " to " + this.mNewVersion + ": " + path);
                }
                throw e;
            } catch (Throwable th) {
                this.mIsInitializing = false;
                if (!(sQLiteDatabase == null || sQLiteDatabase == this.mDatabase)) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }
    }

    public synchronized void close() {
        if (!this.mIsInitializing) {
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                this.mDatabase.close();
                this.mDatabase = null;
            }
        } else {
            throw new IllegalStateException("Closed during initialization");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = TAG;
        Log.w(str, "Upgrading database " + this.mName + " from version " + i + " to " + i2 + "...");
        ArrayList arrayList = new ArrayList();
        getUpgradeFilePaths(i, i2 + -1, i2, arrayList);
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList, new VersionComparator());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                try {
                    String str3 = TAG;
                    Log.w(str3, "processing upgrade: " + str2);
                    String convertStreamToString = Utils.convertStreamToString(this.mContext.getAssets().open(str2));
                    if (convertStreamToString != null) {
                        for (String next : Utils.splitSqlScript(convertStreamToString, ';')) {
                            if (next.trim().length() > 0) {
                                sQLiteDatabase.execSQL(next);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String str4 = TAG;
            Log.w(str4, "Successfully upgraded database " + this.mName + " from version " + i + " to " + i2);
            return;
        }
        Log.e(str, "no upgrade script path from " + i + " to " + i2);
        throw new SQLiteAssetException("no upgrade script path from " + i + " to " + i2);
    }

    @Deprecated
    public void setForcedUpgradeVersion(int i) {
        setForcedUpgrade(i);
    }

    public void setForcedUpgrade(int i) {
        this.mForcedUpgradeVersion = i;
    }

    public void setForcedUpgrade() {
        setForcedUpgrade(this.mNewVersion);
    }

    private SQLiteDatabase createOrOpenDatabase(boolean z) throws SQLiteAssetException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDatabasePath);
        sb.append("/");
        sb.append(this.mName);
        SQLiteDatabase returnDatabase = new File(sb.toString()).exists() ? returnDatabase() : null;
        if (returnDatabase == null) {
            copyDatabaseFromAssets();
            return returnDatabase();
        } else if (!z) {
            return returnDatabase;
        } else {
            Log.w(TAG, "forcing database upgrade!");
            copyDatabaseFromAssets();
            return returnDatabase();
        }
    }

    private SQLiteDatabase returnDatabase() {
        try {
            SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(this.mDatabasePath + "/" + this.mName, this.mFactory, 0);
            String str = TAG;
            Log.i(str, "successfully opened database " + this.mName);
            return openDatabase;
        } catch (SQLiteException e) {
            String str2 = TAG;
            Log.w(str2, "could not open database " + this.mName + " - " + e.getMessage());
            return null;
        }
    }

    private void copyDatabaseFromAssets() throws SQLiteAssetException, IOException {
        InputStream open;
        Log.w(TAG, "copying database from assets...");
        String str = this.mAssetPath;
        String str2 = this.mDatabasePath + "/" + this.mName;
        boolean z = false;
        try {
            try {
                open = this.mContext.getAssets().open(str);
            } catch (IOException unused) {
                open = this.mContext.getAssets().open(str + ".zip");
                z = true;
            }
        } catch (IOException e) {
            SQLiteAssetException sQLiteAssetException = new SQLiteAssetException("Missing " + this.mAssetPath + " file (or .zip, .gz archive) in assets, or target folder not writable");
            sQLiteAssetException.setStackTrace(e.getStackTrace());
            throw sQLiteAssetException;
        }
        try {
            File file = new File(this.mDatabasePath + "/");
            if (!file.exists()) {
                file.mkdir();
            }
            if (z) {
                ZipInputStream fileFromZip = Utils.getFileFromZip(open);
                if (fileFromZip == null) {
                    throw new SQLiteAssetException("Archive is missing a SQLite database file");
                }
                Utils.writeExtractedFileToDisk(fileFromZip, new FileOutputStream(str2));
            } else {
                Utils.writeExtractedFileToDisk(open, new FileOutputStream(str2));
            }
            Log.w(TAG, "database copy complete");
        } catch (IOException e2) {
            SQLiteAssetException sQLiteAssetException2 = new SQLiteAssetException("Unable to write " + str2 + " to data directory");
            sQLiteAssetException2.setStackTrace(e2.getStackTrace());
            throw sQLiteAssetException2;
        }
    }


    private InputStream getUpgradeSQLStream(int i, int i2) {
        String format = String.format(this.mUpgradePathFormat, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        try {
            return this.mContext.getAssets().open(format);
        } catch (IOException unused) {
            String str = TAG;
            Log.w(str, "missing database upgrade script: " + format);
            return null;
        }
    }

    private void getUpgradeFilePaths(int i, int i2, int i3, ArrayList<String> arrayList) {
        int i4;
        if (getUpgradeSQLStream(i2, i3) != null) {
            arrayList.add(String.format(this.mUpgradePathFormat, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
            i4 = i2 - 1;
        } else {
            int i5 = i3;
            i4 = i2 - 1;
            i2 = i5;
        }
        if (i4 >= i) {
            getUpgradeFilePaths(i, i4, i2, arrayList);
        }
    }

    public static class SQLiteAssetException extends SQLiteException {
        public SQLiteAssetException() {
        }

        public SQLiteAssetException(String str) {
            super(str);
        }
    }
}
