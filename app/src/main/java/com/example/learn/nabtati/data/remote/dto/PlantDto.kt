package com.example.learn.nabtati.data.remote.dto

import com.example.learn.nabtati.domain.model.Plant

class PlantDto (
    val name: String,
    private val family: String,
    private val price: Double,
    private val image: String
){
    fun toPlant(): Plant {
        return Plant(name, family, price, image)
    }

}