package com.sudhan.findingfalcone.findfalconetest

import androidx.test.rule.ActivityTestRule
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.presenter.FindFalconePresenter
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.util.TestInputsUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSelectedPlanetTest {

    lateinit var selectedPlanetMap: HashMap<Int, PlanetModel>
    lateinit var findFalconePresenter: FindFalconePresenter
    lateinit var expectedPlanetArray: Array<String>

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconePresenter = FindFalconePresenter(activityRule.activity)

        val planetList = TestInputsUtil.getTestPlanetList()

        selectedPlanetMap = TestInputsUtil.getTestSelectedPlanetMap(planetList)

        expectedPlanetArray = arrayOf("Donlon", "Enchai", "Jebing", "Sapir")
    }

    @Test
    fun testOnGetSelectedPlanetArray() {
        val outputPlanetArray = findFalconePresenter.onGetSelectedPlanetArray(selectedPlanetMap)

        for (i in 0..outputPlanetArray.size-1) {
            Assert.assertEquals(expectedPlanetArray[i], outputPlanetArray[i])
        }
    }
}