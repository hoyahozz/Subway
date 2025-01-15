package co.kr.hoyaho.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.kr.hoyaho.domain.model.Station
import kotlinx.collections.immutable.persistentListOf

internal class HomeStatePreviewParameterProvider : PreviewParameterProvider<HomeScreen.State> {
    override val values: Sequence<HomeScreen.State> = sequenceOf(
        HomeScreen.State.Loading,
        HomeScreen.State.Idle(
            stations = persistentListOf(
                Station(
                    id = "1",
                    name = "낙성대역",
                    lineNumber = "2호선",
                ),
                Station(
                    id = "2",
                    name = "사당역",
                    lineNumber = "2호선",
                ),
                Station(
                    id = "3",
                    name = "방배역",
                    lineNumber = "2호선",
                ),
            ),
            eventSink = { },
        ),
    )
}
