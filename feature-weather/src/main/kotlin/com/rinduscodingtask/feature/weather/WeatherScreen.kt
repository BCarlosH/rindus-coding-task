package com.rinduscodingtask.feature.weather

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.ui.LoadingScreen
import com.rinduscodingtask.core.ui.rememberFlowWithLifecycle

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherUiState by rememberFlowWithLifecycle(flow = viewModel.weatherUiState)
        .collectAsState(initial = WeatherUiState.Loading)

    WeatherScreen(
        weatherUiState = weatherUiState
    )
}

@Composable
private fun WeatherScreen(
    weatherUiState: WeatherUiState
) {
    when (weatherUiState) {
        is WeatherUiState.Loading -> {
            LoadingScreen()
        }
        is WeatherUiState.Success -> {
            WeatherContent(
                weatherUiState.currentWeather
            )
        }
        is WeatherUiState.Error -> {
            Text(
                text = weatherUiState.errorMessage,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun WeatherContent(
    currentWeather: CurrentWeather
) {
    // TODO: implement content
    Text(text = currentWeather.toString())
}
