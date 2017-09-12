package com.ericho.ultradribble.data.repository

import com.ericho.ultradribble.data.Followee
import com.ericho.ultradribble.data.Follower
import com.ericho.ultradribble.retrofit.ApiConstants
import com.ericho.ultradribble.retrofit.RetrofitClient
import com.ericho.ultradribble.retrofit.UserService
import com.ericho.ultradribble.retrofit.UsersService
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body

/**
 */
object UserRepository {

    private val mUsersService = RetrofitClient.createService(UsersService::class.java, AccessTokenManager.accessToken)
    private val mUserService = RetrofitClient.createService(UserService::class.java, AccessTokenManager.accessToken)

    fun checkFollowing(userId: Long): Observable<Response<Body>> {
        return mUserService.checkFollowing(userId)
    }

    fun follow(userId: Long): Observable<Response<Body>> {
        return mUsersService.follow(userId)
    }

    fun unfollow(userId: Long): Observable<Response<Body>> {
        return mUsersService.unfollow(userId)
    }

    fun listFollowersOfUser(userId: Long): Observable<Response<List<Follower>>> {
        return mUsersService.listFollowersOfUser(userId, ApiConstants.PER_PAGE)
    }

    fun listFollowersOfNextPage(url: String): Observable<Response<List<Follower>>> {
        return mUsersService.listFollowersOfNextPage(url)
    }

    fun listFolloweeOfUser(userId: Long): Observable<Response<List<Followee>>> {
        return mUsersService.listFollowingOfUser(userId, ApiConstants.PER_PAGE)
    }

    fun listFolloweesOfNextPage(url: String): Observable<Response<List<Followee>>> {
        return mUsersService.listFollowingOfNextPage(url)
    }

}