package com.eburg_soft.currencyconverter.features


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.eburg_soft.currencyconverter.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val appCompatEditText = onView(
allOf(withId(R.id.etFirstCurrencyNumber),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        appCompatEditText.perform(replaceText("11"), closeSoftKeyboard())
        
        val appCompatEditText2 = onView(
allOf(withId(R.id.etFirstCurrencyNumber), withText("11"),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        appCompatEditText2.perform(click())
        
        val appCompatSpinner = onView(
allOf(withId(R.id.spSecondCurrencyType),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
1),
isDisplayed()))
        appCompatSpinner.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(250)
        
        val appCompatTextView = onData(anything())
.inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
.atPosition(2)
        appCompatTextView.perform(click())
        
        val appCompatSpinner2 = onView(
allOf(withId(R.id.spFirstCurrencyType),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
3),
isDisplayed()))
        appCompatSpinner2.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(250)
        
        val appCompatTextView2 = onData(anything())
.inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
.atPosition(6)
        appCompatTextView2.perform(click())
        
        val appCompatButton = onView(
allOf(withId(R.id.btnCount), withText("Count!"),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
9),
isDisplayed()))
        appCompatButton.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(300)
        
        val appCompatButton2 = onView(
allOf(withId(R.id.btnClear), withText("Clear"),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
10),
isDisplayed()))
        appCompatButton2.perform(click())
        
        val actionMenuItemView = onView(
allOf(withId(R.id.menu_action_history_fragment), withContentDescription("History fragment"),
childAtPosition(
childAtPosition(
withId(R.id.toolbarConverterFragment),
0),
0),
isDisplayed()))
        actionMenuItemView.perform(click())
        
        val appCompatImageButton = onView(
allOf(childAtPosition(
allOf(withId(R.id.toolbarHistoryFragment),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton.perform(click())
        
        val actionMenuItemView2 = onView(
allOf(withId(R.id.menu_action_history_fragment), withContentDescription("History fragment"),
childAtPosition(
childAtPosition(
withId(R.id.toolbarConverterFragment),
0),
0),
isDisplayed()))
        actionMenuItemView2.perform(click())
        
        val floatingActionButton = onView(
allOf(withId(R.id.fab),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        floatingActionButton.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
