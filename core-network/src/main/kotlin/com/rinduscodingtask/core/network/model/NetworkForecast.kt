package com.rinduscodingtask.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkForecast(
    val clouds: Clouds,
    @SerializedName("dt")
    val dataTime: Int,
    @SerializedName("dt_txt")
    val dataTimeIso: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
