package com.sudhan.findingfalcone.findfalcone.view

import com.sudhan.findingfalcone.findfalcone.model.PlanetModel

interface IFindFalconeView {
    fun onRenderSelectForm(planetList: ArrayList<PlanetModel>)
    fun onSetTimeTakenResult(timeTaken: Int)
    fun onFindFalconeEnabledResult(selectedPlanetArray: Array<String>, selectedVehicleArray: Array<String>)
    fun onHandleApiError(errorMsg: String?)
}