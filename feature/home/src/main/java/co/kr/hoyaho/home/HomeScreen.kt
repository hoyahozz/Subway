package co.kr.hoyaho.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.home.HomeContract.HomeSideEffect
import co.kr.hoyaho.home.HomeContract.HomeUiState
import co.kr.hoyaho.home.HomeContract.LoadState

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HomeScreen(
        paddingValues = paddingValues,
        state = uiState,
    )

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            if (effect is HomeSideEffect.ShowToast) {
                // TODO App State 에서 관리하도록 수정
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    state: HomeUiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        when (state.loadState) {
            LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            LoadState.Idle -> Stations(
                state.stations,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun Stations(
    stations: List<Station>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(stations) { _, station ->
            Text("[${station.lineNumber}] - ${station.name}")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeUiStatePreviewParameterProvider::class)
    homeUiState: HomeUiState,
) {
    SubwayTheme {
        Scaffold(
            containerColor = Color.White,
        ) { padding ->
            HomeScreen(
                paddingValues = padding,
                state = homeUiState,
            )
        }
    }
}
