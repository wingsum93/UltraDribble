package com.ericho.ultradribble.data.repository

import com.ericho.ultradribble.data.LikedShot
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.retrofit.*
import com.ericho.ultradribble.ui.main.shots.ShotsPagePresenter
import com.ericho.ultradribble.util.AccessTokenManager
import io.reactivex.Observable
import retrofit2.Response

/**
 */

object ShotsRepository {

    private val mShotsService: ShotsService = RetrofitClient.createService(ShotsService::class.java, AccessTokenManager.accessToken)
    private val mUserService: UserService = RetrofitClient.createService(UserService::class.java, AccessTokenManager.accessToken)
    private val mUsersService: UsersService = RetrofitClient.createService(UsersService::class.java, AccessTokenManager.accessToken)

    fun listFollowingShots(): Observable<Response<List<Shot>>> {
        return mUserService.listFollowingShots(ApiConstants.PER_PAGE)
    }

    fun listShots(type: Int): Observable<Response<List<Shot>>> {
        val timeframe: String? = null
        val date: String? = null

        val sort: String? = if (type == ShotsPagePresenter.TYPE_RECENT) {
            "recent"
        } else {
            null
        }

        val queryList: String? = if (type == ShotsPagePresenter.TYPE_DEBUTS) {
            "debuts"
        } else {
            null
        }

        return mShotsService.listShots(queryList, timeframe, date, sort)
    }

    fun listShotsOfNextPage(url: String): Observable<Response<List<Shot>>> {
        return mShotsService.listShotsOfNextPage(url)
    }

    fun listShotsOfUser(userId: Long): Observable<Response<List<Shot>>> {
        return mUsersService.listShotsOfUser(userId)
    }

    fun listShotsOfUserOfNextPage(url: String): Observable<Response<List<Shot>>> {
        return mUsersService.listShotsOfNextPage(url)
    }

    fun listLikedShotsOfUser(userId: Long): Observable<Response<List<LikedShot>>> {
        return mUsersService.listLikedShotsOfUser(userId)
    }

    fun listLikedShotOfNextPage(url: String): Observable<Response<List<LikedShot>>> {
        return mUsersService.listLikedShotsOfNextPage(url)
    }

}
