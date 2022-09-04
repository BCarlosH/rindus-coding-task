package com.rinduscodingtask.core.network.restApi

import com.rinduscodingtask.core.network.model.NetworkCurrentWeather

interface NetworkWeatherDataSource {
    suspend fun getCurrentWeather(): NetworkCurrentWeather
}
