
package com.ericho.ultradribble.ui.user.shots

import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.data.repository.ShotsRepository
import com.ericho.ultradribble.extension.PageLinks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Listens to user action from the ui [ShotsFragment],
 * retrieves the data and update the ui as required.
 */
class ShotsPresenter(view: ShotsContract.View, user: User) : ShotsContract.Presenter {

    private val mView = view
    private val mUser = user
    private val mCompositeDisposable: CompositeDisposable

    private var mNextPageUrl: String? = null

    private val mCachedShots = arrayListOf<Shot>()

    companion object {
        @JvmField
        val EXTRA_USER = "EXTRA_USER"
    }

    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {
        loadShots()
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun loadShots() {
        mView.setLoadingIndicator(true)
        val disposable = ShotsRepository.listShotsOfUser(mUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    mView.setLoadingIndicator(false)
                    mNextPageUrl = PageLinks(response).next

                    response.body()?.let {
                        mView.setEmptyViewVisibility(it.isEmpty())
                        if (it.isNotEmpty()) {
                            for (shot in it) {
                                shot.user = mUser
                            }
                            if (mCachedShots.isNotEmpty()) {
                                val size = mCachedShots.size
                                mCachedShots.clear()
                                mView.notifyDataAllRemoved(size)
                                mCachedShots.addAll(it)
                                mView.notifyDataAdded(0, it.size)
                            } else {
                                mCachedShots.addAll(it)
                                mView.showShots(mCachedShots)
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

    override fun loadShotsOfNextPage() {
        mNextPageUrl?.let {
            val disposable = ShotsRepository.listShotsOfUserOfNextPage(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        mNextPageUrl = PageLinks(response).next

                        response.body()?.let {
                            val size = mCachedShots.size
                            mCachedShots.addAll(it)
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