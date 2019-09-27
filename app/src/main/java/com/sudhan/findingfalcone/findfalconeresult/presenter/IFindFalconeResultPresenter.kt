package com.sudhan.findingfalcone.findfalconeresult.presenter

import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeRequest

interface IFindFalconeResultPresenter {
    fun onGetToken()
    fun onCreateFindFalconeRequest(planetArray: Array<String>, vehicleArray: Array<String>): FindFalconeRequest
    fun onGetFindFalconeResult(findFalconeRequest: FindFalconeRequest)
}