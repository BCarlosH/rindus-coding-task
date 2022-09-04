package com.rinduscodingtask.core.model

data class CurrentWeather(
    val iconId: String,
    val currentTemperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double
)
