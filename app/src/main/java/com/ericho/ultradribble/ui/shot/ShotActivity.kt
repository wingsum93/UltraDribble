package com.ericho.ultradribble.ui.shot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.ui.shot.ShotPresenter

/**
 *
 * Show the details of a [io.github.tonnyl.mango.data.Shot].
 */

@DeepLink("https://dribbble.com/shots/{id}")
class ShotActivity : AppCompatActivity() {

    private lateinit var mShotFragment: ShotFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        savedInstanceState?.let {
            mShotFragment = supportFragmentManager.getFragment(it, ShotFragment::class.java.simpleName) as ShotFragment
        } ?: run {
            mShotFragment = ShotFragment.newInstance()
        }

        if (!mShotFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mShotFragment, ShotFragment::class.java.simpleName)
                    .commit()
        }

        val isDeepLink = intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)
        val shotId: Long
        // hook the presenter
        if (isDeepLink) {
            var id = intent.extras.getString("id")
            // https://dribbble.com/shots/3495164-Google-people and https://dribbble.com/shots/3495164
            // are both valid
            val dashIndex = if (id.isEmpty()) -1 else id.indexOf("-")
            if (dashIndex != -1) {
                id = id.substring(0, dashIndex)
            }
            shotId = id.toLong()
            com.ericho.ultradribble.ui.shot.ShotPresenter(mShotFragment, shotId)

        } else {
            com.ericho.ultradribble.ui.shot.ShotPresenter(mShotFragment, intent.getParcelableExtra<Shot>(com.ericho.ultradribble.ui.shot.ShotPresenter.EXTRA_SHOT))
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mShotFragment.isAdded) {
            supportFragmentManager.putFragment(outState, ShotFragment::class.java.simpleName, mShotFragment)
        }
    }

}