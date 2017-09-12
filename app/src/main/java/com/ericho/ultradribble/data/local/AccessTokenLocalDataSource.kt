
package com.ericho.ultradribble.data.local

import android.content.Context
import com.ericho.ultradribble.data.AccessToken
import com.ericho.ultradribble.data.datasource.AccessTokenDataSource
import com.ericho.ultradribble.database.AppDatabase
import com.ericho.ultradribble.database.DatabaseCreator
import io.reactivex.Observable

/**
 *
 * Concrete implementation of a data source as a db.
 */

object AccessTokenLocalDataSource : AccessTokenDataSource {

    private var mDatabase: AppDatabase? = null

    override fun init(context: Context) {
        if (!DatabaseCreator.isDatabaseCreated()) {
            DatabaseCreator.createDb(context)
        }
    }

    override fun getAccessToken(id: Long?, code: String?): Observable<AccessToken> {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }

        mDatabase?.let {
            return if (id != null) {
                Observable.just(it.accessTokenDao().query(id))
            } else {
                Observable.empty()
            }
        }

        return Observable.empty()
    }

    override fun saveAccessToken(accessToken: AccessToken) {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }

        mDatabase?.let {
            Thread(Runnable {
                try {
                    mDatabase!!.beginTransaction()
                    mDatabase!!.accessTokenDao().insert(accessToken)
                    mDatabase!!.setTransactionSuccessful()
                } finally {
                    mDatabase!!.endTransaction()
                }
            }).start()
        }
    }

    override fun removeAccessToken(accessToken: AccessToken): Observable<Unit> {
        if (mDatabase == null) {
            mDatabase = DatabaseCreator.getDatabase()
        }

        return Observable.just(mDatabase?.accessTokenDao()?.delete(accessToken))
    }

}