package com.ericho.ultradribble.ui.shot.comments

import com.ericho.ultradribble.data.Comment
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.data.repository.AuthUserRepository
import com.ericho.ultradribble.data.repository.ShotRepository
import com.ericho.ultradribble.extension.PageLinks
import com.ericho.ultradribble.extension.canUserComment
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 *
 * Listens to user action from the ui [CommentsFragment],
 * retrieves the data and update the ui as required.
 */
class CommentsPresenter(view: CommentsContract.View, shot: Shot) : CommentsContract.Presenter {

    private val mView = view
    private val mShot = shot
    private var mCompositeDisposable: CompositeDisposable

    private val mCachedComments = arrayListOf<Comment>()
    private var mNextPageUrl: String? = null

    companion object {
        @JvmField
        val EXTRA_SHOT = "EXTRA_SHOT"
    }

    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {
        loadComments()

        mView.updateTitle(mShot.commentsCount)

        AccessTokenManager.accessToken?.let {
            AuthUserRepository.getAuthenticatedUser(it.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        mView.setEditorVisible(it.canUserComment(), it.avatarUrl)
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun createComment(body: String) {
        val disposable = ShotRepository
                .createComment(mShot.id, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    response.body()?.let {
                        mView.cancelSendingIndicator(true)

                        if (mNextPageUrl == null) {
                            insertNewComment(body)
                        }
                    } ?: run {
                        mView.cancelSendingIndicator(false)
                    }
                }, {
                    mView.cancelSendingIndicator(false)
                    mView.showCreateCommentFailed()
                })
        mCompositeDisposable.add(disposable)
    }

    override fun loadComments() {
        mView.setLoadingIndicator(true)

        val disposable = ShotRepository
                .listComments(mShot.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    mNextPageUrl = PageLinks(response).next
                    mView.setLoadingIndicator(false)

                    response.body()?.let {
                        mView.setEmptyViewVisibility(it.isEmpty())
                        if (it.isNotEmpty()) {
                            if (mCachedComments.isNotEmpty()) {
                                val size = mCachedComments.size
                                mCachedComments.clear()
                                mView.notifyDataAllRemoved(size)
                                mCachedComments.addAll(it)
                                mView.notifyDataAdded(0, mCachedComments.size)
                            } else {
                                mCachedComments.addAll(it)
                                mView.showComments(mCachedComments)
                            }
                        }
                    }
                }, {
                    mView.setEmptyViewVisibility(true)
                    mView.setLoadingIndicator(false)
                    it.printStackTrace()
                })
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreComments() {
        mNextPageUrl?.let {
            val disposable = ShotRepository
                    .listCommentsOfNextPage(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        mNextPageUrl = PageLinks(response).next

                        response.body()?.let {
                            val size = mCachedComments.size
                            mCachedComments.addAll(it)
                            mView.notifyDataAdded(size, it.size)
                        }
                    }, {
                        mView.showNetworkError()
                        it.printStackTrace()
                    })
            mCompositeDisposable.add(disposable)
        }
    }

    private fun insertNewComment(commentBody: String) {
        AccessTokenManager.accessToken?.let { token ->
            AuthUserRepository.getAuthenticatedUser(token.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        mCachedComments.add(Comment(id = token.id, body = commentBody, likesCount = 0, likesUrl = "", createdAt = Date(System.currentTimeMillis()), updatedAt = Date(System.currentTimeMillis()), user = it))
                        mView.notifyDataAdded(mCachedComments.size - 1, 1)
                    }, {
                        it.printStackTrace()
                    })
        }
    }

}