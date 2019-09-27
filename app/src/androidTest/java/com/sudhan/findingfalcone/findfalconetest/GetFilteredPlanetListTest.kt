package com.sudhan.findingfalcone.findfalconetest

import androidx.test.rule.ActivityTestRule
import com.google.gson.Gson
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.presenter.FindFalconePresenter
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.util.TestInputsUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFilteredPlanetListTest {

    lateinit var selectedPlanetMap: HashMap<Int, PlanetModel>
    lateinit var findFalconePresenter: FindFalconePresenter

    @get:Rule
    var activityRule = ActivityTestRule<FindFalconeActivity>(FindFalconeActivity::class.java)

    @Before
    fun setUp() {
        findFalconePresenter = FindFalconePresenter(activityRule.activity)

        findFalconePresenter.planetList = TestInputsUtil.getTestPlanetList()

        selectedPlanetMap = TestInputsUtil.getTestSelectedPlanetMap(findFalconePresenter.planetList)
    }

    @Test
    fun testOnGetFilteredPlanetList() {
        println("Planet: ${Gson().toJson(findFalconePresenter.planetList)}")
        findFalconePresenter.planetList = findFalconePresenter.onGetFilteredPlanetList(selectedPlanetMap)

        for (i in 0..3) {
            Assert.assertTrue(findFalconePresenter.planetList.get(i).isSelected)
        }
    }
}