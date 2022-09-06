package com.rinduscodingtask.core.model

data class Forecast(
    val iconId: String,
    val day: String,
    val hour: String,
    val maxTemperature: Double,
    val minTemperature: Double
)
