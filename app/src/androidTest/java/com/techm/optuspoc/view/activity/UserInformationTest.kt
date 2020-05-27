package com.techm.optuspoc.view.activity


import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
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
class UserInformationTest {

    @Rule @JvmField var activityActivityTestRule = ActivityTestRule(UserInformation::class.java)

    @Test
    fun checkTextDisplayedInDynamicallyCreatedFragment() {
       // var activityScenario=ActivityScenario.launch(UserInformation::class.java)
        val fragment = UserInformationFragment()
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
      //  onView(withId(R.id.txt_cool_message)).check(matches(withText("So Cool!")))
    }
}
