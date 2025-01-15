package co.kr.hoyaho.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.kr.hoyaho.designsystem.theme.SubwayTheme
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.home.HomeScreen.Event.OnStationClicked
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.PersistentList
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
    sealed interface State : CircuitUiState {
        data object Loading : State

        data class Idle(
            val stations: PersistentList<Station>,
            val eventSink: (Event) -> Unit,
        ) : State
    }

    sealed interface Event : CircuitUiEvent {
        data class OnStationClicked(val station: Station) : Event
    }
}

@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun Home(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        when (state) {
            HomeScreen.State.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            is HomeScreen.State.Idle -> Stations(
                stations = state.stations,
                onStationClicked = { station ->
                    state.eventSink(OnStationClicked(station))
                },
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

@Preview(showBackground = true)
@Composable
private fun HomePreview(
    @PreviewParameter(HomeStatePreviewParameterProvider::class)
    state: HomeScreen.State,
) {
    SubwayTheme {
        Home(
            state = state,
            modifier = Modifier,
        )
    }
}
