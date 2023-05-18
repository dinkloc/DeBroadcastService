package com.example.truefalseandroid;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String networkState(Context context) {
        String status = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null) {
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
                status = "Wifi connected";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobie Data connected";
            }
        } else {
            status = "no internet";
        }
        return status;
    }
}