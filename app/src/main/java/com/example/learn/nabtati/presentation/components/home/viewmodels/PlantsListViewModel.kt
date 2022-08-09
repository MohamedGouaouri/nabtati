package com.example.learn.nabtati.presentation.components.home.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.data.db.roomDB
import com.example.learn.nabtati.domain.model.Plant
import com.example.learn.nabtati.domain.usecases.GetPlantsUseCase
import com.example.learn.nabtati.presentation.components.home.PlantsListState
import com.example.learn.nabtati.sockets.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONArray
import java.net.SocketException
import javax.inject.Inject


@HiltViewModel
class PlantsListViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase
): ViewModel() {

    private val _state = mutableStateOf(PlantsListState())
    val state = _state


    init {
        getPlants()
    }

    private fun getPlants(){


        getPlantsUseCase().onEach { result ->

            Log.d("GET PLANTS", "Called Inside")

            when (result) {

                is Resource.Success -> {
                    Log.d("GET PLANTS SUCCESS", "Called getPlants")
                    _state.value = PlantsListState(plants = result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    Log.d("GET PLANTS LOADING", "Called getPlants")
                    _state.value = PlantsListState(isLoading = true)
                }

                is Resource.Error -> {
                    Log.d("GET PLANTS ERROR", "Called getPlants")
                    _state.value = PlantsListState(error = result.message ?: "An unexpected error occurred")
                }

            }
        }.launchIn(viewModelScope)
    }




    fun savePlant(ctx: Context, plant: Plant) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = ctx.let { roomDB.buildDatabase(it) }
            val plantsDao = db?.getPlantsDao()
            plantsDao?.insertPlant(plant)
        }
    }

    fun deletePlant(ctx: Context, plant: Plant) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = ctx.let { roomDB.buildDatabase(it) }
            val plantsDao = db?.getPlantsDao()
            plantsDao?.deletePlant(plant)
        }
    }

}