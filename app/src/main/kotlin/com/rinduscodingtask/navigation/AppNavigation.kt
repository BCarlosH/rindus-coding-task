package com.rinduscodingtask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            // TODO: remove AnimatedNavHost
            NavHost(
                navController = navController,
                startDestination = "TODO: add start destination"
            ) {
                // TODO: Add graph
            }
        }
    }
}
