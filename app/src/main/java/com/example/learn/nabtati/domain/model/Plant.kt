package com.example.learn.nabtati.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

/**
 * Store liked plants in room database (just for learning)
 * */
@Entity(tableName = "plants")
data class Plant(
    val name: String,
    val family: String,
    val price: Double,
    val image: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

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