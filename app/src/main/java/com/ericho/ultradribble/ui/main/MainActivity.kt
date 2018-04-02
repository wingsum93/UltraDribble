package com.ericho.ultradribble.ui.main

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import com.ericho.ultradribble.util.AccessTokenManager
import org.jetbrains.anko.design.snackbar

/**
 * Show the homepage view.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    var lastExitTime:Long = 0L
    var exit_time_second:Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        savedInstanceState?.let {
            mainFragment = supportFragmentManager.getFragment(it, MainFragment::class.java.simpleName) as MainFragment
        } ?: run {
            mainFragment = MainFragment.newInstance()
        }

        if (AccessTokenManager.accessToken == null) {
            AccessTokenManager.init(this)
        }

        if (!mainFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mainFragment, MainFragment::class.java.simpleName)
                    .commit()
        }

        MainPresenter(mainFragment)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mainFragment.isAdded) {
            supportFragmentManager.putFragment(outState, MainFragment::class.java.simpleName, mainFragment)
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() > lastExitTime + exit_time_second * 1000 ){
//            Snackbar.make(findViewById(android.R.id.content),getString(R.string.confirm_to_exit),Snackbar.LENGTH_SHORT)
//                    .setAction(R.string.confirm,{super.onBackPressed()})
//                    .setActionTextColor(Color.YELLOW)
//                    .show()
            lastExitTime = System.currentTimeMillis()
            snackbar(findViewById(android.R.id.content), R.string.confirm_to_exit, R.string.confirm) {
                super.onBackPressed()
            }.setActionTextColor(Color.YELLOW)
                    .setDuration(Snackbar.LENGTH_SHORT)
                    .show()
        }else{
            super.onBackPressed()
        }

    }
}
