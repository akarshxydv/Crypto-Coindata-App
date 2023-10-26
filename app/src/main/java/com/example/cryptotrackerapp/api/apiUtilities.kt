package com.example.cryptotrackerapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiUtilities {
    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Objects.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}