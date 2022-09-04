package com.rinduscodingtask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rinduscodingtask.BottomNavigationBar
import com.rinduscodingtask.feature.caweb.navigation.addCaWebGraph
import com.rinduscodingtask.feature.weather.navigation.addWeatherGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = "weather_graph_route"
            ) {
                addWeatherGraph()
                addCaWebGraph()
            }
        }
    }
}
