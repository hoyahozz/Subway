package co.kr.hoyaho.main.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import co.kr.hoyaho.home.navigation.homeNavGraph
import co.kr.hoyaho.main.ui.SubwayAppState

@Composable
fun SubwayNavHost(
    appState: SubwayAppState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        NavHost(
            navController = navController,
            startDestination = appState.startDestination,
        ) {
            homeNavGraph(
                padding = paddingValues,
            )
        }
    }
}
