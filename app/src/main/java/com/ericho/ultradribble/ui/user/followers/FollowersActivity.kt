package com.ericho.ultradribble.ui.user.followers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 *
 */
class FollowersActivity : AppCompatActivity() {

    private lateinit var mFollowersFragment: FollowersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(FollowersPresenter.EXTRA_FOLLOWERS_TITLE)

        savedInstanceState?.let {
            mFollowersFragment = supportFragmentManager.getFragment(it, FollowersFragment::class.java.simpleName) as FollowersFragment
        } ?: run {
            mFollowersFragment = FollowersFragment.newInstance()
        }

        if (!mFollowersFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mFollowersFragment, FollowersFragment::class.java.simpleName)
                    .commit()
        }

        FollowersPresenter(mFollowersFragment, intent.getLongExtra(FollowersPresenter.EXTRA_USER_ID, 0L))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mFollowersFragment.isAdded) {
            supportFragmentManager.putFragment(outState, FollowersFragment::class.java.simpleName, mFollowersFragment)
        }
    }

}