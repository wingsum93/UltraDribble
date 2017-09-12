package com.ericho.ultradribble.ui.user.following

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 */
class FollowingActivity : AppCompatActivity() {

    private lateinit var mFollowingFragment: FollowingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(FollowingPresenter.EXTRA_FOLLOWING_TITLE)

        savedInstanceState?.let {
            mFollowingFragment = supportFragmentManager.getFragment(it, FollowingFragment::class.java.simpleName) as FollowingFragment
        } ?: run {
            mFollowingFragment = FollowingFragment.newInstance()
        }

        if (!mFollowingFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mFollowingFragment, FollowingFragment::class.java.simpleName)
                    .commit()
        }

        FollowingPresenter(mFollowingFragment, intent.getLongExtra(FollowingPresenter.EXTRA_USER_ID, 0L))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mFollowingFragment.isAdded) {
            supportFragmentManager.putFragment(outState, FollowingFragment::class.java.simpleName, mFollowingFragment)
        }
    }

}