package com.example.learn.nabtati.presentation.pages.home

import com.example.learn.nabtati.domain.model.Plant
import com.example.learn.nabtati.domain.model.Tip

data class PlantsListState(
    val isLoading: Boolean = false,
    val plants: List<Plant> = emptyList(),
    val tip: Tip? = null,
    val error: String = "",
    // If we add configs than we should get the initial value from the config store
    val displayTip: Boolean = true,
    val isRefreshing: Boolean = false
)

