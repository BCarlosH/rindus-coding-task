package com.rinduscodingtask.feature.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.header_current_weather),
                modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            when (weatherUiState.currentWeatherUiState) {
                is CurrentWeatherUiState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier
                            .padding(top = 72.dp)
                            .fillMaxWidth()
                    )
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
        }

        Column {
            when (weatherUiState.fiveDaysForecastUiState) {
                is FiveDaysForecastUiState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier
                            .padding(bottom = 72.dp)
                            .fillMaxWidth()
                    )
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
}

@Composable
private fun CurrentWeatherContent(
    currentWeather: CurrentWeather
) {
    Column {
        Row(
            modifier = Modifier
                .height(96.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = currentWeather.imageUrl,
                contentDescription = stringResource(R.string.desc_weather_icon),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
            )
            Text(
                text = stringResource(
                    R.string.label_degrees,
                    currentWeather.currentTemperature.toString().substringBefore(".")
                ),
                fontSize = 32.sp
            )
            Text(
                text = stringResource(R.string.celsius),
                modifier = Modifier
                    .padding(start = 4.dp),
                fontSize = 16.sp
            )
        }

        Text(
            text = stringResource(R.string.label_humidity, currentWeather.humidity),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.label_pressure, currentWeather.pressure),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.label_wind_speed, currentWeather.windSpeed),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun FiveDaysForecastContent(
    fiveDaysForecast: List<Forecast>
) {
    Column {
        Text(
            text = stringResource(R.string.header_forecast),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            modifier = Modifier
                .padding(start = 4.dp, top = 16.dp, end = 4.dp, bottom = 16.dp)
        ) {
            itemsIndexed(fiveDaysForecast) { index, forecast ->
                Column {
                    ForecastItem(
                        day = if (index > 0 && forecast.day == fiveDaysForecast[index.dec()].day) {
                            ""
                        } else {
                            forecast.day
                        },
                        hour = forecast.hour,
                        imageUrl = forecast.imageUrl,
                        maxTemperature = forecast.maxTemperature.toString().substringBefore("."),
                        minTemperature = forecast.minTemperature.toString().substringBefore("."),
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
