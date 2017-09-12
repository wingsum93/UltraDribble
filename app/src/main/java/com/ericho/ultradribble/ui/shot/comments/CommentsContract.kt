package com.ericho.ultradribble.ui.shot.comments

import com.ericho.ultradribble.data.Comment
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */
interface CommentsContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(loading: Boolean)

        fun showNetworkError()

        fun setEmptyViewVisibility(visible: Boolean)

        fun cancelSendingIndicator(clearText: Boolean)

        fun showCreateCommentFailed()

        fun showComments(comments: List<Comment>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

        fun updateTitle(commentsCount: Int)

        fun setEditorVisible(visible: Boolean, avatarUrl: String)

    }

    interface Presenter : BasePresenter {

        fun createComment(body: String)

        fun loadComments()

        fun loadMoreComments()

    }

}