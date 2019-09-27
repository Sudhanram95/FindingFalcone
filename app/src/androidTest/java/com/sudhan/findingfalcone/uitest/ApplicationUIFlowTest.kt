package com.sudhan.findingfalcone.uitest

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.rule.ActivityTestRule
import com.sudhan.findingfalcone.R
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.findfalcone.view.SelectPlanetAndVehicleAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sudhan.findingfalcone.util.CustomViewActions
import org.hamcrest.CoreMatchers.*


class ApplicationUIFlowTest {

    lateinit var findFalconeActivity: FindFalconeActivity

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconeActivity = activityRule.activity
    }

    @Test
    fun testApplicationUIFlow() {
        onView(withId(R.id.rel_find_falcone)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_select_planet_vehicle)).perform(RecyclerViewActions.actionOnItemAtPosition<SelectPlanetAndVehicleAdapter.Companion.MyViewHolder>(
            0, CustomViewActions.clickChildWithId(R.id.spinner_planet)
        ))

//        onData(anything()).inAdapterView(withId(R.id.spinner_planet)).atPosition(0).perform(click())
    }
}