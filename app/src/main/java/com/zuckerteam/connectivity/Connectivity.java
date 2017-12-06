package com.zuckerteam.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Dung Ali on 8/18/2017.
 */

public class Connectivity {

    Context context;

    public Connectivity(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager!=null){
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info!=null&&info.getState()== NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;
    }
}
