package co.kr.hoyaho.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberSubwayAppState(): SubwayAppState {
    return remember { SubwayAppState() }
}

@Stable
class SubwayAppState
