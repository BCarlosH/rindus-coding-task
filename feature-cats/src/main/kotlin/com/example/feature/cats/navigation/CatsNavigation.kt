package com.example.feature.cats.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.feature.cats.CatsRoute
import com.example.feature.cats.catImage.CatImageRoute
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addCatsGraph(navController: NavController) {
    navigation(
        startDestination = "cats_route",
        route = "cats_graph_route"
    ) {
        addCats(navController)
        addCatImage()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addCats(navController: NavController) {
    composable(
        route = "cats_route"
    ) {
        CatsRoute(
            onCatClick = { catId ->
                navController.navigate("cat_image_route/$catId")
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addCatImage() {
    composable(
        route = "cat_image_route/{catId}",
        arguments = listOf(navArgument("catId") { type = NavType.StringType })
    ) { backStackEntry ->
        CatImageRoute(catId = backStackEntry.arguments?.getString("catId") ?: "")
    }
}
