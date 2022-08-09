package com.example.learn.nabtati.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.learn.nabtati.domain.model.Plant


@Dao
interface PlantsDao {

    @Insert
    fun insertPlant(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

}