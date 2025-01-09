package co.kr.hoyaho.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import co.kr.hoyaho.home.HomeContract.HomeUiEvent
import co.kr.hoyaho.home.HomeContract.HomeUiState
import co.kr.hoyaho.home.HomeContract.LoadState

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    navigateToDetail: (lineNumber: String, stationName: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HomeScreen(
        paddingValues = paddingValues,
        state = uiState,
        onStationClicked = { station ->
            viewModel.setEvent(HomeUiEvent.OnStationClicked(station))
        },
    )

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeSideEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is HomeSideEffect.NavigateToDetail -> navigateToDetail(effect.station.lineNumber, effect.station.name)
            }
        }
    }
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    state: HomeUiState,
    onStationClicked: (Station) -> Unit,
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
                onStationClicked = onStationClicked,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun Stations(
    stations: List<Station>,
    onStationClicked: (Station) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(stations) { _, station ->
            Text(
                "[${station.lineNumber}] - ${station.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStationClicked(station) }
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp,
                    ),
            )
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
                onStationClicked = { },
            )
        }
    }
}
