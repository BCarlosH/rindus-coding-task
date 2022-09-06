package com.rinduscodingtask.core.network.restApi

import com.rinduscodingtask.core.network.model.NetworkCurrentWeather
import com.rinduscodingtask.core.network.model.NetworkFiveDaysForecast

interface NetworkWeatherDataSource {
    suspend fun getCurrentWeather(): NetworkCurrentWeather
    suspend fun getFiveDaysForecast(): NetworkFiveDaysForecast
}
