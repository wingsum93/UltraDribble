package com.ericho.ultradribble.extension

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.ericho.ultradribble.BuildConfig
import com.ericho.ultradribble.mvp.BasePresenter


/**
 * This extension is for easy input fabric answer
 * Created by steve_000 on 20/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */
fun BasePresenter.logCustomView(contentId:String,contentType:String,contentName:String,
                                customAttr:ArrayList<Pair<String,String>> = ArrayList<Pair<String,String>>()){
    val z = ContentViewEvent()
    z.putContentId(contentId)
    z.putContentType(contentType)
    z.putContentName(contentName)

    customAttr.add("flavor" to BuildConfig.FLAVOR )
    customAttr.add("build_type" to BuildConfig.BUILD_TYPE )
    customAttr.forEach {
        z.putCustomAttribute(it.first,it.second)
    }
    Answers.getInstance().logContentView(z)
}
fun BasePresenter.logCustomView_UserProfile(username:String){
    val l = ArrayList<Pair<String,String>>()
    val z = "username" to username
    l.add(z)
    this.logCustomView("user","user_info","User Profile Page",l)
}
fun BasePresenter.logCustomView_Setting(){
    val l = ArrayList<Pair<String,String>>()
    this.logCustomView("setting","setting","Setting Page",l)
}