package com.ericho.ultradribble.database.dao

import android.arch.persistence.room.*
import com.ericho.ultradribble.data.AccessToken

/**
 */

@Dao
interface AccessTokenDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(accessToken: AccessToken)

    @Query("SELECT * FROM access_token WHERE id = :id")
    fun query(id: Long): AccessToken

    @Update
    fun update(accessToken: AccessToken)

    @Delete
    fun delete(accessToken: AccessToken)

}