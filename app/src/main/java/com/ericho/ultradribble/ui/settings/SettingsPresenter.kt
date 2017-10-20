package com.ericho.ultradribble.ui.settings

import android.content.Context
import com.ericho.ultradribble.extension.dirSize
import com.ericho.ultradribble.extension.logCustomView_Setting
import com.ericho.ultradribble.glide.GlideApp
import com.ericho.ultradribble.glide.MyGlideModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

/**
 *
 * Listens to user action from the ui [SettingsFragment],
 * retrieves the data and update the ui as required.
 */

class SettingsPresenter(view: SettingsContract.View) : SettingsContract.Presenter {

    private val mView = view
    private val mCompositeDisposable: CompositeDisposable

    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {
        this.logCustomView_Setting()
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun computeCacheSize(context: Context) {
        val disposable = Observable.fromCallable<Long>(
                Callable<Long> {
                    return@Callable GlideApp.getPhotoCacheDir(context.applicationContext, MyGlideModule.CACHE_FILE_NAME).dirSize()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.updateCacheSize(it)
                }, {

                })
        mCompositeDisposable.add(disposable)
    }

    override fun clearCache(context: Context) {
        val disposable = Observable.fromCallable<Unit>(
                Callable {
                    return@Callable GlideApp.get(context.applicationContext).clearDiskCache()
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.updateCacheSize(0)
                }, {

                })
        mCompositeDisposable.add(disposable)
    }

}