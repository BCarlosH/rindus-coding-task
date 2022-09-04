package com.rinduscodingtask.core.data.repository

import com.rinduscodingtask.core.data.utils.Result
import com.rinduscodingtask.core.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(): Flow<Result<CurrentWeather>>
}
