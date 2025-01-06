package co.kr.hoyaho.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.main.ui.SubwayApp
import co.kr.hoyaho.main.ui.rememberSubwayAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberSubwayAppState()

            SubwayTheme {
                SubwayApp(appState)
            }
        }
    }
}
