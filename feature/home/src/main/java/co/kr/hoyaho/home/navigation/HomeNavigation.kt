package co.kr.hoyaho.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import co.kr.hoyaho.home.HomeRoute
import co.kr.hoyaho.navigation.Route

private typealias HomeBaseRoute = Route.HomeBase
private typealias HomeRoute = Route.Home

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(
    route = HomeRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToDetail: (lineNumber: String, stationName: String) -> Unit,
    homeDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeRoute(padding, navigateToDetail)
        }
    }
    homeDestination()
}
