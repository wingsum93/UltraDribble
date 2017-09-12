package com.ericho.ultradribble.data.repository

import android.content.Context
import com.ericho.ultradribble.data.AccessToken
import com.ericho.ultradribble.data.datasource.AccessTokenDataSource
import com.ericho.ultradribble.data.local.AccessTokenLocalDataSource
import com.ericho.ultradribble.data.remote.AccessTokenRemoteDataSource
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.Observable

/**
 */

object AccessTokenRepository : AccessTokenDataSource {

    override fun init(context: Context) {
        AccessTokenLocalDataSource.init(context)
    }

    override fun getAccessToken(id: Long?, code: String?): Observable<AccessToken> {
        if (code != null) {
            return AccessTokenRemoteDataSource.getAccessToken(id, code)
                    .flatMap { accessToken ->
                        Observable.just(accessToken)
                                .doOnNext {
                                    AccessTokenManager.accessToken = accessToken
                                }
                    }
        }

        return AccessTokenLocalDataSource.getAccessToken(id, code)
                .flatMap { accessToken ->
                    Observable.just(accessToken)
                            .doOnNext {
                                AccessTokenManager.accessToken = accessToken
                            }
                }
    }

    override fun saveAccessToken(accessToken: AccessToken) {
        AccessTokenLocalDataSource.saveAccessToken(accessToken)
    }

    override fun removeAccessToken(accessToken: AccessToken): Observable<Unit> {
        return AccessTokenLocalDataSource.removeAccessToken(accessToken)
    }
}