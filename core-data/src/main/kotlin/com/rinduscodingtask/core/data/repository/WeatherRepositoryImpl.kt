package com.rinduscodingtask.core.data.repository

import com.rinduscodingtask.core.data.errorHandler.ErrorHandler
import com.rinduscodingtask.core.data.utils.Result
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.network.model.NetworkCurrentWeather
import com.rinduscodingtask.core.network.restApi.NetworkWeatherDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val networkWeatherDataSource: NetworkWeatherDataSource,
    private val errorHandler: ErrorHandler
) : WeatherRepository {

    override suspend fun getCurrentWeather(): Flow<Result<CurrentWeather>> {
        return flow<Result<CurrentWeather>> {
            emit(Result.Success(networkWeatherDataSource.getCurrentWeather().asCurrentWeather()))
        }
            .catch { emit(Result.Error(errorHandler.parseError(it))) }
            .flowOn(Dispatchers.IO)
    }
}

private fun NetworkCurrentWeather.asCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        iconId = weather.first().icon,
        currentTemperature = main.temp,
        maxTemperature = main.temp_max,
        minTemperature = main.temp_min,
        humidity = main.humidity,
        pressure = main.pressure,
        windSpeed = wind.speed
    )
}
