package co.kr.hoyaho.main.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import co.kr.hoyaho.main.navigation.SubwayNavHost

@Composable
fun SubwayApp(
    appState: SubwayAppState,
) {
    Scaffold { padding ->
        SubwayNavHost(
            appState = appState,
            paddingValues = padding,
        )
    }
}
