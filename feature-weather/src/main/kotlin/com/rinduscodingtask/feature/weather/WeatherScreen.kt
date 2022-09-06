package com.rinduscodingtask.feature.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import com.rinduscodingtask.core.ui.LoadingScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherUiState: WeatherUiState by viewModel.weatherUiState.collectAsStateWithLifecycle()

    WeatherScreen(
        weatherUiState = weatherUiState
    )
}

@Composable
private fun WeatherScreen(
    weatherUiState: WeatherUiState
) {
    Column {

        when (weatherUiState.currentWeatherUiState) {
            is CurrentWeatherUiState.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxWidth())
            }
            is CurrentWeatherUiState.Success -> {
                CurrentWeatherContent(
                    weatherUiState.currentWeatherUiState.currentWeather
                )
            }
            is CurrentWeatherUiState.Error -> {
                Text(
                    text = weatherUiState.currentWeatherUiState.errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        when (weatherUiState.fiveDaysForecastUiState) {
            is FiveDaysForecastUiState.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxWidth())
            }
            is FiveDaysForecastUiState.Success -> {
                FiveDaysForecastContent(
                    weatherUiState.fiveDaysForecastUiState.fiveDaysForecast
                )
            }
            is FiveDaysForecastUiState.Error -> {
                Text(
                    text = weatherUiState.fiveDaysForecastUiState.errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
private fun CurrentWeatherContent(
    currentWeather: CurrentWeather
) {
    // TODO: implement content
    Text(text = currentWeather.toString())
}

@Composable
private fun FiveDaysForecastContent(
    fiveDaysForecast: List<Forecast>
) {
    // TODO: implement content
    LazyRow {
        items(fiveDaysForecast) {
            Column {
                Text(text = it.day)
                Text(text = it.hour)
                Text(text = it.iconId)
                Text(text = it.maxTemperature.toString())
                Text(text = it.minTemperature.toString())
            }
        }
    }
}
