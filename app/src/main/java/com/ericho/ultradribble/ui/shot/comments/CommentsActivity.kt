package com.ericho.ultradribble.ui.shot.comments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 *
 * Show []s of a shot.
 */

class CommentsActivity : AppCompatActivity() {

    private lateinit var mCommentsFragment: CommentsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        savedInstanceState?.let {
            mCommentsFragment = supportFragmentManager.getFragment(it, CommentsFragment::class.java.simpleName) as CommentsFragment
        } ?: run {
            mCommentsFragment = CommentsFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, mCommentsFragment, CommentsFragment::class.java.simpleName)
                .commit()

        CommentsPresenter(mCommentsFragment, intent.getParcelableExtra(CommentsPresenter.EXTRA_SHOT))

    }

}