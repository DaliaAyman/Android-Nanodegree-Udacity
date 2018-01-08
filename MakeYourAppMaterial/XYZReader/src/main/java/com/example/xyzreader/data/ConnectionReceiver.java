package com.example.xyzreader.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.xyzreader.MyApplication;

/**
 * Created by Dalia on 1/4/2018.
 */

public class ConnectionReceiver extends BroadcastReceiver {
    public static final String TAG = "Connection Receiver";
    public static OnNetworkChange onNetworkChangeCallback;

//    public void registerReceiver(OnNetworkChange onNetworkChangeCallback){
//        this.onNetworkChangeCallback = onNetworkChangeCallback;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Intent action: " + intent.getAction());

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
//            Toast.makeText(context, "Network is changed or disconnected", Toast.LENGTH_LONG).show();

            if(onNetworkChangeCallback != null){
                onNetworkChangeCallback.onNetworkDisconnected(isConnected);
            }else{
                Log.d(TAG, "Callback not registered in UI");
            }
        }
    }

    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public interface OnNetworkChange{
        public void onNetworkDisconnected(boolean isConnected);
    }
}
