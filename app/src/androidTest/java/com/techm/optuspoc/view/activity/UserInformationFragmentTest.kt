package com.techm.optuspoc.view.activity


import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techm.optuspoc.R
import com.techm.optuspoc.view.fragment.UserInformationFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class UserInformationFragmentTest {

    @Rule @JvmField var activityActivityTestRule = ActivityTestRule(UserInformation::class.java)

    @Test
    fun checkTextDisplayedInDynamicallyCreatedFragment() {

        val fragment = UserInformationFragment()
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }
    /**test for checking scrolling functionality**/
    @Test
    fun listScrollToLastAndFirst() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val recyclerview: RecyclerView =
            activityActivityTestRule.activity.findViewById<RecyclerView>(R.id.user_list)
        val count = recyclerview.adapter?.itemCount
        onView(withId(R.id.user_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                count!!.toInt()
            )
        )

        onView(withId(R.id.user_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

}
