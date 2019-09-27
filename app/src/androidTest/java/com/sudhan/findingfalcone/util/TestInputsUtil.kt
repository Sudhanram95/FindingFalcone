package com.sudhan.findingfalcone.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel
import java.io.IOException
import java.nio.charset.Charset

object TestInputsUtil {

    fun getJson(context: Context, path : String) : String {
        var json = ""
        try {
            val inputStream = context.assets.open(path)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }

    fun getTestPlanetList(): ArrayList<PlanetModel> {
        val planetsJSON = getJson(InstrumentationRegistry.getInstrumentation().context, "getPlanets.json")
        return Gson().fromJson<java.util.ArrayList<PlanetModel>>(planetsJSON,
                object : TypeToken<java.util.ArrayList<PlanetModel>>() {}.type)
    }

    fun getTestVehicleList(): ArrayList<VehiclesModel> {
        val vehicleJSON = getJson(InstrumentationRegistry.getInstrumentation().context, "getVehicles.json")
        return Gson().fromJson<java.util.ArrayList<VehiclesModel>>(vehicleJSON,
                object : TypeToken<java.util.ArrayList<VehiclesModel>>() {}.type)
    }

    fun getTestSelectedPlanetMap(planetList: ArrayList<PlanetModel>): HashMap<Int, PlanetModel> {
        val selectedPlanetMap = HashMap<Int, PlanetModel>()
        selectedPlanetMap.put(0, planetList.get(0))
        selectedPlanetMap.put(1, planetList.get(1))
        selectedPlanetMap.put(2, planetList.get(2))
        selectedPlanetMap.put(3, planetList.get(3))
        return selectedPlanetMap
    }

    fun getTestSelectedVehicleMap(vehicleList: ArrayList<VehiclesModel>): HashMap<Int, VehiclesModel> {
        val selectedVehicleMap = HashMap<Int, VehiclesModel>()
        selectedVehicleMap.put(0, vehicleList.get(0))
        selectedVehicleMap.put(1, vehicleList.get(1))
        selectedVehicleMap.put(2, vehicleList.get(2))
        selectedVehicleMap.put(3, vehicleList.get(3))
        return selectedVehicleMap
    }
}