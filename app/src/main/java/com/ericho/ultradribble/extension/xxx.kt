package com.ericho.ultradribble.extension

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.support.customtabs.CustomTabsIntent

/**
 * Created by steve_000 on 18/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */
fun Context.broweWithChromeTab(url:String){
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(this, Uri.parse(url))
}