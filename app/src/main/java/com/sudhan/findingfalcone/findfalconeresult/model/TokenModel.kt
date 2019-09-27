package com.sudhan.findingfalcone.findfalconeresult.model

import com.google.gson.annotations.SerializedName

open class TokenModel {
    @SerializedName("token")
    open lateinit var token: String
}