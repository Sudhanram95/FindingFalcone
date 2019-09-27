package com.sudhan.findingfalcone.findfalconetest

import androidx.test.rule.ActivityTestRule
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import com.sudhan.findingfalcone.findfalcone.presenter.FindFalconePresenter
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.util.TestInputsUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculateTimeTakenTest {

    lateinit var selectedPlanetMap: HashMap<Int, PlanetModel>
    lateinit var selectedVehicleMap: HashMap<Int, VehiclesModel>
    lateinit var findFalconePresenter: FindFalconePresenter

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconePresenter = FindFalconePresenter(activityRule.activity)

        val testPlanetList = TestInputsUtil.getTestPlanetList()
        val testVehicleList = TestInputsUtil.getTestVehicleList()

        selectedPlanetMap = TestInputsUtil.getTestSelectedPlanetMap(testPlanetList)
        selectedVehicleMap = TestInputsUtil.getTestSelectedVehicleMap(testVehicleList)
    }

    @Test
    fun testOnCalculateTimeTaken() {
        val totalTimeTaken = findFalconePresenter.onCalculateTimeTaken(selectedPlanetMap, selectedVehicleMap)
        Assert.assertEquals(200, totalTimeTaken)
    }
}