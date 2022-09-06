package com.rinduscodingtask.feature.caweb.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rinduscodingtask.feature.caweb.CaWebRoute

fun NavGraphBuilder.addCaWebGraph() {
    navigation(
        startDestination = "ca_web_route",
        route = "ca_web_graph_route"
    ) {
        addCaWeb()
    }
}

private fun NavGraphBuilder.addCaWeb() {
    composable(
        route = "ca_web_route"
    ) {
        CaWebRoute()
    }
}
