package com.ericho.ultradribble.extension

import android.content.Context
import android.support.v7.preference.PreferenceManager
import com.ericho.ultradribble.data.AccessToken
import com.google.gson.Gson

/**
 * Created by steve_000 on 12/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */
fun Context.getAccessToken():AccessToken{
    val p = PreferenceManager.getDefaultSharedPreferences(this)
    val str = p.getString("ACCESS_TOKEN","")
    val result = Gson().fromJson(str,AccessToken::class.java)
    return result
}