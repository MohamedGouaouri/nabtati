package com.example.learn.nabtati.data.repository

import com.example.learn.nabtati.data.remote.apis.NabtatiApi
import com.example.learn.nabtati.data.remote.dto.PlantDto
import com.example.learn.nabtati.domain.model.Tip
import com.example.learn.nabtati.domain.repository.PlantRepository
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val api: NabtatiApi
): PlantRepository {
    override suspend fun getPlants(): List<PlantDto> {
        return api.getPlants()
    }

//    override suspend fun getPlantDetails(coinId: String): PlantDetailsDto {
//        return api.getPl
//    }

    override suspend fun getTip(): Tip? {
        return api.getTip()
    }
}