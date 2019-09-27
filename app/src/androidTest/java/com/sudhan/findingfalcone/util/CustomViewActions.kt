package com.sudhan.findingfalcone.util

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import org.hamcrest.Matcher

object CustomViewActions {

    fun clickChildWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return click().constraints
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v = view!!.findViewById<View>(id)
                v.performClick()
            }
        }
    }
 }