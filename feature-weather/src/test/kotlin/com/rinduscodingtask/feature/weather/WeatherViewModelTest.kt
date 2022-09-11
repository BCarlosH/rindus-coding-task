package com.rinduscodingtask.feature.weather

import com.rinduscodingtask.core.data.utils.Result
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import com.rinduscodingtask.feature.weather.utils.MainDispatcherRule
import com.rinduscodingtask.feature.weather.utils.TestWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val weatherRepository = TestWeatherRepository()
    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        viewModel = WeatherViewModel(weatherRepository = weatherRepository)
    }

    @Test
    fun uiStateWeather_whenInitialized_thenIsLoading() = runTest {
        assertEquals(
            WeatherUiState(
                CurrentWeatherUiState.Loading,
                FiveDaysForecastUiState.Loading
            ),
            viewModel.weatherUiState.value
        )
    }

    @Test
    fun uiStateWeather_whenSuccess_matchesWeatherFromRepository() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.weatherUiState.collect() }
        weatherRepository.sendCurrentWeather(successCurrentWeather)
        weatherRepository.sendFiveDaysForecast(successFiveDaysForecast)

        val weatherUiState = viewModel.weatherUiState.value

        assertTrue(weatherUiState.currentWeatherUiState is CurrentWeatherUiState.Success)
        val successCurrentWeatherUiState =
            weatherUiState.currentWeatherUiState as CurrentWeatherUiState.Success
        assertEquals(
            successCurrentWeather.data,
            successCurrentWeatherUiState.currentWeather
        )

        assertTrue(weatherUiState.fiveDaysForecastUiState is FiveDaysForecastUiState.Success)
        val successFiveDaysForecastUiState =
            weatherUiState.fiveDaysForecastUiState as FiveDaysForecastUiState.Success
        assertEquals(
            successFiveDaysForecast.data,
            successFiveDaysForecastUiState.fiveDaysForecast
        )

        collectJob.cancel()
    }

    @Test
    fun uiStateWeather_whenError_matchesResponseFromRepository() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.weatherUiState.collect() }
        weatherRepository.sendCurrentWeather(errorCurrentWeather)
        weatherRepository.sendFiveDaysForecast(errorFiveDaysForecast)

        val weatherUiState = viewModel.weatherUiState.value

        assertTrue(weatherUiState.currentWeatherUiState is CurrentWeatherUiState.Error)
        val errorCurrentWeatherUiState =
            weatherUiState.currentWeatherUiState as CurrentWeatherUiState.Error
        assertEquals(
            errorCurrentWeather.errorMessage,
            errorCurrentWeatherUiState.errorMessage
        )

        assertTrue(weatherUiState.fiveDaysForecastUiState is FiveDaysForecastUiState.Error)
        val errorFiveDaysForecastUiState =
            weatherUiState.fiveDaysForecastUiState as FiveDaysForecastUiState.Error
        assertEquals(
            errorFiveDaysForecast.errorMessage,
            errorFiveDaysForecastUiState.errorMessage
        )

        collectJob.cancel()
    }
}

private val successCurrentWeather = Result.Success(
    CurrentWeather(
        imageUrl = "",
        currentTemperature = 0.0,
        humidity = 1,
        pressure = 1,
        windSpeed = 0.0
    )
)

private val successFiveDaysForecast = Result.Success(
    listOf(
        Forecast(
            imageUrl = "",
            day = "",
            hour = "",
            maxTemperature = 0.0,
            minTemperature = 0.0
        ),
        Forecast(
            imageUrl = "",
            day = "",
            hour = "",
            maxTemperature = 0.0,
            minTemperature = 0.0
        )
    )
)

private val errorCurrentWeather = Result.Error(
    "error"
)

private val errorFiveDaysForecast = Result.Error(
    "error"
)
