package com.ericho.ultradribble.extension

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by steve_000 on 22/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.extension
 */
fun FragmentManager.action(block:FragmentTransaction.()->Unit){
    val t = beginTransaction()
    block(t)
    t.commit()
}