package com.ericho.ultradribble.data.datasource

import android.content.Context
import com.ericho.ultradribble.data.AccessToken
import io.reactivex.Observable

/**
 *
 * Main entry point for accessing access token data.
 */

interface AccessTokenDataSource {

    fun init(context: Context)

    fun getAccessToken(id: Long?, code: String?): Observable<AccessToken>

    fun saveAccessToken(accessToken: AccessToken)

    fun removeAccessToken(accessToken: AccessToken): Observable<Unit>

}