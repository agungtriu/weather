package com.agungtriu.weather.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @field:SerializedName("current")
    val current: Current? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("timezone_offset")
    val timezoneOffset: Float? = null,

    @field:SerializedName("daily")
    val daily: List<DailyItem>? = null,

    @field:SerializedName("lon")
    val lon: Any? = null,

    @field:SerializedName("hourly")
    val hourly: List<HourlyItem>? = null,

    @field:SerializedName("minutely")
    val minutely: List<MinutelyItem>? = null,

    @field:SerializedName("lat")
    val lat: Any? = null
)

data class MinutelyItem(

    @field:SerializedName("dt")
    val dt: Float? = null,

    @field:SerializedName("precipitation")
    val precipitation: Any? = null
)

data class Current(

    @field:SerializedName("rain")
    val rain: Rain? = null,

    @field:SerializedName("sunrise")
    val sunrise: Float? = null,

    @field:SerializedName("temp")
    val temp: Any? = null,

    @field:SerializedName("visibility")
    val visibility: Float? = null,

    @field:SerializedName("uvi")
    val uvi: Any? = null,

    @field:SerializedName("pressure")
    val pressure: Float? = null,

    @field:SerializedName("clouds")
    val clouds: Float? = null,

    @field:SerializedName("feels_like")
    val feelsLike: Any? = null,

    @field:SerializedName("dt")
    val dt: Float? = null,

    @field:SerializedName("wind_deg")
    val windDeg: Float? = null,

    @field:SerializedName("dew_poFloat")
    val dewPoFloat: Any? = null,

    @field:SerializedName("sunset")
    val sunset: Float? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    @field:SerializedName("humidity")
    val humidity: Float? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Any? = null
)

data class FeelsLike(

    @field:SerializedName("eve")
    val eve: Any? = null,

    @field:SerializedName("night")
    val night: Any? = null,

    @field:SerializedName("day")
    val day: Any? = null,

    @field:SerializedName("morn")
    val morn: Any? = null
)

data class WeatherItem(

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("main")
    val main: String? = null,

    @field:SerializedName("id")
    val id: Float? = null
)

data class HourlyItem(

    @field:SerializedName("rain")
    val rain: Rain? = null,

    @field:SerializedName("temp")
    val temp: Float? = null,

    @field:SerializedName("visibility")
    val visibility: Float? = null,

    @field:SerializedName("uvi")
    val uvi: Any? = null,

    @field:SerializedName("pressure")
    val pressure: Float? = null,

    @field:SerializedName("clouds")
    val clouds: Float? = null,

    @field:SerializedName("feels_like")
    val feelsLike: Any? = null,

    @field:SerializedName("wind_gust")
    val windGust: Any? = null,

    @field:SerializedName("dt")
    val dt: Float? = null,

    @field:SerializedName("pop")
    val pop: Float? = null,

    @field:SerializedName("wind_deg")
    val windDeg: Float? = null,

    @field:SerializedName("dew_poFloat")
    val dewPoFloat: Any? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    @field:SerializedName("humidity")
    val humidity: Float? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Any? = null
)

data class Rain(

    @field:SerializedName("1h")
    val jsonMember1h: Any? = null
)

data class Temp(

    @field:SerializedName("min")
    val min: Float? = null,

    @field:SerializedName("max")
    val max: Float? = null,

    @field:SerializedName("eve")
    val eve: Float? = null,

    @field:SerializedName("night")
    val night: Float? = null,

    @field:SerializedName("day")
    val day: Float? = null,

    @field:SerializedName("morn")
    val morn: Float? = null
)

data class DailyItem(

    @field:SerializedName("moonset")
    val moonset: Float? = null,

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("rain")
    val rain: Any? = null,

    @field:SerializedName("sunrise")
    val sunrise: Float? = null,

    @field:SerializedName("temp")
    val temp: Temp? = null,

    @field:SerializedName("moon_phase")
    val moonPhase: Any? = null,

    @field:SerializedName("uvi")
    val uvi: Any? = null,

    @field:SerializedName("moonrise")
    val moonrise: Float? = null,

    @field:SerializedName("pressure")
    val pressure: Float? = null,

    @field:SerializedName("clouds")
    val clouds: Float? = null,

    @field:SerializedName("feels_like")
    val feelsLike: FeelsLike? = null,

    @field:SerializedName("wind_gust")
    val windGust: Any? = null,

    @field:SerializedName("dt")
    val dt: Long? = null,

    @field:SerializedName("pop")
    val pop: Float? = null,

    @field:SerializedName("wind_deg")
    val windDeg: Float? = null,

    @field:SerializedName("dew_poFloat")
    val dewPoFloat: Any? = null,

    @field:SerializedName("sunset")
    val sunset: Float? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    @field:SerializedName("humidity")
    val humidity: Float? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Any? = null
)
