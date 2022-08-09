package com.example.learn.nabtati.data.remote.dto

import com.example.learn.nabtati.domain.model.Plant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlantDto (
    val name: String,
    private val family: String,
    private val price: Double,
    private val image: String
){
    fun toPlant(): Plant {
        return Plant(name = name, family = family, price = price, image = image)
    }


    companion object{
        fun fromJSON(jsonString: String): Plant{
            val gson = Gson()
            return gson.fromJson(jsonString, Plant::class.java)
        }

        fun fromJSONList(jsonString: String): List<PlantDto> {
            val gson = Gson()
            val typeToken = object : TypeToken<List<Plant>>() {}.type
            return gson.fromJson(jsonString, typeToken)
        }
    }

}