package com.agungtriu.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceWeather {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") token: String,
        @Query("exclude") exclude: String? = null,
        @Query("units") units: String? = null,
        @Query("lang") lang: String? = null
    ): WeatherResponse
}