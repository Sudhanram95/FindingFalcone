package com.sudhan.findingfalcone.findfalcone.presenter

import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import com.sudhan.findingfalcone.findfalcone.repository.FindFalconeNetworkManager
import com.sudhan.findingfalcone.findfalcone.view.IFindFalconeView
import com.sudhan.findingfalcone.network.NetworkCallback

open class FindFalconePresenter(var iFindFalconeView: IFindFalconeView) : IFindFalconePresenter {

    val networkManager = FindFalconeNetworkManager()
    lateinit var planetList: ArrayList<PlanetModel>
    lateinit var vehicleList: ArrayList<VehiclesModel>

    override fun onGetPlanetList() {
        networkManager.getPlanets(object : NetworkCallback {
            override fun onApiSuccess(response: Any) {
                planetList = response as ArrayList<PlanetModel>
                planetList.add(0, PlanetModel())
                onGetVehicleList()
            }

            override fun onApiFailure(errorMsg: Any) {
                val error = errorMsg as Throwable
                iFindFalconeView.onHandleApiError(error.message)
            }
        })
    }

    override fun onGetVehicleList() {
        networkManager.getVehicles(object : NetworkCallback {
            override fun onApiSuccess(response: Any) {
                vehicleList = response as ArrayList<VehiclesModel>
                iFindFalconeView.onRenderSelectForm(planetList)
            }

            override fun onApiFailure(errorMsg: Any) {
                val error = errorMsg as Throwable
                iFindFalconeView.onHandleApiError(error.message)
            }
        })
    }

    override fun onGetFilteredPlanetList(selectedPlanetList: HashMap<Int, PlanetModel>): ArrayList<PlanetModel> {
        for (planet in planetList) {
            planet.isSelected = selectedPlanetList.containsValue(planet)
        }
        return planetList
    }

    override fun onGetFilteredVehicleList(distanceOfPlanet: Int, selectedVehicle: VehiclesModel?): ArrayList<VehiclesModel> {
        return ArrayList(vehicleList.filter {
            it.maxDistance >= distanceOfPlanet &&
                    (it.totalNumber > 0 || it.equals(selectedVehicle))
        })
    }

    override fun onUpdateVehicleCount(previousSelectedVehicle: VehiclesModel?, recentSelectedVehicle: VehiclesModel) {
        if (previousSelectedVehicle != null && !previousSelectedVehicle.equals(recentSelectedVehicle)) {
            val previousVehicleIndex = vehicleList.indexOf(previousSelectedVehicle)
            previousSelectedVehicle.totalNumber = previousSelectedVehicle.totalNumber + 1
            vehicleList.set(previousVehicleIndex, previousSelectedVehicle)
        }
        val recentVehicleIndex = vehicleList.indexOf(recentSelectedVehicle)
        recentSelectedVehicle.totalNumber = recentSelectedVehicle.totalNumber - 1
        vehicleList.set(recentVehicleIndex, recentSelectedVehicle)
    }

    override fun onCalculateTimeTaken(selectedPlanetMap: HashMap<Int, PlanetModel>, selectedVehicleMap: HashMap<Int, VehiclesModel>): Int {
        var timeTaken = 0
        for (key in selectedVehicleMap.keys) {
            val planet = selectedPlanetMap.get(key)
            val vehicle = selectedVehicleMap.get(key)

            timeTaken += planet!!.distance / vehicle!!.speed
        }
        return timeTaken
    }

    override fun onEnableFindFalcone(selectedPlanetMap: HashMap<Int, PlanetModel>, selectedVehicleMap: HashMap<Int, VehiclesModel>) {
        if(selectedVehicleMap.size == 4) {
            iFindFalconeView.onFindFalconeEnabledResult(onGetSelectedPlanetArray(selectedPlanetMap), onGetSelectedVehicleArray(selectedVehicleMap))
        }
    }

    override fun onGetSelectedPlanetArray(selectedPlanetMap: HashMap<Int, PlanetModel>): Array<String> {
        val selectedPlanetArray = arrayOf("", "", "", "")
        for (i in 0..selectedPlanetMap.values.size-1) {
            selectedPlanetArray.set(i, selectedPlanetMap.get(i)!!.name)
        }
        return selectedPlanetArray
    }

    override fun onGetSelectedVehicleArray(selectedVehicleMap: HashMap<Int, VehiclesModel>): Array<String> {
        val selectedVehicleArray = arrayOf("", "", "", "")
        for (i in 0..selectedVehicleMap.values.size-1) {
            selectedVehicleArray.set(i, selectedVehicleMap.get(i)!!.name)
        }
        return selectedVehicleArray
    }
}