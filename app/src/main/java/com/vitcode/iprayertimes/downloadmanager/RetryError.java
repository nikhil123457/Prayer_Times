package com.vitcode.iprayertimes.downloadmanager;

public class RetryError extends Exception {
    public RetryError() {
        super("Maximum retry exceeded");
    }

    public RetryError(Throwable th) {
        super(th);
    }
}
