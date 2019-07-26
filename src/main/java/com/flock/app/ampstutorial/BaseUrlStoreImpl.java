package com.flock.app.ampstutorial;

import javax.inject.Named;

/**
 * Simple, non-persistent store of {@link BaseUrlRecord}. Useful during development process, be should be replaced
 * with a persistent store for production use.
 */
@Named
public class BaseUrlStoreImpl implements BaseUrlStore {

    private String baseUrl = "https://www.flock.com";

    @Override
    public String get() {
        return baseUrl;
    }

    @Override
    public void put(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
