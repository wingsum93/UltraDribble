package com.ericho.ultradribble.ui.user.followers

import com.ericho.ultradribble.data.Follower
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */
interface FollowersContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(loading: Boolean)

        fun setEmptyViewVisibility(visible: Boolean)

        fun showNetworkError()

        fun showFollowers(followers: List<Follower>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

    }

    interface Presenter : BasePresenter {

        fun loadFollowers()

        fun loadMoreFollowers()

    }

}