package com.example.learn.nabtati.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.learn.nabtati.data.db.daos.PlantsDao
import com.example.learn.nabtati.domain.model.Plant

@Database(entities = [Plant::class], version = 1)
@TypeConverters(Converters::class)
abstract class roomDB: RoomDatabase() {

    abstract fun getPlantsDao() : PlantsDao

    companion object {
        @Volatile
        private var INSTANCE: roomDB? = null
        fun buildDatabase(context: Context): roomDB? {
            if (INSTANCE == null) { synchronized(this) {
                INSTANCE = Room.databaseBuilder(context, roomDB::class.java, "tdm_project")

                    .allowMainThreadQueries().build() } }
            return INSTANCE }
    }
}