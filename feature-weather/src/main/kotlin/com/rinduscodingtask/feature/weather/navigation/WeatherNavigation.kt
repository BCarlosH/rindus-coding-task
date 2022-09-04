package com.rinduscodingtask.feature.weather.navigation

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

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
        // TODO: implement actual route
        Text(text = "weather_route")
    }
}
