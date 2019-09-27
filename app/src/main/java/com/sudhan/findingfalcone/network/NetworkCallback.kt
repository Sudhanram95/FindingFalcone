package com.sudhan.findingfalcone.network

interface NetworkCallback {
    fun onApiSuccess(response: Any)
    fun onApiFailure(errorMsg: Any)
}