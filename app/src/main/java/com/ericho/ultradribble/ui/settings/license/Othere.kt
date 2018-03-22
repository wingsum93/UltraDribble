package com.ericho.ultradribble.ui.settings.license

import java.util.*

/**
 * Created by steve_000 on 18/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui.settings.license
 */
fun LicensesFragment.getLicenseList():List<LicenseBo>{

    val t = ArrayList<LicenseBo>()
    t.add(getLicenseBo("Retrofit2","square","Apache License. Version 2.0","https://github.com/square/retrofit"))
    t.add(getLicenseBo("Okhttp3","square","Apache License. Version 2.0","https://github.com/square/okhttp"))
    t.add(getLicenseBo("Rxkotlin","ReactiveX","Apache License. Version 2.0","https://github.com/ReactiveX/RxKotlin"))
    t.add(getLicenseBo("Rxandroid","ReactiveX","Apache License. Version 2.0","https://github.com/ReactiveX/RxAndroid"))
    t.add(getLicenseBo("Glide","Bumptech","BSD, part MIT and Apache 2.0","https://github.com/bumptech/glide"))

    t.add(getLicenseBo("Anko","Kotlin","Apache License. Version 2.0","https://github.com/Kotlin/anko"))
    t.add(getLicenseBo("flexbox-layout","google","Apache License. Version 2.0","https://github.com/google/flexbox-layout"))
    t.add(getLicenseBo("Room Persistence Library","google","Apache License. Version 2.0","https://developer.android.com/topic/libraries/architecture/room.html"))
    t.add(getLicenseBo("DeepLinkDispatch","airbnb","Apache License. Version 2.0","https://github.com/airbnb/DeepLinkDispatch"))
    t.add(getLicenseBo("Timber","JakeWharton","Apache License. Version 2.0","https://github.com/JakeWharton/timber"))
    t.add(getLicenseBo("stetho","facebook","BSD-licensed","https://github.com/facebook/stetho"))
    t.add(getLicenseBo("Crashlytics","fabric","Apache License. Version 2.0","https://fabric.io/kits/android/crashlytics"))
    t.add(getLicenseBo("Android Support Library","google","Apache License. Version 2.0","https://github.com/square/okhttp"))

    t.forEach {
        it.name = it.name.firstCharUppercase()
        it.author = it.author.firstCharUppercase()
    }
    t.sortWith(compareBy<LicenseBo> { it.name }.thenBy { it.author })
    return t
}

private fun LicensesFragment.getLicenseBo(name:String,
                                          author:String,
                                          lisence: String,
                                          url:String):LicenseBo{
    return LicenseBo(name,author,lisence,url)
}
fun String.firstCharUppercase() = this.first().toUpperCase() + this.substring(1)