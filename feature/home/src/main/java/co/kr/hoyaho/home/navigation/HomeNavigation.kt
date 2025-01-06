package co.kr.hoyaho.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import co.kr.hoyaho.home.HomeRoute
import co.kr.hoyaho.navigation.Route

private typealias HomeRoute = Route.Home

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(
    route = HomeRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
) {
    composable<HomeRoute> {
        HomeRoute(padding)
    }
}
