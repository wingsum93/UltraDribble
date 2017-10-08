package com.ericho.ultradribble.ui.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ericho.ultradribble.R
import com.ericho.ultradribble.extension.action

/**
 * Created by steve_000 on 22/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui.test
 */
class TestMainAct:AppCompatActivity() , TestMainFrag.TestHomeListener {

    var testMainFragment:TestMainFrag? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)
        initView(savedInstanceState)

    }

    private fun initView(savedInstanceState: Bundle?) {

        savedInstanceState?.let {
            testMainFragment = supportFragmentManager.getFragment(savedInstanceState,"main") as TestMainFrag
        }?: run {
            testMainFragment = TestMainFrag()
        }

        if(!testMainFragment!!.isAdded){
            supportFragmentManager.action{
                add(R.id.container,testMainFragment)
            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(testMainFragment!!.isAdded) supportFragmentManager.putFragment(outState,"main",testMainFragment)
    }

    override fun onCustomButtonOpen() {
        supportFragmentManager.action {

        }
    }

    override fun switchToFragment(frag: Fragment) {
        supportFragmentManager.action {
            replace(R.id.container,frag)
                    .addToBackStack(frag::class.java.name)

        }
    }

    override fun onBackPressed() {
        if (!supportFragmentManager.popBackStackImmediate()) {
            super.onBackPressed()
        }
    }
}