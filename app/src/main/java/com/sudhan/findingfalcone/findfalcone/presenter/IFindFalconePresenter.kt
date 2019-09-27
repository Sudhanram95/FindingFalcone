package com.sudhan.findingfalcone.findfalcone.presenter

import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel

interface IFindFalconePresenter {
    fun onGetPlanetList()
    fun onGetVehicleList()
    fun onGetFilteredPlanetList(selectedPlanetList: HashMap<Int, PlanetModel>): ArrayList<PlanetModel>
    fun onGetFilteredVehicleList(distanceOfPlanet: Int, selectedVehicle: VehiclesModel?): ArrayList<VehiclesModel>
    fun onUpdateVehicleCount(previousSelectedVehicle: VehiclesModel?, recentSelectedVehicle: VehiclesModel)
    fun onCalculateTimeTaken(selectedPlanetMap: HashMap<Int, PlanetModel>, selectedVehicleMap: HashMap<Int, VehiclesModel>): Int
    fun onEnableFindFalcone(selectedPlanetMap: HashMap<Int, PlanetModel>, selectedVehicleMap: HashMap<Int, VehiclesModel>)
    fun onGetSelectedPlanetArray(selectedPlanetMap: HashMap<Int, PlanetModel>): Array<String>
    fun onGetSelectedVehicleArray(selectedVehicleMap: HashMap<Int, VehiclesModel>): Array<String>
}