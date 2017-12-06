package com.ericho.ultradribble.ui

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ericho.ultradribble.ui.settings.SettingsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eric on 6/12/2017.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SettingsActivityUiTest {
    private val STOP_INTERVAL = 1000L * 5
    @Rule
    @JvmField
    val rule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun empty(){

        Thread.sleep(STOP_INTERVAL)
    }

//    @Test
//    fun btnClick(){
//
//        onView(withText("clear_cache"))
//                .perform(ViewActions.click());
//
//    }
}