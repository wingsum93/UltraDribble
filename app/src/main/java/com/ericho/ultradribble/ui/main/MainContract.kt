package com.ericho.ultradribble.ui.main

import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {

    interface View : BaseView<Presenter> {

        fun initViews()

        fun showAuthUserInfo(user: User)

        fun disableShortcuts()

        fun navigateToLogin()

    }

    interface Presenter : BasePresenter {

        fun fetchUser()

        fun logoutUser()

        fun getUser(): User?

    }

}