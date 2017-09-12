package com.ericho.ultradribble.ui.user

import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */
interface UserProfileContract {

    interface View : BaseView<Presenter> {

        fun showUserInfo(user: User)

        fun setFollowable(followable: Boolean)

        fun setFollowing(isFollowing: Boolean)

        fun showNetworkError()

    }

    interface Presenter : BasePresenter {

        fun checkFollowing()

        fun toggleFollow()

        fun getUser(): User

    }

}