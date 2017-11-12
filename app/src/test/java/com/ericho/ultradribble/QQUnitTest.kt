package com.ericho.ultradribble

import com.ericho.ultradribble.ui.settings.license.firstCharUppercase
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(JUnit4::class)
class QQUnitTest {
    @Test
    fun addition_isCorrect() {
        val str = "abv"
        val str2 = "POk"
        val str3 = "lol"
        val str4 = "QQQ"

        assertTrue(str != str.firstCharUppercase())
        assertTrue(str2 == str2.firstCharUppercase())
        assertTrue(str3 != str3.firstCharUppercase())
        assertTrue(str4 == str4.firstCharUppercase())
    }
}