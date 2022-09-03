package com.rinduscodingtask.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberAnimatedNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = "TODO: add start destination"
            ) {
                // TODO: Add graph
            }
        }
    }
}
