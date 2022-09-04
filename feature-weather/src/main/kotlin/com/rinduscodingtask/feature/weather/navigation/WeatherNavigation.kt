package com.rinduscodingtask.feature.weather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rinduscodingtask.feature.weather.WeatherRoute

fun NavGraphBuilder.addWeatherGraph() {
    navigation(
        startDestination = "weather_route",
        route = "weather_graph_route"
    ) {
        addWeather()
    }
}

private fun NavGraphBuilder.addWeather() {
    composable(
        route = "weather_route"
    ) {
        WeatherRoute()
    }
}
