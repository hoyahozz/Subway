package co.kr.hoyaho.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.kr.hoyaho.navigation.Route

@Composable
fun rememberSubwayAppState(
    navController: NavHostController = rememberNavController(),
): SubwayAppState {
    return remember { SubwayAppState(navController) }
}

@Stable
@Suppress("UnusedPrivateProperty")
class SubwayAppState(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.HomeBase
}
