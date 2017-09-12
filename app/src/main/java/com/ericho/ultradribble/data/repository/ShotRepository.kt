
package com.ericho.ultradribble.data.repository

import com.ericho.ultradribble.data.Comment
import com.ericho.ultradribble.data.Like
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.retrofit.RetrofitClient
import com.ericho.ultradribble.retrofit.ShotsService
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body

/**
 */

object ShotRepository {

    private val mShotsService = RetrofitClient.createService(ShotsService::class.java, AccessTokenManager.accessToken)

    fun getShot(shotId: Long): Observable<Response<Shot>> {
        return mShotsService.getShot(shotId)
    }

    fun checkLike(shotId: Long): Observable<Response<Like>> {
        return mShotsService.checkLikeOfShot(shotId)
    }

    fun likeShot(shotId: Long): Observable<Response<Like>> {
        return mShotsService.likeShot(shotId)
    }

    fun unlikeShot(shotId: Long): Observable<Response<Body>> {
        return mShotsService.unlikeShot(shotId)
    }

    fun listComments(shotId: Long): Observable<Response<List<Comment>>> {
        return mShotsService.listCommentsForShot(shotId)
    }

    fun listCommentsOfNextPage(url: String): Observable<Response<List<Comment>>> {
        return mShotsService.listCommentsOfNextPage(url)
    }

    fun createComment(shotId: Long, body: String): Observable<Response<Comment>> {
        return mShotsService.createComment(shotId, body)
    }

    fun listLikes(shotId: Long): Observable<Response<List<Like>>> {
        return mShotsService.listLikesForShot(shotId)
    }

    fun listLikesOfNextPage(url: String): Observable<Response<List<Like>>> {
        return mShotsService.listLikesOfNextPage(url)
    }
}