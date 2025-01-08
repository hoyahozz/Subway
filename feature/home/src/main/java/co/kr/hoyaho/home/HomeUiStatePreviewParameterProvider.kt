package co.kr.hoyaho.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.home.HomeContract.HomeUiState
import co.kr.hoyaho.home.HomeContract.LoadState
import kotlinx.collections.immutable.persistentListOf

internal class HomeUiStatePreviewParameterProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> = sequenceOf(
        HomeUiState(
            loadState = LoadState.Idle,
            stations = persistentListOf(
                Station(
                    id = "1",
                    title = "낙성대역",
                    lineNumber = "2호선",
                ),
                Station(
                    id = "1",
                    title = "사당역",
                    lineNumber = "2호선",
                ),
                Station(
                    id = "1",
                    title = "방배역",
                    lineNumber = "2호선",
                ),
            ),
        ),
    )
}
