package com.ericho.ultradribble.ui.shot.likes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 *
 * Show [com.ericho.ultradribble.data.Shot]s of a shot.
 */
class LikesActivity : AppCompatActivity() {

    private var mLikesFragment: LikesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        savedInstanceState?.let {
            mLikesFragment = supportFragmentManager.getFragment(it, LikesFragment::class.java.simpleName) as? LikesFragment
        } ?: run {
            mLikesFragment = LikesFragment.newInstance()
        }

        mLikesFragment?.let {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, it, LikesFragment::class.java.simpleName)
                    .commit()
            LikesPresenter(it, intent.getParcelableExtra(LikesPresenter.EXTRA_SHOT))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mLikesFragment?.let {
            if (it.isAdded) {
                supportFragmentManager.putFragment(outState, LikesFragment::class.java.simpleName, mLikesFragment)
            }
        }
    }

}