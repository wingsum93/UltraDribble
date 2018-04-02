package com.ericho.ultradribble.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.ericho.ultradribble.App


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
        if (NetworkInfo.State.CONNECTED == state)
            App.getAppConfig().setUseWIFI(true)
        else
            App.getAppConfig().setUseWIFI(false)
    }
}