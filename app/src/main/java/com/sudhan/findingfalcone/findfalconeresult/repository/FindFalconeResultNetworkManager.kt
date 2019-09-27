package com.sudhan.findingfalcone.findfalconeresult.repository

import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeRequest
import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeResponse
import com.sudhan.findingfalcone.findfalconeresult.model.TokenModel
import com.sudhan.findingfalcone.network.NetworkCallback
import com.sudhan.findingfalcone.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FindFalconeResultNetworkManager {
    val apiEndPoints = NetworkUtil.retrofitHelper()?.create(FindFalconeApiEndpoints::class.java)

    fun getToken(networkCallback: NetworkCallback) {
        apiEndPoints?.getToken()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<TokenModel>() {
                override fun onSuccess(tokenModel: TokenModel) {
                    networkCallback.onApiSuccess(tokenModel)
                }

                override fun onError(e: Throwable) {
                    networkCallback.onApiFailure(e)
                }
            })
    }

    fun getFindFalconeResult(findFalconeRequest: FindFalconeRequest, networkCallback: NetworkCallback) {
        apiEndPoints?.getFindFalconeResult(findFalconeRequest)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<FindFalconeResponse>() {
                override fun onSuccess(findFalconeResponse: FindFalconeResponse) {
                    networkCallback.onApiSuccess(findFalconeResponse)
                }

                override fun onError(e: Throwable) {
                    networkCallback.onApiFailure(e)
                }
            })
    }
}