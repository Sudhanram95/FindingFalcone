package com.sudhan.findingfalcone.findfalconeresult.presenter

import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeRequest
import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeResponse
import com.sudhan.findingfalcone.findfalconeresult.model.TokenModel
import com.sudhan.findingfalcone.findfalconeresult.repository.FindFalconeResultNetworkManager
import com.sudhan.findingfalcone.findfalconeresult.view.IFindFalconeResultView
import com.sudhan.findingfalcone.network.NetworkCallback

class FindFalconeResultPresenter(var iFindFalconeResultView: IFindFalconeResultView): IFindFalconeResultPresenter {

    val networkManager = FindFalconeResultNetworkManager()
    lateinit var token: String

    override fun onGetToken() {
        networkManager.getToken(object : NetworkCallback {
            override fun onApiSuccess(response: Any) {
                val tokenModel = response as TokenModel
                token = tokenModel.token

                iFindFalconeResultView.onGetTokenApiSuccessResult()
            }

            override fun onApiFailure(errorMsg: Any) {
                val error = errorMsg as Throwable
                iFindFalconeResultView.onHandleApiError(error.message)
            }
        })
    }

    override fun onCreateFindFalconeRequest(planetArray: Array<String>, vehicleArray: Array<String>): FindFalconeRequest {
        val findFalconeRequest = FindFalconeRequest()
        findFalconeRequest.token = token
        findFalconeRequest.planetList = planetArray.toList()
        findFalconeRequest.vehicleList = vehicleArray.toList()
        return findFalconeRequest
    }

    override fun onGetFindFalconeResult(findFalconeRequest: FindFalconeRequest) {
        networkManager.getFindFalconeResult(findFalconeRequest, object : NetworkCallback {
            override fun onApiSuccess(response: Any) {
                val findFalconeResponse = response as FindFalconeResponse
                iFindFalconeResultView.onFindFalconeResult(findFalconeResponse)
            }

            override fun onApiFailure(errorMsg: Any) {
                val error = errorMsg as Throwable
                if (error.message!!.contains("400"))
                    onGetToken()
                else
                    iFindFalconeResultView.onHandleApiError(error.message)
            }
        })
    }
}