package co.kr.hoyaho.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.kr.hoyaho.detail.DetailContract.DetailUiState
import co.kr.hoyaho.detail.DetailContract.LoadState
import co.kr.hoyaho.domain.model.StationPassengerStats
import java.time.LocalDate

internal class DetailUiStatePreviewParameterProvider : PreviewParameterProvider<DetailUiState> {
    override val values: Sequence<DetailUiState> = sequenceOf(
        DetailUiState(
            loadState = LoadState.Loading,
        ),
        DetailUiState(
            loadState = LoadState.Idle,
            stats = null,
        ),
        DetailUiState(
            loadState = LoadState.Idle,
            stats = StationPassengerStats(
                lineNumber = "02호선",
                name = "낙성대",
                useDate = LocalDate.now(),
                boardingCount = 3500,
                alightingCount = 3500,
            ),
        ),
    )
}
