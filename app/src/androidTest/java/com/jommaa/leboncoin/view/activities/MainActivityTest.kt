package com.jommaa.leboncoin.view.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.jommaa.leboncoin.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun assert_loading_appears() {
        Espresso.onView((ViewMatchers.withId(R.id.progress))).check(matches(isDisplayed()))
    }

    @Test
    fun assert_can_scroll_to_into_albums_list() {
        Espresso.onView((ViewMatchers.withId(R.id.recycler_albumsList)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }
}