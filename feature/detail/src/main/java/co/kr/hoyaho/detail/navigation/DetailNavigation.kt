package co.kr.hoyaho.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import co.kr.hoyaho.detail.DetailRoute
import co.kr.hoyaho.navigation.Route

fun NavController.navigateToDetail(
    lineNumber: String,
    stationName: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) = navigate(
    route = Route.Detail(lineNumber, stationName),
    builder = navOptions,
)

fun NavGraphBuilder.detailScreen() {
    composable<Route.Detail> { navBackstackEntry ->
        navBackstackEntry.toRoute<Route.Detail>().apply {
            DetailRoute(lineNumber, stationName)
        }
    }
}
