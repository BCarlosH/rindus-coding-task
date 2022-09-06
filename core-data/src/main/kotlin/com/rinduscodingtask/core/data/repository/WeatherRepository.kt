package com.rinduscodingtask.core.data.repository

import com.rinduscodingtask.core.data.utils.Result
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(): Flow<Result<CurrentWeather>>
    fun getFiveDaysForecast(): Flow<Result<List<Forecast>>>
}
