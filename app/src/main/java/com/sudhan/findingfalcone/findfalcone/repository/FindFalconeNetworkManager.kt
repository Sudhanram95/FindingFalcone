package com.sudhan.findingfalcone.findfalcone.repository

import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import com.sudhan.findingfalcone.network.NetworkCallback
import com.sudhan.findingfalcone.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FindFalconeNetworkManager {

    val apiEndPoints = NetworkUtil.retrofitHelper()?.create(GetPlanetAndVehicleApiEndpoints::class.java)

    fun getPlanets(networkCallback: NetworkCallback) {
        apiEndPoints?.getPlanets()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<ArrayList<PlanetModel>>() {
                override fun onSuccess(planetList: ArrayList<PlanetModel>) {
                    networkCallback.onApiSuccess(planetList)
                }

                override fun onError(e: Throwable) {
                    networkCallback.onApiFailure(e)
                }
            })
    }

    fun getVehicles(networkCallback: NetworkCallback) {
        apiEndPoints?.getVehicles()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<ArrayList<VehiclesModel>>() {
                override fun onSuccess(vehicleList: ArrayList<VehiclesModel>) {
                    networkCallback.onApiSuccess(vehicleList)
                }

                override fun onError(e: Throwable) {
                    networkCallback.onApiFailure(e)
                }
            })
    }
}