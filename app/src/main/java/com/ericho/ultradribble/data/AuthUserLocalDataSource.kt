package com.ericho.ultradribble.data

import android.content.Context
import com.ericho.ultradribble.database.AppDatabase
import com.ericho.ultradribble.database.DatabaseCreator

import io.reactivex.Observable

/**
 *
 * Concrete implementation of a data source as a db.
 */

object AuthUserLocalDataSource : AuthUserDataSource {

    private var mDatabase: AppDatabase? = null

    override fun init(context: Context) {
        if (!DatabaseCreator.isDatabaseCreated()) {
            DatabaseCreator.createDb(context)
        }
        mDatabase = DatabaseCreator.getDatabase()
    }

    override fun getAuthenticatedUser(userId: Long?): Observable<User> {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }

        mDatabase?.let {
            return if (userId != null) {
                it.userDao().query(userId).toObservable()
            } else {
                Observable.empty()
            }
        }
        return Observable.empty()
    }

    override fun saveAuthenticatedUser(user: User) {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }

        mDatabase?.let {
            Thread(Runnable {
                try {
                    it.beginTransaction()
                    it.userDao().insert(user)
                    it.setTransactionSuccessful()
                } finally {
                    it.endTransaction()
                }
            }).start()
        }
    }

    override fun updateAuthenticatedUser(user: User) {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }
        mDatabase?.userDao()?.update(user)
    }

    override fun deleteAuthenticatedUser(user: User): Observable<Unit> {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }
        return Observable.just(mDatabase?.userDao()?.delete(user))
    }

    override fun refreshAuthenticatedUser(): Observable<User> {
        // Not required for the remote data source because the [com.ericho.ultradribble.data.repository.UserRepository]
        // handles removing data from cache and database.
        return Observable.empty()
    }

}