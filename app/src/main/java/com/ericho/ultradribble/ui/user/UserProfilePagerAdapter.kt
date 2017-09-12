package com.ericho.ultradribble.ui.user

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.User
import com.ericho.ultradribble.ui.user.likeshots.LikedShotsFragment
import com.ericho.ultradribble.ui.user.likeshots.LikedShotsPresenter
import com.ericho.ultradribble.ui.user.shots.ShotsFragment
import com.ericho.ultradribble.ui.user.shots.ShotsPresenter

/**
 */
class UserProfilePagerAdapter(context: Context, user: User, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mTitles = arrayOf<String>()
    private val mUser = user

    init {
        mTitles = arrayOf(
                context.getString(R.string.tab_title_shots),
                context.getString(R.string.tab_title_likes)
        )
    }

    override fun getCount() = mTitles.size

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = ShotsFragment.newInstance()
                ShotsPresenter(fragment, mUser)
            }
            else -> {
                fragment = LikedShotsFragment.newInstance()
                LikedShotsPresenter(fragment, mUser.id)
            }
        }
        return fragment
    }

    override fun getPageTitle(position: Int) = mTitles[position]

}