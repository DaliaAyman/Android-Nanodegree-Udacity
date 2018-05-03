package com.example.xyzreader.remote;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    public static final URL BASE_URL;
    private static String TAG = Config.class.toString();

    public static final String NETWORK_SWITCH_FILTER = "com.example.xyzreader.NETWORK_SWITCH_FILTER";
    public static final String INTERNET_CONNECTION_ERROR = "Please check your Internet connection.";

    static {
        URL url = null;
        try {
            url = new URL("https://go.udacity.com/xyz-reader-json" );
//            url = new URL("https://raw.githubusercontent.com/TNTest/xyzreader/master/data.json");
        } catch (MalformedURLException ignored) {
            // TODO: throw a real error
            Log.e(TAG, INTERNET_CONNECTION_ERROR);
        }

        BASE_URL = url;
    }
}
