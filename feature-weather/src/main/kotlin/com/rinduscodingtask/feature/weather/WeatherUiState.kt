package com.rinduscodingtask.feature.weather

import com.rinduscodingtask.core.model.CurrentWeather

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val currentWeather: CurrentWeather) : WeatherUiState
    data class Error(val errorMessage: String) : WeatherUiState
}
