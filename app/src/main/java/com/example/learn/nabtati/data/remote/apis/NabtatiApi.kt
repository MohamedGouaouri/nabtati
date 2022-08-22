package com.example.learn.nabtati.data.remote.apis

import android.util.Log
import com.example.learn.nabtati.data.remote.dto.PlantDto
import com.example.learn.nabtati.data.remote.dto.TipDto
import com.example.learn.nabtati.domain.model.Tip
import com.example.learn.nabtati.sockets.SocketHandler
import retrofit2.http.GET

interface NabtatiApi {

    @GET("/plants")
    suspend fun getPlants(): List<PlantDto>

    suspend fun getPlantsWS(): List<PlantDto> {
        try {
            var plants = listOf<PlantDto>()
            SocketHandler.getSocket().emit("fetch_plants", "get")
            SocketHandler.getSocket().on("fetch_plants"){ args ->
                plants = PlantDto.fromJSONList(args[0] as String)
                Log.d("SOCKET PLANTS", args[0].toString() )
            }
            return plants
        }catch (_: Exception){
            SocketHandler.closeConnection()
        }
        return emptyList()
    }



    @GET("/tip")
    suspend fun getTip(): Tip

}