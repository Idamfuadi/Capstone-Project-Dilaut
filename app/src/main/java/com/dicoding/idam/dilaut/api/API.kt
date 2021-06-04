package com.dicoding.idam.dilaut.api

import com.dicoding.idam.dilaut.fish.FishResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("api_predictFishPrices")

    fun getDataFish(@Query("q") query: String)
            : Call<FishResponse>
}