package com.example.learn.nabtati.presentation.pages.home

sealed class PlantsListEvents {
    object Refresh : PlantsListEvents()
    object SwipeTipCard: PlantsListEvents()
}