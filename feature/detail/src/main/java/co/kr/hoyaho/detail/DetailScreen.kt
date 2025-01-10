package co.kr.hoyaho.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.detail.DetailContract.DetailSideEffect
import co.kr.hoyaho.detail.DetailContract.DetailUiState
import co.kr.hoyaho.detail.DetailContract.LoadState

@Composable
internal fun DetailRoute(
    lineNumber: String,
    stationName: String,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        DetailScreen(
            state = uiState,
            modifier = Modifier.padding(padding),
        )
    }

    LaunchedEffect(lineNumber, stationName) {
        viewModel.getRecentStationPassenger(lineNumber, stationName)
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            if (effect is DetailSideEffect.ShowToast) {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun DetailScreen(
    state: DetailUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state.loadState) {
            LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            LoadState.Idle -> state.stats?.let { stats ->
                Column(
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Text("${stats.lineNumber} ${stats.name}")
                    Text("탑승 인원 : ${stats.boardingCount}")
                    Text("하차 인원 : ${stats.alightingCount}")
                    Text("등록일 : ${stats.useDate}")
                }
            } ?: Text(
                "통계가 존재하지 않습니다.",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview(
    @PreviewParameter(DetailUiStatePreviewParameterProvider::class)
    state: DetailUiState,
) {
    SubwayTheme {
        Scaffold(
            containerColor = Color.White,
        ) { padding ->
            DetailScreen(
                state = state,
                modifier = Modifier.padding(padding),
            )
        }
    }
}
