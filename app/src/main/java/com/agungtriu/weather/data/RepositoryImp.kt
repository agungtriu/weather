package com.agungtriu.weather.data

import com.agungtriu.weather.data.remote.ApiServiceGeo
import com.agungtriu.weather.data.remote.ApiServiceWeather
import com.agungtriu.weather.data.remote.GeoResponse
import com.agungtriu.weather.data.remote.WeatherResponse
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiServiceGeo: ApiServiceGeo,
    private val apiServiceWeather: ApiServiceWeather
) : Repository {
    companion object {
        const val TOKEN = "251cb9ad41659a84600f8b17d1d921ad"
    }

    override suspend fun getLocation(): GeoResponse = apiServiceGeo.getLocation()

    override suspend fun getWeather(
        lat: String,
        lon: String,
        exclude: String?,
        units: String?,
        lang: String?
    ): WeatherResponse = apiServiceWeather.getWeather(lat, lon, TOKEN, exclude, units, lang)
}