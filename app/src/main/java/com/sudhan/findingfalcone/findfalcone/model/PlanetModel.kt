package com.sudhan.findingfalcone.findfalcone.model

import com.google.gson.annotations.SerializedName

class PlanetModel {

    @SerializedName("name")
    var name: String = ""
    @SerializedName("distance")
    var distance: Int = 0

    var isSelected: Boolean = false
}