package com.example.feature.cats.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.feature.cats.CatsRoute
import com.example.feature.cats.catImage.CatImageRoute

fun NavGraphBuilder.addCatsGraph(navController: NavController) {
    navigation(
        startDestination = "cats_route",
        route = "cats_graph_route"
    ) {
        addCats(navController)
        addCatImage()
    }
}

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

private fun NavGraphBuilder.addCatImage() {
    composable(
        route = "cat_image_route/{catId}",
        arguments = listOf(navArgument("catId") { type = NavType.StringType })
    ) { backStackEntry ->
        CatImageRoute(catId = backStackEntry.arguments?.getString("catId") ?: "")
    }
}
