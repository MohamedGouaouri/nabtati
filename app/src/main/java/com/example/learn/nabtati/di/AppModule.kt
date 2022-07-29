package com.example.learn.nabtati.di

import com.example.learn.nabtati.common.Constants
import com.example.learn.nabtati.data.remote.dto.NabtatiApi
import com.example.learn.nabtati.data.repository.PlantRepository
import com.example.learn.nabtati.domain.repository.PlantRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): NabtatiApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: NabtatiApi): PlantRepository{
        return PlantRepositoryImpl(api)
    }
}