package com.flock.app.network;

public interface OkhttpProvider {
    void postEvent(String headerActionType, String body);
}
