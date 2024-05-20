package com.vitcode.iprayertimes.downloadmanager;

import android.os.Handler;


import com.vitcode.iprayertimes.downloadmanager.util.Log;

import java.security.InvalidParameterException;

public class ThinDownloadManager implements DownloadManager {
    private DownloadRequestQueue mRequestQueue;

    public ThinDownloadManager() {
        this(true);
    }

    public ThinDownloadManager(boolean z) {
        DownloadRequestQueue downloadRequestQueue = new DownloadRequestQueue();
        this.mRequestQueue = downloadRequestQueue;
        downloadRequestQueue.start();
        setLoggingEnabled(z);
    }

    public ThinDownloadManager(Handler handler) throws InvalidParameterException {
        DownloadRequestQueue downloadRequestQueue = new DownloadRequestQueue(handler);
        this.mRequestQueue = downloadRequestQueue;
        downloadRequestQueue.start();
        setLoggingEnabled(true);
    }

    public ThinDownloadManager(int i) {
        DownloadRequestQueue downloadRequestQueue = new DownloadRequestQueue(i);
        this.mRequestQueue = downloadRequestQueue;
        downloadRequestQueue.start();
        setLoggingEnabled(true);
    }

    public int add(DownloadRequest downloadRequest) throws IllegalArgumentException {
        checkReleased("add(...) called on a released ThinDownloadManager.");
        if (downloadRequest != null) {
            return this.mRequestQueue.add(downloadRequest);
        }
        throw new IllegalArgumentException("DownloadRequest cannot be null");
    }

    public int cancel(int i) {
        checkReleased("cancel(...) called on a released ThinDownloadManager.");
        return this.mRequestQueue.cancel(i);
    }

    public void cancelAll() {
        checkReleased("cancelAll() called on a released ThinDownloadManager.");
        this.mRequestQueue.cancelAll();
    }

    public int pause(int i) {
        checkReleased("pause(...) called on a released ThinDownloadManager.");
        return this.mRequestQueue.pause(i);
    }

    public void pauseAll() {
        checkReleased("pauseAll() called on a released ThinDownloadManager.");
        this.mRequestQueue.pauseAll();
    }

    public int query(int i) {
        checkReleased("query(...) called on a released ThinDownloadManager.");
        return this.mRequestQueue.query(i);
    }

    public void release() {
        if (!isReleased()) {
            this.mRequestQueue.release();
            this.mRequestQueue = null;
        }
    }

    public boolean isReleased() {
        return this.mRequestQueue == null;
    }

    private void checkReleased(String str) {
        if (isReleased()) {
            throw new IllegalStateException(str);
        }
    }

    private static void setLoggingEnabled(boolean z) {
        Log.setEnabled(z);
    }
}
