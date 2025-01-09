package co.kr.hoyaho.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import co.kr.hoyaho.detail.DetailScreen
import co.kr.hoyaho.navigation.Route

private typealias DetailRoute = Route.Detail

fun NavController.navigateToDetail(
    lineNumber: String,
    stationName: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) = navigate(
    route = DetailRoute(lineNumber, stationName),
    builder = navOptions,
)

fun NavGraphBuilder.detailScreen() {
    composable<DetailRoute> { navBackstackEntry ->
        navBackstackEntry.toRoute<DetailRoute>().apply {
            DetailScreen(lineNumber, stationName)
        }
    }
}
