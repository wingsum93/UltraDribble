package com.ericho.ultradribble.ui.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R

/**
 *
 * Show profile for a [].
 */

class UserProfileActivity : AppCompatActivity() {

    private lateinit var mUserProfileFragment: UserProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        savedInstanceState?.let {
            mUserProfileFragment = supportFragmentManager.getFragment(savedInstanceState, UserProfileFragment::class.java.simpleName) as UserProfileFragment
        } ?: run {
            mUserProfileFragment = UserProfileFragment.getInstance()
        }

        if (!mUserProfileFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mUserProfileFragment, UserProfileFragment::class.java.simpleName)
                    .commit()
        }

        UserProfilePresenter(mUserProfileFragment, intent.getParcelableExtra(UserProfilePresenter.EXTRA_USER))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mUserProfileFragment.isAdded) {
            supportFragmentManager.putFragment(outState, UserProfileFragment::class.java.simpleName, mUserProfileFragment)
        }
    }

}