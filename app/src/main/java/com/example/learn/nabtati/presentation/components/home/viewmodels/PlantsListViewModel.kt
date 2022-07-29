package com.example.learn.nabtati.presentation.components.home.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.learn.nabtati.common.Resource
import com.example.learn.nabtati.domain.usecases.GetPlantsUseCase
import com.example.learn.nabtati.presentation.components.home.PlantsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
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

        Log.d("GET PLANTS", "Called getPlants")

        getPlantsUseCase().onEach { result ->


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
        }
    }
}