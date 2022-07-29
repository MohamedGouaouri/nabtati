package com.example.learn.nabtati.domain.usecases

import android.util.Log
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.data.repository.PlantRepository
import com.example.learn.nabtati.domain.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlantsUseCase @Inject constructor(
 private val repository: PlantRepository
){
    operator fun invoke(): Flow<Resource<List<Plant>>> = flow{
        Log.d("PLANTS INVOKE", "Getting data")
        try {
            emit(Resource.Loading())
            val plants = repository.getPlants()?.map {
                it.toPlant()
            }
            emit(Resource.Success(plants))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}