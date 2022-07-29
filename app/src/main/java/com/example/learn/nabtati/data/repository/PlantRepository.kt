package com.example.learn.nabtati.data.repository

import com.example.learn.nabtati.data.remote.dto.PlantDto

interface PlantRepository {

    suspend fun getPlants(): List<PlantDto>?

//    suspend fun getPlantDetails(coinId: String): PlantDetailsDto

}