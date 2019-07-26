package com.flock.app.ampstutorial;

/**
 * Interface for storing twitter authentication tokens for users.
 * Implementations can decide if they should be backed by a persistent store or not.
 */
public interface BaseUrlStore {

    String  get();

    void put(String baseUrl);
}
