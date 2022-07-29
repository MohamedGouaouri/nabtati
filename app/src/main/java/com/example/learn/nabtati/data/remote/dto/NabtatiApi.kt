package com.example.learn.nabtati.data.remote.dto

import retrofit2.http.GET

interface NabtatiApi {

    @GET("/plants")
    suspend fun getPlants(): List<PlantDto>

//    @GET("/v1/coins/{coinId}")
//    suspend fun getCoinDetails(@Path("coinId") coinId: String): PlantDetailsDto

}