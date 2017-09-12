package com.ericho.ultradribble.data

import android.content.Context
import io.reactivex.Observable

/**
 *
 * Main entry point for accessing user data.
 */
interface AuthUserDataSource {

    fun init(context: Context)

    fun getAuthenticatedUser(userId: Long?): Observable<User>

    fun saveAuthenticatedUser(user: User)

    fun updateAuthenticatedUser(user: User)

    fun deleteAuthenticatedUser(user: User): Observable<Unit>

    fun refreshAuthenticatedUser(): Observable<User>

}