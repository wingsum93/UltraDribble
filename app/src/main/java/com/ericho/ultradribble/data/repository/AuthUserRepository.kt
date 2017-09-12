

package com.ericho.ultradribble.data.repository

import android.content.Context
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.data.datasource.AuthUserDataSource
import com.ericho.ultradribble.data.local.AuthUserLocalDataSource
import com.ericho.ultradribble.data.remote.AuthUserRemoteDataSource
import io.reactivex.Observable


/**
 */

object AuthUserRepository : AuthUserDataSource {

    private var mCachedUsers: MutableMap<Long, User> = linkedMapOf()

    override fun init(context: Context) {
        AuthUserLocalDataSource.init(context)
    }

    override fun getAuthenticatedUser(userId: Long?): Observable<User> {
        userId?.let {
            mCachedUsers[userId]?.let {
                return Observable.just(it)
            }
            return AuthUserLocalDataSource
                    .getAuthenticatedUser(userId)
                    .flatMap {
                        Observable.just(it)
                                .doOnNext {
                                    mCachedUsers.put(userId, it)
                                }
                    }
        } ?: run {
            return AuthUserRemoteDataSource.getAuthenticatedUser(userId)
                    .flatMap {
                        Observable.just(it)
                                .doOnNext {
                                    mCachedUsers.put(it.id, it)
                                    AuthUserLocalDataSource.saveAuthenticatedUser(it)
                                }
                    }
        }
    }

    override fun saveAuthenticatedUser(user: User) {
        AuthUserLocalDataSource.saveAuthenticatedUser(user)
    }

    override fun updateAuthenticatedUser(user: User) {
        AuthUserLocalDataSource.updateAuthenticatedUser(user)
    }

    override fun deleteAuthenticatedUser(user: User): Observable<Unit> {
        return AuthUserLocalDataSource.deleteAuthenticatedUser(user)
    }

    override fun refreshAuthenticatedUser(): Observable<User> {
        return AuthUserRemoteDataSource.refreshAuthenticatedUser()
                .flatMap {
                    Observable.just(it)
                            .doOnNext { user ->
                                if (mCachedUsers.keys.contains(user.id)) {
                                    mCachedUsers[user.id] = it
                                } else {
                                    mCachedUsers.put(it.id, it)
                                }
                                AuthUserLocalDataSource.updateAuthenticatedUser(it)
                            }
                }
    }

}