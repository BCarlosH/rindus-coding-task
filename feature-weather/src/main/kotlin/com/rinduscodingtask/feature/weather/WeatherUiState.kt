package com.rinduscodingtask.feature.weather

import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast

internal data class WeatherUiState(
    val currentWeatherUiState: CurrentWeatherUiState,
    val fiveDaysForecastUiState: FiveDaysForecastUiState
)

internal sealed interface CurrentWeatherUiState {
    object Loading : CurrentWeatherUiState

    data class Success(
        val currentWeather: CurrentWeather
    ) : CurrentWeatherUiState

    data class Error(
        val errorMessage: String
    ) : CurrentWeatherUiState
}

internal sealed interface FiveDaysForecastUiState {
    object Loading : FiveDaysForecastUiState

    data class Success(
        val fiveDaysForecast: List<Forecast>
    ) : FiveDaysForecastUiState

    data class Error(
        val errorMessage: String
    ) : FiveDaysForecastUiState
}
