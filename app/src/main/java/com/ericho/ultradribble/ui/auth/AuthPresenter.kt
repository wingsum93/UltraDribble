package com.ericho.ultradribble.ui.auth

import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.repository.AccessTokenRepository
import com.ericho.ultradribble.data.repository.AuthUserRepository
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Listens to user action from the ui ,
 * retrieves the data and update the ui as required.
 */

class AuthPresenter(view: AuthContract.View) : AuthContract.Presenter {

    private val mView: AuthContract.View = view
    private val mCompositeDisposable: CompositeDisposable

    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun requestAccessToken(code: String) {
        mCompositeDisposable.clear()
        val disposable = AccessTokenRepository.getAccessToken(null, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ token ->
                    if (token.accessToken.isNullOrEmpty()) {
                        if (mView.isActive()) {
                            mView.setLoginIndicator(false)
                            mView.showMessage(R.string.request_refresh_token_failed)
                        }
                    } else {
                        // Update the access token of AccountManager
                        AccessTokenManager.accessToken = token

                        // Save the access token to database
                        AccessTokenRepository.saveAccessToken(token)

                        requestUserInfo()
                    }
                }, { error ->
                    if (mView.isActive()) {
                        mView.setLoginIndicator(false)
                        error.message?.let {
                            mView.showMessage(it)
                        }
                    }
                })
        mCompositeDisposable.add(disposable)
    }

    private fun requestUserInfo() {
        val disposable = AuthUserRepository.getAuthenticatedUser(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    if (user.id != 0L) {
                        // Update the user id in access token
                        AccessTokenManager.accessToken?.let { it.id = user.id }

                        // Save the user info to database
                        AuthUserRepository.saveAuthenticatedUser(user)

                        AccessTokenManager.accessToken?.let { mView.updateLoginStatus(it) }
                    }
                }, { error ->
                    if (mView.isActive()) {
                        mView.setLoginIndicator(false)
                        error.message?.let {
                            mView.showMessage(it)
                        }
                    }
                })
        mCompositeDisposable.add(disposable)
    }

}