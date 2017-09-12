package com.ericho.ultradribble.mvp

/**
 * Created by Eric Ho on 2017/6/24.
 *
 * Base view in MVP architecture.
 */

interface BaseView<in T> {

    fun setPresenter(presenter : T)

}