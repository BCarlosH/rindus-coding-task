package com.rinduscodingtask.core.model

data class CurrentWeather(
    val imageUrl: String,
    val currentTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double
)
