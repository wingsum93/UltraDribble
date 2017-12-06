package com.ericho.ultradribble.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ericho.ultradribble.R
import com.ericho.ultradribble.ui.auth.AuthActivity
import com.ericho.ultradribble.ui.settings.SettingsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eric on 6/12/2017.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AuthActivityUiTest {
    private val STOP_INTERVAL = 1000L * 5
    @Rule
    @JvmField
    val rule = ActivityTestRule(AuthActivity::class.java)

    @Test
    fun empty(){

        Thread.sleep(STOP_INTERVAL)
    }

    @Test
    fun btnClick(){

        onView(withId(R.id.button_get_started))
                .perform(ViewActions.click());

    }
}