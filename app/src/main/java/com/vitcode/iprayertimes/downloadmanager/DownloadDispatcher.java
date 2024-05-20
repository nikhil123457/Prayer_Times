package com.vitcode.iprayertimes.downloadmanager;


import com.vitcode.iprayertimes.downloadmanager.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

class DownloadDispatcher extends Thread {
    private final int BUFFER_SIZE = 4096;
    private final int HTTP_REQUESTED_RANGE_NOT_SATISFIABLE = 316;
    private final int HTTP_TEMP_REDIRECT = 307;
    private final int MAX_REDIRECTS = 5;
    private long mContentLength;
    private DownloadRequestQueue.CallBackDelivery mDelivery;
    private long mDownloadedCacheSize = 0;
    private final BlockingQueue<DownloadRequest> mQueue;
    private volatile boolean mQuit = false;
    private int mRedirectionCount = 0;
    private Timer mTimer;
    private boolean shouldAllowRedirects = true;

    DownloadDispatcher(BlockingQueue<DownloadRequest> blockingQueue, DownloadRequestQueue.CallBackDelivery callBackDelivery) {
        this.mQueue = blockingQueue;
        this.mDelivery = callBackDelivery;
    }

    public void run() {
        /*
            r3 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            java.util.Timer r0 = new java.util.Timer
            r0.<init>()
            r3.mTimer = r0
        L_0x000c:
            r0 = 0
            java.util.concurrent.BlockingQueue<DownloadRequest> r1 = r3.mQueue     // Catch:{ InterruptedException -> 0x0045 }
            java.lang.Object r1 = r1.take()     // Catch:{ InterruptedException -> 0x0045 }
            DownloadRequest r1 = (DownloadRequest) r1     // Catch:{ InterruptedException -> 0x0045 }
            r0 = 0
            r3.mRedirectionCount = r0     // Catch:{ InterruptedException -> 0x0043 }
            r0 = 1
            r3.shouldAllowRedirects = r0     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0043 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.String r2 = "Download initiated for "
            r0.append(r2)     // Catch:{ InterruptedException -> 0x0043 }
            int r2 = r1.getDownloadId()     // Catch:{ InterruptedException -> 0x0043 }
            r0.append(r2)     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x0043 }
            util.Log.v(r0)     // Catch:{ InterruptedException -> 0x0043 }
            r0 = 2
            r3.updateDownloadState(r1, r0)     // Catch:{ InterruptedException -> 0x0043 }
            android.net.Uri r0 = r1.getUri()     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x0043 }
            r3.executeDownload(r1, r0)     // Catch:{ InterruptedException -> 0x0043 }
            goto L_0x000c
        L_0x0043:
            r0 = r1
            goto L_0x0046
        L_0x0045:
        L_0x0046:
            boolean r1 = r3.mQuit
            if (r1 == 0) goto L_0x000c
            if (r0 == 0) goto L_0x005e
            r0.finish()
            int r1 = r0.getDownloadState()
            r2 = 16
            if (r1 == r2) goto L_0x005e
            r1 = 1008(0x3f0, float:1.413E-42)
            java.lang.String r2 = "Download cancelled"
            r3.updateDownloadFailed(r0, r1, r2)
        L_0x005e:
            java.util.Timer r0 = r3.mTimer
            r0.cancel()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: DownloadDispatcher.run():void");
    }
    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    public void executeDownload(DownloadRequest r7, String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "ContentValues"
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x01f0 }
            r1.<init>(r8)     // Catch:{ MalformedURLException -> 0x01f0 }
            r8 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ SocketTimeoutException -> 0x01d9, ConnectTimeoutException -> 0x01cd, IOException -> 0x01bd, all -> 0x01bb }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ SocketTimeoutException -> 0x01d9, ConnectTimeoutException -> 0x01cd, IOException -> 0x01bd, all -> 0x01bb }
            java.io.File r8 = new java.io.File     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            android.net.Uri r2 = r7.getDestinationURI()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = r2.getPath()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            boolean r2 = r8.exists()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r2 == 0) goto L_0x0029
            long r2 = r8.length()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r8 = (int) r2     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r2 = (long) r8     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.mDownloadedCacheSize = r2     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
        L_0x0029:
            java.lang.String r8 = "Range"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r3 = "bytes="
            r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r3 = r6.mDownloadedCacheSize     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r3 = "-"
            r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r1.setRequestProperty(r8, r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = "Existing file mDownloadedCacheSize: "
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r2 = r6.mDownloadedCacheSize     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = r8.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            util.Log.d((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8 = 0
            r1.setInstanceFollowRedirects(r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            RetryPolicy r2 = r7.getRetryPolicy()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r2 = r2.getCurrentTimeout()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r1.setConnectTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            RetryPolicy r2 = r7.getRetryPolicy()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r2 = r2.getCurrentTimeout()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r1.setReadTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.util.HashMap r2 = r7.getCustomHeaders()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r2 == 0) goto L_0x009a
            java.util.Set r3 = r2.keySet()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
        L_0x0084:
            boolean r4 = r3.hasNext()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r4 == 0) goto L_0x009a
            java.lang.Object r4 = r3.next()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.Object r5 = r2.get(r4)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r1.addRequestProperty(r4, r5)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x0084
        L_0x009a:
            r2 = 4
            r6.updateDownloadState(r7, r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r2 = r1.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r3.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r4 = "Response code obtained for downloaded Id "
            r3.append(r4)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r4 = r7.getDownloadId()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r3.append(r4)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r4 = " : httpResponse Code "
            r3.append(r4)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r3.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            util.Log.v(r3)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x015e
            r3 = 206(0xce, float:2.89E-43)
            if (r2 == r3) goto L_0x015e
            r8 = 307(0x133, float:4.3E-43)
            if (r2 == r8) goto L_0x0118
            r8 = 416(0x1a0, float:5.83E-43)
            if (r2 == r8) goto L_0x0110
            r8 = 500(0x1f4, float:7.0E-43)
            if (r2 == r8) goto L_0x0108
            r8 = 503(0x1f7, float:7.05E-43)
            if (r2 == r8) goto L_0x0100
            switch(r2) {
                case 301: goto L_0x0118;
                case 302: goto L_0x0118;
                case 303: goto L_0x0118;
                default: goto L_0x00dd;
            }     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
        L_0x00dd:
            r8 = 1002(0x3ea, float:1.404E-42)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r3 = "Unhandled HTTP response:"
            r0.append(r3)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r0.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = " message:"
            r0.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = r1.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r0.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r0 = r0.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x015a
        L_0x0100:
            java.lang.String r0 = r1.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x015a
        L_0x0108:
            java.lang.String r0 = r1.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x015a
        L_0x0110:
            java.lang.String r0 = r1.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x015a
        L_0x0118:
            int r8 = r6.mRedirectionCount     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r2 = 5
            if (r8 >= r2) goto L_0x0147
            boolean r3 = r6.shouldAllowRedirects     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r3 == 0) goto L_0x0147
            int r8 = r8 + 1
            r6.mRedirectionCount = r8     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = "Redirect for downloaded Id "
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r2 = r7.getDownloadId()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = r8.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            util.Log.v((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = "Location"
            java.lang.String r8 = r1.getHeaderField(r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r6.executeDownload(r7, r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x0118
        L_0x0147:
            if (r8 <= r2) goto L_0x015a
            boolean r8 = r6.shouldAllowRedirects     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r8 == 0) goto L_0x015a
            r8 = 1005(0x3ed, float:1.408E-42)
            java.lang.String r0 = "Too many redirects, giving up"
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            if (r1 == 0) goto L_0x0159
            r1.disconnect()
        L_0x0159:
            return
        L_0x015a:
            if (r1 == 0) goto L_0x01e7
            goto L_0x01e4
        L_0x015e:
            r6.shouldAllowRedirects = r8     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r8 = r6.readResponseHeaders(r7, r1, r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r2 = 1
            if (r8 != r2) goto L_0x01a8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = "Existing mDownloadedCacheSize: "
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r2 = r6.mDownloadedCacheSize     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = r8.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            util.Log.d((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.<init>()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r2 = "File mContentLength: "
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r2 = r6.mContentLength     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            r8.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = r8.toString()     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            util.Log.d((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r2 = r6.mDownloadedCacheSize     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            long r4 = r6.mContentLength     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 != 0) goto L_0x01a4
            r6.updateDownloadComplete(r7)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            java.lang.String r8 = "Download Completed"
            util.Log.d((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x01af
        L_0x01a4:
            r6.transferData(r7, r1)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
            goto L_0x01af
        L_0x01a8:
            r8 = 1006(0x3ee, float:1.41E-42)
            java.lang.String r0 = "Transfer-Encoding not found as well as can't know size of download, giving up"
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b9, ConnectTimeoutException -> 0x01b7, IOException -> 0x01b5 }
        L_0x01af:
            if (r1 == 0) goto L_0x01b4
            r1.disconnect()
        L_0x01b4:
            return
        L_0x01b5:
            r8 = move-exception
            goto L_0x01c0
        L_0x01b7:
            r8 = move-exception
            goto L_0x01d0
        L_0x01b9:
            r8 = move-exception
            goto L_0x01dc
        L_0x01bb:
            r7 = move-exception
            goto L_0x01ea
        L_0x01bd:
            r0 = move-exception
            r1 = r8
            r8 = r0
        L_0x01c0:
            r8.printStackTrace()     // Catch:{ all -> 0x01e8 }
            r8 = 1004(0x3ec, float:1.407E-42)
            java.lang.String r0 = "Trouble with low-level sockets"
            r6.updateDownloadFailed(r7, r8, r0)     // Catch:{ all -> 0x01e8 }
            if (r1 == 0) goto L_0x01e7
            goto L_0x01e4
        L_0x01cd:
            r0 = move-exception
            r1 = r8
            r8 = r0
        L_0x01d0:
            r8.printStackTrace()     // Catch:{ all -> 0x01e8 }
            r6.attemptRetryOnTimeOutException(r7)     // Catch:{ all -> 0x01e8 }
            if (r1 == 0) goto L_0x01e7
            goto L_0x01e4
        L_0x01d9:
            r0 = move-exception
            r1 = r8
            r8 = r0
        L_0x01dc:
            r8.printStackTrace()     // Catch:{ all -> 0x01e8 }
            r6.attemptRetryOnTimeOutException(r7)     // Catch:{ all -> 0x01e8 }
            if (r1 == 0) goto L_0x01e7
        L_0x01e4:
            r1.disconnect()
        L_0x01e7:
            return
        L_0x01e8:
            r7 = move-exception
            r8 = r1
        L_0x01ea:
            if (r8 == 0) goto L_0x01ef
            r8.disconnect()
        L_0x01ef:
            throw r7
        L_0x01f0:
            r8 = 1007(0x3ef, float:1.411E-42)
            java.lang.String r0 = "MalformedURLException: URI passed is malformed."
            r6.updateDownloadFailed(r7, r8, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: DownloadDispatcher.executeDownload(DownloadRequest, java.lang.String):void");
    }


    private void transferData(DownloadRequest r9, HttpURLConnection r10) {
        /*
            r8 = this;
            java.lang.String r0 = "Error in creating destination file"
            r1 = 0
            r8.cleanupDestination(r9, r1)
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0015 }
            java.io.InputStream r10 = r10.getInputStream()     // Catch:{ IOException -> 0x0015 }
            r3.<init>(r10)     // Catch:{ IOException -> 0x0015 }
            goto L_0x001a
        L_0x0011:
            r9 = move-exception
            r0 = r2
            goto L_0x00b8
        L_0x0015:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x0011 }
            r3 = r2
        L_0x001a:
            java.io.File r10 = new java.io.File     // Catch:{ all -> 0x00b5 }
            android.net.Uri r4 = r9.getDestinationURI()     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = r4.getPath()     // Catch:{ all -> 0x00b5 }
            r10.<init>(r4)     // Catch:{ all -> 0x00b5 }
            boolean r4 = r10.exists()     // Catch:{ all -> 0x00b5 }
            r5 = 1
            r6 = 1001(0x3e9, float:1.403E-42)
            if (r4 != 0) goto L_0x0052
            java.io.File r4 = r10.getParentFile()     // Catch:{ IOException -> 0x0049 }
            if (r4 == 0) goto L_0x003f
            boolean r7 = r4.exists()     // Catch:{ IOException -> 0x0049 }
            if (r7 != 0) goto L_0x003f
            r4.mkdirs()     // Catch:{ IOException -> 0x0049 }
        L_0x003f:
            boolean r4 = r10.createNewFile()     // Catch:{ IOException -> 0x0049 }
            if (r4 != 0) goto L_0x0057
            r8.updateDownloadFailed(r9, r6, r0)     // Catch:{ IOException -> 0x0049 }
            goto L_0x0050
        L_0x0049:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x00b5 }
            r8.updateDownloadFailed(r9, r6, r0)     // Catch:{ all -> 0x00b5 }
        L_0x0050:
            r1 = 1
            goto L_0x0057
        L_0x0052:
            if (r3 == 0) goto L_0x0057
            r9.abortCancel()     // Catch:{ all -> 0x00b5 }
        L_0x0057:
            if (r1 != 0) goto L_0x0083
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x006c }
            java.lang.String r1 = "rw"
            r0.<init>(r10, r1)     // Catch:{ IOException -> 0x006c }
            long r1 = r8.mDownloadedCacheSize     // Catch:{ IOException -> 0x0069, all -> 0x0067 }
            r0.seek(r1)     // Catch:{ IOException -> 0x0069, all -> 0x0067 }
            r2 = r0
            goto L_0x0070
        L_0x0067:
            r9 = move-exception
            goto L_0x00b7
        L_0x0069:
            r10 = move-exception
            r2 = r0
            goto L_0x006d
        L_0x006c:
            r10 = move-exception
        L_0x006d:
            r10.printStackTrace()     // Catch:{ all -> 0x00b5 }
        L_0x0070:
            if (r3 != 0) goto L_0x0078
            java.lang.String r10 = "Error in creating input stream"
            r8.updateDownloadFailed(r9, r6, r10)     // Catch:{ all -> 0x00b5 }
            goto L_0x0083
        L_0x0078:
            if (r2 != 0) goto L_0x0080
            java.lang.String r10 = "Error in writing download contents to the destination file"
            r8.updateDownloadFailed(r9, r6, r10)     // Catch:{ all -> 0x00b5 }
            goto L_0x0083
        L_0x0080:
            r8.transferData(r9, r3, r2)     // Catch:{ all -> 0x00b5 }
        L_0x0083:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r9 = move-exception
            r9.printStackTrace()
        L_0x008d:
            if (r2 == 0) goto L_0x00aa
            r2.close()     // Catch:{ IOException -> 0x0095 }
            goto L_0x00aa
        L_0x0093:
            r9 = move-exception
            goto L_0x009f
        L_0x0095:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x00b4
            r2.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00b4
        L_0x009f:
            if (r2 == 0) goto L_0x00a9
            r2.close()     // Catch:{ IOException -> 0x00a5 }
            goto L_0x00a9
        L_0x00a5:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00a9:
            throw r9
        L_0x00aa:
            if (r2 == 0) goto L_0x00b4
            r2.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00b4:
            return
        L_0x00b5:
            r9 = move-exception
            r0 = r2
        L_0x00b7:
            r2 = r3
        L_0x00b8:
            if (r2 == 0) goto L_0x00c2
            r2.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00c2:
            if (r0 == 0) goto L_0x00df
            r0.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00df
        L_0x00c8:
            r9 = move-exception
            goto L_0x00d4
        L_0x00ca:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x00e9
            r0.close()     // Catch:{ IOException -> 0x00e5 }
            goto L_0x00e9
        L_0x00d4:
            if (r0 == 0) goto L_0x00de
            r0.close()     // Catch:{ IOException -> 0x00da }
            goto L_0x00de
        L_0x00da:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00de:
            throw r9
        L_0x00df:
            if (r0 == 0) goto L_0x00e9
            r0.close()     // Catch:{ IOException -> 0x00e5 }
            goto L_0x00e9
        L_0x00e5:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00e9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: DownloadDispatcher.transferData(DownloadRequest, java.net.HttpURLConnection):void");
    }

    private void transferData(DownloadRequest downloadRequest, InputStream inputStream, RandomAccessFile randomAccessFile) {
        byte[] bArr = new byte[4096];
        long j = this.mDownloadedCacheSize;
        downloadRequest.setDownloadState(8);
        Log.v("Content Length: " + this.mContentLength + " for Download Id " + downloadRequest.getDownloadId());
        while (!downloadRequest.isCancelled()) {
            int readFromResponse = readFromResponse(downloadRequest, bArr, inputStream);
            long j2 = this.mContentLength;
            if (j2 != -1 && j2 > 0) {
                updateDownloadProgress(downloadRequest, (int) ((100 * j) / j2), j);
            }
            if (readFromResponse == -1) {
                updateDownloadComplete(downloadRequest);
                return;
            } else if (readFromResponse != Integer.MIN_VALUE) {
                if (writeDataToDestination(downloadRequest, bArr, readFromResponse, randomAccessFile)) {
                    j += (long) readFromResponse;
                } else {
                    downloadRequest.finish();
                    updateDownloadFailed(downloadRequest, 1001, "Failed writing file");
                    return;
                }
            } else {
                return;
            }
        }
        Log.v("Stopping the download as Download Request is cancelled for Downloaded Id " + downloadRequest.getDownloadId());
        downloadRequest.finish();
        updateDownloadFailed(downloadRequest, 1008, "Download cancelled");
    }

    private int readFromResponse(DownloadRequest downloadRequest, byte[] bArr, InputStream inputStream) {
        try {
            return inputStream.read(bArr);
        } catch (IOException e) {
            if ("unexpected end of stream".equals(e.getMessage())) {
                return -1;
            }
            updateDownloadFailed(downloadRequest, 1004, "IOException: Failed reading response");
            return Integer.MIN_VALUE;
        }
    }

    private boolean writeDataToDestination(DownloadRequest downloadRequest, byte[] bArr, int i, RandomAccessFile randomAccessFile) {
        try {
            randomAccessFile.write(bArr, 0, i);
            return true;
        } catch (IOException unused) {
            updateDownloadFailed(downloadRequest, 1001, "IOException when writing download contents to the destination file");
            return false;
        } catch (Exception unused2) {
            updateDownloadFailed(downloadRequest, 1001, "Exception when writing download contents to the destination file");
            return false;
        }
    }

    private int readResponseHeaders(DownloadRequest downloadRequest, HttpURLConnection httpURLConnection, int i) {
        String headerField = httpURLConnection.getHeaderField("Transfer-Encoding");
        this.mContentLength = -1;
        if (headerField != null) {
            Log.v("Ignoring Content-Length since Transfer-Encoding is also defined for Downloaded Id " + downloadRequest.getDownloadId());
        } else if (i == 200) {
            this.mContentLength = getHeaderFieldLong(httpURLConnection, "Content-Length", -1);
        } else {
            this.mContentLength = getHeaderFieldLong(httpURLConnection, "Content-Length", -1) + this.mDownloadedCacheSize;
        }
        if (this.mContentLength != -1) {
            return 1;
        }
        if (headerField == null || !headerField.equalsIgnoreCase("chunked")) {
            return -1;
        }
        return 1;
    }

    private long getHeaderFieldLong(URLConnection uRLConnection, String str, long j) {
        try {
            return Long.parseLong(uRLConnection.getHeaderField(str));
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    private void attemptRetryOnTimeOutException(final DownloadRequest downloadRequest) {
        updateDownloadState(downloadRequest, 128);
        RetryPolicy retryPolicy = downloadRequest.getRetryPolicy();
        try {
            retryPolicy.retry();
            this.mTimer.schedule(new TimerTask() {
                public void run() {
                    DownloadDispatcher downloadDispatcher = DownloadDispatcher.this;
                    DownloadRequest downloadRequest = null;
                    downloadDispatcher.executeDownload(downloadRequest, downloadRequest.getUri().toString());
                }
            }, (long) retryPolicy.getCurrentTimeout());
        } catch (RetryError unused) {
            updateDownloadFailed(downloadRequest, 1009, "Connection time out after maximum retires attempted");
        }
    }

    private void cleanupDestination(DownloadRequest downloadRequest, boolean z) {
        if (!downloadRequest.isResumable() || z) {
            Log.d("cleanupDestination() deleting " + downloadRequest.getDestinationURI().getPath());
            File file = new File(downloadRequest.getDestinationURI().getPath());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void updateDownloadState(DownloadRequest downloadRequest, int i) {
        downloadRequest.setDownloadState(i);
    }

    private void updateDownloadComplete(DownloadRequest downloadRequest) {
        this.mDownloadedCacheSize = 0;
        this.mDelivery.postDownloadComplete(
                downloadRequest);
        downloadRequest.setDownloadState(16);
        downloadRequest.finish();
    }

    private void updateDownloadFailed(DownloadRequest downloadRequest, int i, String str) {
        this.mDownloadedCacheSize = 0;
        this.shouldAllowRedirects = false;
        downloadRequest.setDownloadState(32);
        if (downloadRequest.getDeleteDestinationFileOnFailure()) {
            cleanupDestination(downloadRequest, true);
        }
        this.mDelivery.postDownloadFailed(downloadRequest, i, str);
        downloadRequest.finish();
    }

    private void updateDownloadProgress(DownloadRequest downloadRequest, int i, long j) {
        this.mDelivery.postProgressUpdate(downloadRequest, this.mContentLength, j, i);
    }
}
