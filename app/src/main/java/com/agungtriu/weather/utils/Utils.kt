package com.agungtriu.weather.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utils {
    fun millisToClock(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis * 1000L)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return formatter.format(localDateTime)
    }

    fun millisToDate(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis * 1000L)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("d MMM")
        return localDateTime.format(formatter)
    }
}