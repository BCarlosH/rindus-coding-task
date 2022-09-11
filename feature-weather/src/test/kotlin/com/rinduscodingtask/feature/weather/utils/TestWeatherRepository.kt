package com.rinduscodingtask.feature.weather.utils

import com.rinduscodingtask.core.data.repository.WeatherRepository
import com.rinduscodingtask.core.data.utils.Result
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestWeatherRepository : WeatherRepository {

    /**
     * Backing hot flows for testing.
     */
    private val currentWeatherFlow: MutableSharedFlow<Result<CurrentWeather>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val forecastListFlow: MutableSharedFlow<Result<List<Forecast>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getCurrentWeather(): Flow<Result<CurrentWeather>> = currentWeatherFlow

    override fun getFiveDaysForecast(): Flow<Result<List<Forecast>>> = forecastListFlow

    /**
     * A test-only APIs to allow controlling the data tests.
     */
    fun sendCurrentWeather(currentWeather: Result<CurrentWeather>) {
        currentWeatherFlow.tryEmit(currentWeather)
    }
    fun sendFiveDaysForecast(forecast: Result<List<Forecast>>) {
        forecastListFlow.tryEmit(forecast)
    }
}
