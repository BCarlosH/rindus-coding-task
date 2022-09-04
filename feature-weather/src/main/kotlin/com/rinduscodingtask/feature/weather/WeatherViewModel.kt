package com.rinduscodingtask.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rinduscodingtask.core.data.repository.WeatherRepository
import com.rinduscodingtask.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherUiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState

    init {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            weatherRepository.getCurrentWeather()
                .collect { result ->
                    _weatherUiState.value = when (result) {
                        is Result.Success -> WeatherUiState.Success(result.data)
                        is Result.Error -> WeatherUiState.Error(result.errorMessage)
                    }
                }
        }
    }
}
