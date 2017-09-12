package com.ericho.ultradribble.ui.auth

import android.content.Intent
import android.support.annotation.StringRes
import com.ericho.ultradribble.data.AccessToken
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface AuthContract {

    interface View : BaseView<Presenter> {

        fun isActive(): Boolean

        fun setLoginIndicator(isLoading: Boolean)

        fun showMessage(@StringRes id: Int)

        fun showMessage(message: String)

        fun handleAuthCallback(intent: Intent?)

        fun updateLoginStatus(accessToken: AccessToken)

    }

    interface Presenter : BasePresenter {

        fun requestAccessToken(code: String)

    }

}