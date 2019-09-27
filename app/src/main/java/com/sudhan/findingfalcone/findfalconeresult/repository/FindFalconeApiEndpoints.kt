package com.sudhan.findingfalcone.findfalconeresult.repository

import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeRequest
import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeResponse
import com.sudhan.findingfalcone.findfalconeresult.model.TokenModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FindFalconeApiEndpoints {
    @Headers("Accept: application/json")
    @POST("token")
    fun getToken(): Single<TokenModel>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("find")
    fun getFindFalconeResult(@Body findFalconeRequest: FindFalconeRequest): Single<FindFalconeResponse>
}