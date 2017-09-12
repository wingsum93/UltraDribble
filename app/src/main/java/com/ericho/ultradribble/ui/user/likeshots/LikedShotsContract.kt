package com.ericho.ultradribble.ui.user.likeshots

import com.ericho.ultradribble.data.LikedShot
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface LikedShotsContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(loading: Boolean)

        fun setEmptyViewVisibility(visible: Boolean)

        fun showNetworkError()

        fun showLikedShots(likeShots: List<LikedShot>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

    }

    interface Presenter : BasePresenter {

        fun loadLikedShots()

        fun loadMoreLikedShots()

    }

}