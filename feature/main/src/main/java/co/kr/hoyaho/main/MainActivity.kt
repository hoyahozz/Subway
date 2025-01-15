package co.kr.hoyaho.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.home.HomeScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val backStack = rememberSaveableBackStack(root = HomeScreen)
            val navigator = rememberCircuitNavigator(backStack)

            SubwayTheme {
                CircuitCompositionLocals(circuit) {
                    Scaffold { padding ->
                        NavigableCircuitContent(
                            navigator = navigator,
                            backStack = backStack,
                            modifier = Modifier.padding(padding),
                        )
                    }
                }
            }
        }
    }
}
