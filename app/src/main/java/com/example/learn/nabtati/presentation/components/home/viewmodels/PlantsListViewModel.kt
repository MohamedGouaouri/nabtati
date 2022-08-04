package com.example.learn.nabtati.presentation.components.home.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.data.remote.dto.PlantDto
import com.example.learn.nabtati.domain.model.Plant
import com.example.learn.nabtati.domain.usecases.GetPlantsUseCase
import com.example.learn.nabtati.presentation.components.home.PlantsListState
import com.example.learn.nabtati.sockets.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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

        try {
            var plants: List<Plant>
            SocketHandler.getSocket().emit("fetch_plants", "get")
            SocketHandler.getSocket().on("fetch_plants"){ args ->
                CoroutineScope(Dispatchers.Main).launch {
                    plants = Plant.fromJSONList(args[0] as JSONArray)
                    Log.d("SOCKET PLANTS", plants[0].name )
                    _state.value = PlantsListState(plants = plants.toMutableList())

                }
            }
            SocketHandler.closeConnection()

        }catch (_: Exception){
            SocketHandler.closeConnection()
        }

//        getPlantsUseCase().onEach { result ->
//
//            Log.d("GET PLANTS", "Called Inside")
//
//            when (result) {
//
//                is Resource.Success -> {
//                    Log.d("GET PLANTS SUCCESS", "Called getPlants")
//                    _state.value = PlantsListState(plants = result.data ?: emptyList())
//                }
//
//                is Resource.Loading -> {
//                    Log.d("GET PLANTS LOADING", "Called getPlants")
//                    _state.value = PlantsListState(isLoading = true)
//                }
//
//                is Resource.Error -> {
//                    Log.d("GET PLANTS ERROR", "Called getPlants")
//                    _state.value = PlantsListState(error = result.message ?: "An unexpected error occurred")
//                }
//
//            }
//        }
    }

    fun addPlant(){
        try {
            SocketHandler.getSocket().on("fetch_new_plant"){ args->
                CoroutineScope(Dispatchers.Main).launch {
                    val plant = Plant.fromJSON(args[0].toString())
                    _state.value.plants.add(plant)
                }
            }
        }catch (e: SocketException){
            SocketHandler.closeConnection()
        }
    }

}