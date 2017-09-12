package com.ericho.ultradribble.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ericho.ultradribble.data.AccessToken
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.database.dao.AccessTokenDao
import com.ericho.ultradribble.database.dao.UserDao

/**
 * Created by Eric Ho on 2017/6/28.
 */

@Database(entities = arrayOf(AccessToken::class, User::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        val DATABASE_NAME = "dribble-db"
    }

    abstract fun accessTokenDao(): AccessTokenDao

    abstract fun userDao(): UserDao

}