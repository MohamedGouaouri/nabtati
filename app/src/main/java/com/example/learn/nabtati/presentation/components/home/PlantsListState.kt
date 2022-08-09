package com.example.learn.nabtati.presentation.components.home

import com.example.learn.nabtati.domain.model.Plant

data class PlantsListState(
    val isLoading: Boolean = false,
    val plants: List<Plant> = emptyList(),
    val error: String = ""
)

