/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dlogan.android.tvmaze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.fail
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test


class BottomNavigationTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun openFirstScreen() {
        onView(allOf(withContentDescription(R.string.title_on_now), isDisplayed()))
                .perform(click())
    }

    @Test
    fun openSecondScreen() {
        onView(allOf(withContentDescription(R.string.title_all_shows), isDisplayed()))
    }

    @Test
    fun openThirdScreen() {
        onView(allOf(withContentDescription(R.string.title_about), isDisplayed()))
                .perform(click())
    }

    @Test
    fun assertFirstScreen() {
        onView(allOf(withText(R.string.title_on_now), isDescendantOfA(withId(R.id.action_bar))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun assertSecondScreen() {
        onView(allOf(withText(R.string.title_all_shows), isDescendantOfA(withId(R.id.action_bar))))
    }

    @Test
    fun assertThirdScreen() {
        onView(allOf(withText(R.string.title_about), isDescendantOfA(withId(R.id.action_bar))))
    }
}
