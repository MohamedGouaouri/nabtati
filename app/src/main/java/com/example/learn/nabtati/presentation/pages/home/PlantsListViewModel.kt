package com.example.learn.nabtati.presentation.pages.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.data.db.roomDB
import com.example.learn.nabtati.domain.model.Plant
import com.example.learn.nabtati.domain.usecases.GetPlantsUseCase
import com.example.learn.nabtati.domain.usecases.GetTipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlantsListViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val getTipUseCase: GetTipUseCase
): ViewModel() {

    private var _state = mutableStateOf(PlantsListState())
    var state = _state


    init {
        getPlants()
        getTip()
    }

    private fun getPlants(){

        getPlantsUseCase().onEach { result ->


            when (result) {

                is Resource.Success -> {
                    _state.value = _state.value.copy(plants = result.data ?: emptyList(), isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }

            }
        }.launchIn(viewModelScope)
    }


    private fun getTip(){
        getTipUseCase.exec().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(tip = result.data, isLoading = false)
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }




    fun onEvent(event: PlantsListEvents) {
        when(event) {
            is PlantsListEvents.Refresh -> {
                getPlants()
            }
            is PlantsListEvents.SwipeTipCard -> {
                _state.value = _state.value.copy(displayTip = false)
            }
        }
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