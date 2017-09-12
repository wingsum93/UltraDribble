package com.ericho.ultradribble.database.dao

import android.arch.persistence.room.*
import com.ericho.ultradribble.data.User
import io.reactivex.Flowable

/**
 * Created by Eric Ho on 2017/6/28.
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * FROM user where id = :userId")
    fun query(userId: Long): Flowable<User>

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

}