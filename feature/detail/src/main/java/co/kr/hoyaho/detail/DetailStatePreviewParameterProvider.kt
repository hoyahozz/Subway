package co.kr.hoyaho.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.kr.hoyaho.domain.model.StationPassengerStats
import java.time.LocalDate

internal class DetailStatePreviewParameterProvider : PreviewParameterProvider<DetailScreen.State> {
    override val values: Sequence<DetailScreen.State> = sequenceOf(
        DetailScreen.State.Loading,
        DetailScreen.State.Idle(stats = null),
        DetailScreen.State.Idle(
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
