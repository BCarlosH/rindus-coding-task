package com.example.templateusingjetpackcompose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.feature.cats.navigation.addCatsGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            NavHost(
                navController = navController,
                startDestination = "cats_graph_route"
            ) {
                
            }
        }
    }
}
