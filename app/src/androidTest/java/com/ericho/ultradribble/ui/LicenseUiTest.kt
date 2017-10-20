package com.ericho.ultradribble.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ericho.ultradribble.R
import com.ericho.ultradribble.ui.settings.license.LicensesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Before



/**
 * Created by steve_000 on 18/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui
 */
@RunWith(AndroidJUnit4::class)
class LicenseUiTest {
    private var mStringToBetyped: String? = null

    @Rule
    @JvmField
    var act:ActivityTestRule<LicensesActivity> = ActivityTestRule(LicensesActivity::class.java)


    @Before
    fun initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso"

    }

    @Test
    fun xxx(){
        Thread.sleep((10 * 1000).toLong())
    }
    @Test
    fun xxx22(){
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}