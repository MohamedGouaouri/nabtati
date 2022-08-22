package com.example.learn.nabtati.domain.usecases

import android.util.Log
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.domain.model.Tip
import com.example.learn.nabtati.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTipUseCase @Inject constructor(
    private val repository: PlantRepository
) {

    fun exec(): Flow<Resource<Tip>> = flow {
        try {
            emit(Resource.Loading())
            val tip = repository.getTip()
            if (tip != null) {
                Log.d("TIP", tip.tip)
            }
            emit(Resource.Success(tip))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}