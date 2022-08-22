package com.example.learn.nabtati.domain.repository

import com.example.learn.nabtati.data.remote.dto.PlantDto
import com.example.learn.nabtati.domain.model.Tip

interface PlantRepository {

    suspend fun getPlants(): List<PlantDto>?

//    suspend fun getPlantDetails(coinId: String): PlantDetailsDto

    suspend fun getTip(): Tip?

}