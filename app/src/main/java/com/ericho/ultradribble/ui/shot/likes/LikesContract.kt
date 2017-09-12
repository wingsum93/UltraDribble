package com.ericho.ultradribble.ui.shot.likes

import com.ericho.ultradribble.data.Like
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */
interface LikesContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(loading: Boolean)

        fun showNetworkError()

        fun showLikes(likes: List<Like>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

        fun setEmptyViewVisibility(visible: Boolean)

        fun updateTitle(likeCount: Int)

    }

    interface Presenter : BasePresenter {

        fun loadLikes()

        fun loadMoreLikes()

    }

}