package com.ericho.ultradribble.ui.shot

import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 * Created by lizhaotailang on 2017/6/28.
 *
 * This specifies the contract between the view and the presenter.
 */
interface ShotContract {

    interface View : BaseView<Presenter> {

        fun showNetworkError()

        fun setLikeStatus(like: Boolean)

        fun updateLikeCount(count: Int)

        fun show(shot: Shot)

        fun navigateToUserProfile(user: User)

        fun navigateToComments(shot: Shot)

        fun navigateToLikes(shot: Shot)

        fun openInBrowser(url: String)

        fun share(url: String)

    }

    interface Presenter : BasePresenter {

        fun toggleLike()

        fun navigateToUserProfile()

        fun navigateToComments()

        fun navigateToLikes()

        fun share()

        fun openInBrowser()

    }

}