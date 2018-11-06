package com.ricky.fliktos


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest_tags_separated_by_space() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.text_input),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.text_input_wrapper),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("cat orange"), closeSoftKeyboard())

        val searchGoButton = onView(
            allOf(
                withId(R.id.search_go_btn),
                isDisplayed()
            )
        )
        searchGoButton.perform(click())
    }

    @Test
    fun mainActivityTest_tags_separated_by_comma() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.text_input),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.text_input_wrapper),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("cat,orange"), closeSoftKeyboard())

        val searchGoButton = onView(
            allOf(
                withId(R.id.search_go_btn),
                isDisplayed()
            )
        )
        searchGoButton.perform(click())
    }

    @Test
    fun mainActivityTest_tags_separated_by_comma_and_space() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.text_input),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.text_input_wrapper),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("cat, orange"), closeSoftKeyboard())

        val searchGoButton = onView(
            allOf(
                withId(R.id.search_go_btn),
                isDisplayed()
            )
        )
        searchGoButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
