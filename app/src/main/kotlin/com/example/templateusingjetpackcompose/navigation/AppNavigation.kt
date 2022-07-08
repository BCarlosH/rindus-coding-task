package com.example.templateusingjetpackcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.cats.navigation.addCatsGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberAnimatedNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = "cats_graph_route"
            ) {
                addCatsGraph(navController)
            }
        }
    }
}
