package com.rinduscodingtask.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkFiveDaysForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    @SerializedName("list")
    val forecastList: List<NetworkForecast>,
    val message: Int
)
