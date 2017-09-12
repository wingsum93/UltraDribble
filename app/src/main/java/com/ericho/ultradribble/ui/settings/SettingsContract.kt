package com.ericho.ultradribble.ui.settings

import android.content.Context
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface SettingsContract {

    interface View : BaseView<Presenter> {

        fun updateCacheSize(size: Long)

    }

    interface Presenter : BasePresenter {

        fun computeCacheSize(context: Context)

        fun clearCache(context: Context)

    }

}