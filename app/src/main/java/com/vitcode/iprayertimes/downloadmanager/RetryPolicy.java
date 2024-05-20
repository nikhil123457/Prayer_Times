package com.vitcode.iprayertimes.downloadmanager;

public interface RetryPolicy {
    float getBackOffMultiplier();

    int getCurrentRetryCount();

    int getCurrentTimeout();

    void retry() throws RetryError;
}
