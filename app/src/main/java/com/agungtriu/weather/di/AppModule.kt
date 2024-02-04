package com.agungtriu.weather.di

import com.agungtriu.weather.data.remote.ApiServiceGeo
import com.agungtriu.weather.data.remote.ApiServiceWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApiServiceWeather(): ApiServiceWeather =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/3.0/")
            .build()
            .create(ApiServiceWeather::class.java)

    @Singleton
    @Provides
    fun provideApiServiceGeo(): ApiServiceGeo =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://get.geojs.io/v1/ip/")
            .build()
            .create(ApiServiceGeo::class.java)
}