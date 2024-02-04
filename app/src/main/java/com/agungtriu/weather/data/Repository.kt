package com.agungtriu.weather.data

import com.agungtriu.weather.data.remote.GeoResponse
import com.agungtriu.weather.data.remote.WeatherResponse

interface Repository {
    suspend fun getLocation(): GeoResponse
    suspend fun getWeather(
        lat: String,
        lon: String,
        exclude: String?,
        units: String?,
        lang: String?
    ): WeatherResponse
}