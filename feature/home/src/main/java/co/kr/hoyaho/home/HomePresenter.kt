package co.kr.hoyaho.home

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.kr.hoyaho.detail.DetailScreen
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.domain.usecase.GetStationsUseCase
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

class HomePresenter @AssistedInject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    @ApplicationContext private val appContext: Context,
    @Assisted private val navigator: Navigator,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        // 로딩 상태 관리, 더 좋은 방법이 없을까..
        var loaded by rememberRetained { mutableStateOf(false) }
        var stations by rememberRetained { mutableStateOf(persistentListOf<Station>()) }

        LaunchedEffect(loaded) {
            if (!loaded) {
                stations = getStationsUseCase().getOrElse {
                    // 더 좋은 방법이 없을까..
                    Toast.makeText(appContext, "알 수 없는 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                    emptyList()
                }.toPersistentList()
                loaded = true
            }
        }

        return if (loaded) {
            HomeScreen.State.Idle(
                stations = stations,
                eventSink = { event ->
                    when (event) {
                        is HomeScreen.Event.OnStationClicked -> {
                            navigator.goTo(
                                DetailScreen(
                                    lineNumber = event.station.lineNumber,
                                    stationName = event.station.name,
                                ),
                            )
                        }
                    }
                },
            )
        } else {
            HomeScreen.State.Loading
        }
    }

    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
