package com.ericho.ultradribble.data.remote

import android.content.Context
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.data.datasource.AuthUserDataSource
import com.ericho.ultradribble.retrofit.RetrofitClient
import com.ericho.ultradribble.retrofit.UserService
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.Observable

/**
 * Created by lizhaotailang on 2017/6/28.
 *
 * Implementation of data source that accessing network.
 */

object AuthUserRemoteDataSource : AuthUserDataSource {

    private var mUserService: UserService = RetrofitClient.createService(UserService::class.java, AccessTokenManager.accessToken)

    override fun init(context: Context) {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.UserRepository]
        // handles the initialization.
    }

    override fun getAuthenticatedUser(userId: Long?): Observable<User> {
        return mUserService.getAuthenticatedUser()
    }

    override fun saveAuthenticatedUser(user: User) {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.UserRepository]
        // handles saving data to cache and database.
    }

    override fun updateAuthenticatedUser(user: User) {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.UserRepository]
        // handles updating data of cache and database.
    }

    override fun deleteAuthenticatedUser(user: User): Observable<Unit> {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.UserRepository]
        // handles removing data from cache and database.
        return Observable.empty()
    }

    override fun refreshAuthenticatedUser(): Observable<User> {
        return mUserService.getAuthenticatedUser()
    }

}