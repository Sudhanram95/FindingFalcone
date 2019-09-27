package com.sudhan.findingfalcone.findfalconeresulttest

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.gson.Gson
import com.sudhan.findingfalcone.findfalconeresult.presenter.FindFalconeResultPresenter
import com.sudhan.findingfalcone.findfalconeresult.view.FindFalconeResultActivity
import com.sudhan.findingfalcone.util.TestInputsUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateFindFalconeRequestTest {

    lateinit var expectedRequest: String
    lateinit var planetArray: Array<String>
    lateinit var vehicleArray: Array<String>
    lateinit var findFalconeResultPresenter: FindFalconeResultPresenter

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeResultActivity>(FindFalconeResultActivity::class.java)

    @Before
    fun setUp() {
        findFalconeResultPresenter = FindFalconeResultPresenter(activityRule.activity)
        findFalconeResultPresenter.token = "abcde"
        expectedRequest = TestInputsUtil.getJson(InstrumentationRegistry.getInstrumentation().context, "findFalconeRequest.json")
        planetArray = arrayOf("Donlon", "Enchai", "Jebing", "Sapir")
        vehicleArray = arrayOf("Space pod", "Space rocket", "Space shuttle", "Space ship")
    }

    @Test
    fun testOnCreateFindFalconeRequest() {
        val outputModel = findFalconeResultPresenter.onCreateFindFalconeRequest(planetArray, vehicleArray)
        val outputRequest = Gson().toJson(outputModel)

        Assert.assertEquals(expectedRequest, outputRequest)
    }
}