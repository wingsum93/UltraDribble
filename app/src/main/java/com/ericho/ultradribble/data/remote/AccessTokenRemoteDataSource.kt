package com.ericho.ultradribble.data.remote

import android.content.Context
import com.ericho.ultradribble.data.AccessToken
import com.ericho.ultradribble.data.datasource.AccessTokenDataSource
import com.ericho.ultradribble.retrofit.AccessTokenService
import com.ericho.ultradribble.retrofit.ApiConstants
import com.ericho.ultradribble.retrofit.RetrofitClient
import io.reactivex.Observable

/**
 *
 * Implementation of the data source that accessing network.
 */

object AccessTokenRemoteDataSource : AccessTokenDataSource {

    override fun init(context: Context) {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.AccessTokenRepository]
        // handle the initialization.
    }

    override fun getAccessToken(id: Long?, code: String?): Observable<AccessToken> {
        return RetrofitClient.createService(AccessTokenService::class.java, null)
                .getAccessToken(ApiConstants.DRIBBBLE_GET_ACCESS_TOKEN_URL,
                        ApiConstants.CLIENT_ID,
                        ApiConstants.CLIENT_SECRET,
                        code!!,
                        ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI)
    }

    override fun saveAccessToken(accessToken: AccessToken) {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.AccessTokenRepository]
        // handle the saving data to cache and database.
    }

    override fun removeAccessToken(accessToken: AccessToken): Observable<Unit> {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.AccessTokenRepository]
        // handle the removing data from cache and database.
        return Observable.empty()
    }
}