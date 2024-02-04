package com.agungtriu.weather.data.remote

import retrofit2.http.GET

interface ApiServiceGeo {
    @GET("geo.json")
    suspend fun getLocation(): GeoResponse
}