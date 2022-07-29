package com.example.learn.nabtati.presentation.routes

sealed class Screens (val route: String) {
    object PlantsListScreen : Screens("plants_list_screen")
    object PlantDetailScreen : Screens("plant_details_screen")
}
