package com.rinduscodingtask.core.model

data class Forecast(
    val imageUrl: String,
    val day: String,
    val hour: String,
    val maxTemperature: Double,
    val minTemperature: Double
)
