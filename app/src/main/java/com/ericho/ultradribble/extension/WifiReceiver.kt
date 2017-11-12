package com.ericho.ultradribble.extension

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager

/**
 * Created by steve_000 on 12/11/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */
class WifiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == android.net.wifi.WifiManager.NETWORK_STATE_CHANGED_ACTION) {
            val manager = context?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            if (manager?.isWifiEnabled ?: false) {
                val wifiInfo = manager!!.connectionInfo
                if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                    val ssid = wifiInfo.getSSID()//SSID found
                }
            }
        }
    }
}