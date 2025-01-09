package co.kr.hoyaho.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DetailScreen(
    lineNumber: String,
    stationName: String,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Text(
            "Detail, $lineNumber - $stationName",
            modifier = Modifier.padding(padding),
        )
    }
}
