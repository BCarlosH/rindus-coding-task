package com.rinduscodingtask.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rinduscodingtask.core.data.repository.WeatherRepository
import com.rinduscodingtask.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    weatherRepository: WeatherRepository
) : ViewModel() {

    internal val weatherUiState: StateFlow<WeatherUiState> = combine(
        weatherRepository.getCurrentWeather(),
        weatherRepository.getFiveDaysForecast()
    ) { currentWeatherResult, fiveDaysForecastResult ->

        val currentWeather = when (currentWeatherResult) {
            is Result.Success -> CurrentWeatherUiState.Success(currentWeatherResult.data)
            is Result.Error -> CurrentWeatherUiState.Error(currentWeatherResult.errorMessage)
        }

        val fiveDaysForecast = when (fiveDaysForecastResult) {
            is Result.Success -> FiveDaysForecastUiState.Success(fiveDaysForecastResult.data)
            is Result.Error -> FiveDaysForecastUiState.Error(fiveDaysForecastResult.errorMessage)
        }

        WeatherUiState(currentWeather, fiveDaysForecast)

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WeatherUiState(
            CurrentWeatherUiState.Loading,
            FiveDaysForecastUiState.Loading
        )
    )
}
