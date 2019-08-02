package com.flock.app.settings;

/**
 * Interface for storing twitter authentication tokens for users.
 * Implementations can decide if they should be backed by a persistent store or not.
 */
public interface BaseUrlStore {

    String get();

    String getConfluenceBaseUrl();

    void put(String baseUrl);
}
