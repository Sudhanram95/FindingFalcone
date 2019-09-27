package com.sudhan.findingfalcone.findfalcone.repository

import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import io.reactivex.Single
import retrofit2.http.GET

interface GetPlanetAndVehicleApiEndpoints {

    @GET("planets")
    fun getPlanets(): Single<ArrayList<PlanetModel>>

    @GET("vehicles")
    fun getVehicles(): Single<ArrayList<VehiclesModel>>
}