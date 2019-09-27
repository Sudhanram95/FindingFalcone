package com.sudhan.findingfalcone.findfalcone.model

import com.google.gson.annotations.SerializedName

class VehiclesModel {

    @SerializedName("name")
    lateinit var name: String
    @SerializedName("total_no")
    var totalNumber: Int = 0
    @SerializedName("max_distance")
    var maxDistance: Int = 0
    @SerializedName("speed")
    var speed: Int = 0
}