package com.flock.app;

public class Utils {

    private Utils() {
    }

    public static boolean isValidUrl(String url) {
        return (url.startsWith("https://") || url.startsWith("http://"));
    }
}
