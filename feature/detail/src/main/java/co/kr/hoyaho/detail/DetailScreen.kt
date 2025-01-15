package co.kr.hoyaho.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.domain.model.StationPassengerStats
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(
    val lineNumber: String,
    val stationName: String,
) : Screen {
    sealed interface State : CircuitUiState {
        data object Loading : State

        data class Idle(
            val stats: StationPassengerStats? = null,
        ) : State
    }
}

@CircuitInject(DetailScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun Detail(
    state: DetailScreen.State,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            DetailScreen.State.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            is DetailScreen.State.Idle -> state.stats?.let { stats ->
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

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview(
    @PreviewParameter(DetailStatePreviewParameterProvider::class)
    state: DetailScreen.State,
) {
    SubwayTheme {
        Detail(state = state)
    }
}
