package com.example.learn.nabtati.domain.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

data class Plant(
    val name: String,
    val family: String,
    val price: Double,
    val image: String
){
    companion object{
        fun fromJSON(jsonString: String): Plant{
            val gson = Gson()
            return gson.fromJson(jsonString, Plant::class.java)
        }

        fun fromJSONList(jsonString: JSONArray): List<Plant> {
            val gson = Gson()
            val typeToken = object : TypeToken<List<Plant>>() {}.type

            return gson.fromJson(jsonString.toString(), typeToken)
        }
    }

}