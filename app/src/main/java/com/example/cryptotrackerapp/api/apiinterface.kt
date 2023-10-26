package com.example.cryptotrackerapp.api

import com.example.cryptotrackerapp.models.MarketModelItem
import retrofit2.Response

import retrofit2.http.GET

interface apiinterface {

    @GET("v1/coins")
    suspend fun getMarketData():Response<MarketModelItem>
}