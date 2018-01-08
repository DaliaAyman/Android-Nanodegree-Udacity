package com.example.xyzreader;

import android.app.Application;

import com.example.xyzreader.data.ConnectionReceiver;

/**
 * Created by Dalia on 1/8/2018.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectionListener(ConnectionReceiver.OnNetworkChange listener) {
        ConnectionReceiver.onNetworkChangeCallback = listener;
    }
}