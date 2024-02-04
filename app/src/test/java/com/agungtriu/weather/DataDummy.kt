package com.agungtriu.weather

import com.agungtriu.weather.data.remote.GeoResponse
import com.agungtriu.weather.data.remote.WeatherResponse
import com.agungtriu.weather.utils.Helper

object DataDummy {
    val dummyLocation = Helper.jsonToObject<GeoResponse>("location.json")
    val dummyWeather = Helper.jsonToObject<WeatherResponse>("weather.json")
}