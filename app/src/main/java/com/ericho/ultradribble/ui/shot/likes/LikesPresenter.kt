package com.ericho.ultradribble.ui.shot.likes

import com.ericho.ultradribble.data.Like
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.data.repository.ShotRepository
import com.ericho.ultradribble.extension.PageLinks

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Listens to user action from the ui [LikesFragment],
 * retrieves the data and update the ui as required.
 */

class LikesPresenter(view: LikesContract.View, shot: Shot) : LikesContract.Presenter {

    private val mView = view
    private val mShot = shot
    private val mCompositeDisposable: CompositeDisposable

    private val mCachedLikes = arrayListOf<Like>()
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
        loadLikes()
        mView.updateTitle(mShot.likesCount)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun loadLikes() {
        mView.setLoadingIndicator(true)

        val disposable = ShotRepository
                .listLikes(mShot.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    mView.setLoadingIndicator(false)
                    mNextPageUrl = PageLinks(response).next

                    response.body()?.let {
                        mView.setEmptyViewVisibility(it.isEmpty())
                        if (it.isNotEmpty()) {
                            if (mCachedLikes.isNotEmpty()) {
                                val size = mCachedLikes.size
                                mCachedLikes.clear()
                                mView.notifyDataAllRemoved(size)
                                mCachedLikes.addAll(it)
                                mView.notifyDataAdded(0, mCachedLikes.size)
                            } else {
                                mCachedLikes.addAll(it)
                                mView.showLikes(mCachedLikes)
                            }
                        }
                    }
                }, {
                    mView.setLoadingIndicator(false)
                    mView.setEmptyViewVisibility(true)
                    it.printStackTrace()
                })
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreLikes() {
        mNextPageUrl?.let {
            val disposable = ShotRepository
                    .listLikesOfNextPage(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        mNextPageUrl = PageLinks(response).next

                        response.body()?.let {
                            val size = mCachedLikes.size
                            mCachedLikes.addAll(it)
                            mView.notifyDataAdded(size, it.size)
                        }
                    }, {
                        mView.showNetworkError()
                        it.printStackTrace()
                    })
            mCompositeDisposable.add(disposable)
        }
    }

}