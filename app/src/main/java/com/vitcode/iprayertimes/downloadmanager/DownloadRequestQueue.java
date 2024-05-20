package com.vitcode.iprayertimes.downloadmanager;

import android.os.Handler;
import android.os.Looper;


import com.vitcode.iprayertimes.downloadmanager.util.Log;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadRequestQueue {
    private Set<DownloadRequest> mCurrentRequests = new HashSet();
    private CallBackDelivery mDelivery;
    private DownloadDispatcher[] mDownloadDispatchers;
    private PriorityBlockingQueue<DownloadRequest> mDownloadQueue = new PriorityBlockingQueue<>();
    private AtomicInteger mSequenceGenerator = new AtomicInteger();

    class CallBackDelivery {
        private final Executor mCallBackExecutor;

        public CallBackDelivery(final Handler handler) {
            this.mCallBackExecutor = runnable -> handler.post(runnable);
        }

        public void postDownloadComplete(final DownloadRequest downloadRequest) {
            this.mCallBackExecutor.execute(new Runnable() {
                public void run() {
                    if (downloadRequest.getDownloadListener() != null) {
                        downloadRequest.getDownloadListener().onDownloadComplete(downloadRequest.getDownloadId());
                    }
                    if (downloadRequest.getStatusListener() != null) {
                        downloadRequest.getStatusListener().onDownloadComplete(downloadRequest);
                    }
                }
            });
        }

        public void postDownloadFailed(final DownloadRequest downloadRequest, final int i, final String str) {
            this.mCallBackExecutor.execute(new Runnable() {
                public void run() {
                    if (downloadRequest.getDownloadListener() != null) {
                        downloadRequest.getDownloadListener().onDownloadFailed(downloadRequest.getDownloadId(), i, str);
                    }
                    if (downloadRequest.getStatusListener() != null) {
                        downloadRequest.getStatusListener().onDownloadFailed(downloadRequest, i, str);
                    }
                }
            });
        }

        public void postProgressUpdate(DownloadRequest downloadRequest, long j, long j2, int i) {
            final DownloadRequest downloadRequest2 = downloadRequest;
            final long j3 = j;
            final long j4 = j2;
            final int i2 = i;
            this.mCallBackExecutor.execute(new Runnable() {
                public void run() {
                    if (downloadRequest2.getDownloadListener() != null) {
                        downloadRequest2.getDownloadListener().onProgress(downloadRequest2.getDownloadId(), j3, j4, i2);
                    }
                    if (downloadRequest2.getStatusListener() != null) {
                        downloadRequest2.getStatusListener().onProgress(downloadRequest2, j3, j4, i2);
                    }
                }
            });
        }
    }

    public DownloadRequestQueue() {
        initialize(new Handler(Looper.getMainLooper()));
    }

    public DownloadRequestQueue(int i) {
        initialize(new Handler(Looper.getMainLooper()), i);
    }

    public DownloadRequestQueue(Handler handler) throws InvalidParameterException {
        if (handler != null) {
            initialize(handler);
            return;
        }
        throw new InvalidParameterException("callbackHandler must not be null");
    }

    public void start() {
        stop();
        for (int i = 0; i < this.mDownloadDispatchers.length; i++) {
            DownloadDispatcher downloadDispatcher = new DownloadDispatcher(this.mDownloadQueue, this.mDelivery);
            this.mDownloadDispatchers[i] = downloadDispatcher;
            downloadDispatcher.start();
        }
    }

    /* access modifiers changed from: package-private */
    public int add(DownloadRequest downloadRequest) {
        int downloadId = getDownloadId();
        downloadRequest.setDownloadRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(downloadRequest);
        }
        downloadRequest.setDownloadId(downloadId);
        this.mDownloadQueue.add(downloadRequest);
        return downloadId;
    }

    /* access modifiers changed from: package-private */
    public int query(int i) {
        synchronized (this.mCurrentRequests) {
            for (DownloadRequest next : this.mCurrentRequests) {
                if (next.getDownloadId() == i) {
                    int downloadState = next.getDownloadState();
                    return downloadState;
                }
            }
            return 64;
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelAll() {
        synchronized (this.mCurrentRequests) {
            for (DownloadRequest cancel : this.mCurrentRequests) {
                cancel.cancel();
            }
            this.mCurrentRequests.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public int cancel(int i) {
        synchronized (this.mCurrentRequests) {
            for (DownloadRequest next : this.mCurrentRequests) {
                if (next.getDownloadId() == i) {
                    next.cancel();
                    return 1;
                }
            }
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public int pause(int i) {
        checkResumableDownloadEnabled(i);
        return cancel(i);
    }

    /* access modifiers changed from: package-private */
    public void pauseAll() {
        checkResumableDownloadEnabled(-1);
        cancelAll();
    }

    private void checkResumableDownloadEnabled(int i) {
        synchronized (this.mCurrentRequests) {
            for (DownloadRequest next : this.mCurrentRequests) {
                if (i == -1 && !next.isResumable()) {

                    Log.e("ThinDownloadManager", String.format(Locale.getDefault(), "This request has not enabled resume feature hence request will be cancelled. Request Id: %d", new Object[]{Integer.valueOf(next.getDownloadId())}));
                } else if (next.getDownloadId() != i) {
                    continue;
                } else if (!next.isResumable()) {
                    throw new IllegalStateException("You cannot pause the download, unless you have enabled Resume feature in DownloadRequest.");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void finish(DownloadRequest downloadRequest) {
        Set<DownloadRequest> set = this.mCurrentRequests;
        if (set != null) {
            synchronized (set) {
                this.mCurrentRequests.remove(downloadRequest);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        Set<DownloadRequest> set = this.mCurrentRequests;
        if (set != null) {
            synchronized (set) {
                this.mCurrentRequests.clear();
                this.mCurrentRequests = null;
            }
        }
        if (this.mDownloadQueue != null) {
            this.mDownloadQueue = null;
        }
        if (this.mDownloadDispatchers != null) {
            stop();
            int i = 0;
            while (true) {
                DownloadDispatcher[] downloadDispatcherArr = this.mDownloadDispatchers;
                if (i < downloadDispatcherArr.length) {
                    downloadDispatcherArr[i] = null;
                    i++;
                } else {
                    this.mDownloadDispatchers = null;
                    return;
                }
            }
        }
    }

    private void initialize(Handler handler) {
        this.mDownloadDispatchers = new DownloadDispatcher[Runtime.getRuntime().availableProcessors()];
        this.mDelivery = new CallBackDelivery(handler);
    }

    private void initialize(Handler handler, int i) {
        this.mDownloadDispatchers = new DownloadDispatcher[i];
        this.mDelivery = new CallBackDelivery(handler);
    }

    private void stop() {
        int i = 0;
        while (true) {
            DownloadDispatcher[] downloadDispatcherArr = this.mDownloadDispatchers;
            if (i < downloadDispatcherArr.length) {
                if (downloadDispatcherArr[i] != null) {
                    downloadDispatcherArr[i].quit();
                }
                i++;
            } else {
                return;
            }
        }
    }

    private int getDownloadId() {
        return this.mSequenceGenerator.incrementAndGet();
    }
}
