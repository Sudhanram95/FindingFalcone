package com.sudhan.findingfalcone.findfalconetest

import androidx.test.rule.ActivityTestRule
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import com.sudhan.findingfalcone.findfalcone.presenter.FindFalconePresenter
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.util.TestInputsUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSelectedVehicleTest {

    lateinit var findFalconePresenter: FindFalconePresenter
    lateinit var selectedVehicleMap: HashMap<Int, VehiclesModel>
    lateinit var expectedVehicleArray: Array<String>

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconePresenter = FindFalconePresenter(activityRule.activity)

        val vehicleList = TestInputsUtil.getTestVehicleList()

        selectedVehicleMap = TestInputsUtil.getTestSelectedVehicleMap(vehicleList)

        expectedVehicleArray = arrayOf("Space pod", "Space rocket", "Space shuttle", "Space ship")
    }

    @Test
    fun testOnGetSelectedVehicleArray() {
        val outputVehicleArray = findFalconePresenter.onGetSelectedVehicleArray(selectedVehicleMap)

        for (i in 0..outputVehicleArray.size-1) {
            Assert.assertEquals(expectedVehicleArray[i], outputVehicleArray[i])
        }
    }
}