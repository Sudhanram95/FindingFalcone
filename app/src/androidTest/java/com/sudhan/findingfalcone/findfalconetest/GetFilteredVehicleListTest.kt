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

class GetFilteredVehicleListTest {

    lateinit var findFalconePresenter: FindFalconePresenter
    lateinit var selectedVehicle: VehiclesModel

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconePresenter = FindFalconePresenter(activityRule.activity)

        findFalconePresenter.vehicleList = TestInputsUtil.getTestVehicleList()

        selectedVehicle = findFalconePresenter.vehicleList.get(1)
    }

    @Test
    fun testOnGetFilteredVehicleList() {
        findFalconePresenter.onUpdateVehicleCount(null, selectedVehicle)
        findFalconePresenter.vehicleList = findFalconePresenter.onGetFilteredVehicleList(100, selectedVehicle)

        Assert.assertEquals(0, findFalconePresenter.vehicleList.get(1).totalNumber)
    }
}