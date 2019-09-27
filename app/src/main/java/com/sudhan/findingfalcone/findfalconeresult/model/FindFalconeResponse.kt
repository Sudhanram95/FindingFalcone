package com.sudhan.findingfalcone.findfalconeresult.model

import com.google.gson.annotations.SerializedName

class FindFalconeResponse {
    @SerializedName("planet_name")
    lateinit var planetName: String

    @SerializedName("status")
    lateinit var status: String
}