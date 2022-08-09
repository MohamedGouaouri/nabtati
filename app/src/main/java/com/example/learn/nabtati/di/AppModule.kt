package com.example.learn.nabtati.di

import com.example.learn.nabtati.common.Constants
import com.example.learn.nabtati.data.remote.apis.NabtatiApi
import com.example.learn.nabtati.domain.repository.PlantRepository
import com.example.learn.nabtati.data.repository.PlantRepositoryImpl
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
    fun provideNabtatiApi(): NabtatiApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePlantRepository(api: NabtatiApi): PlantRepository {
        return PlantRepositoryImpl(api)
    }
}