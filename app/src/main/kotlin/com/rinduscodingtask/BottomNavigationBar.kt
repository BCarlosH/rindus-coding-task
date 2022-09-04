package com.rinduscodingtask

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
internal fun BottomNavigationBar(
    navController: NavHostController
) {
    BottomNavigation {
        val currentRoute = navController.currentRoute()

        BottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(id = item.iconResId), stringResource(id = item.labelResId))
                },
                label = {
                    Text(text = stringResource(id = item.labelResId))
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.6f),
                alwaysShowLabel = true,
                selected = currentRoute == item.destination,
                onClick = {
                    if (currentRoute != item.destination) {
                        navController.navigate(item.destination) {
                            restoreState = true
                            /**
                             * Pop up to the start destination of the graph to avoid building up
                             * a large stack of destinations.
                             *
                             * In case of the requirement being every time the user press the back
                             * button the app closes, then we can use popUpTo(0).
                             */
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun NavHostController.currentRoute(): String? {
    val navDestination = currentBackStackEntryAsState().value?.destination ?: return null

    val bottomBarRoutes = BottomNavigationItems.map { it.destination }
    return navDestination.hierarchy.find { it.route in bottomBarRoutes }?.route
}

internal val BottomNavigationItems = listOf(
    BottomNavigationItem(
        destination = "weather_route",
        labelResId = R.string.weather_item,
        iconResId = R.drawable.ic_action_weather
    ),
    BottomNavigationItem(
        destination = "ca_web_route",
        labelResId = R.string.ca_web_item,
        iconResId = R.drawable.ic_action_ca_web
    )
)

internal class BottomNavigationItem(
    val destination: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
)
