package co.kr.hoyaho.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@Suppress("UnusedParameter")
internal fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        paddingValues = paddingValues,
    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
) {
    Box(
        modifier = Modifier.padding(paddingValues),
    ) {
        Text(
            "Home",
            modifier = Modifier.padding(paddingValues),
        )
    }
}
