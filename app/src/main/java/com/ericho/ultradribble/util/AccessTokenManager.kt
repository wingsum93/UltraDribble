package com.ericho.ultradribble.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ericho.ultradribble.data.AccessToken
import com.google.gson.Gson

/**
 */
object AccessTokenManager {

    var accessToken: AccessToken? = null

    fun init(context: Context) {
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        accessToken = Gson().fromJson(sp.getString(Constants.ACCESS_TOKEN, ""), AccessToken::class.java)
    }

    fun clear() {
        accessToken = null
    }

}