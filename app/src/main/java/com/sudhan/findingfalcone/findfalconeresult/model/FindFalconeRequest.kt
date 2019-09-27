package com.sudhan.findingfalcone.findfalconeresult.model

import com.google.gson.annotations.SerializedName

class FindFalconeRequest : TokenModel() {
    @SerializedName("planet_names")
    lateinit var planetList: List<String>

    @SerializedName("vehicle_names")
    lateinit var vehicleList: List<String>
}