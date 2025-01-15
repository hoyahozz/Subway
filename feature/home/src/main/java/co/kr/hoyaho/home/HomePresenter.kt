package co.kr.hoyaho.home

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import co.kr.hoyaho.detail.DetailScreen
import co.kr.hoyaho.domain.usecase.GetStationsUseCase
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.produceRetainedState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

class HomePresenter @AssistedInject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    @ApplicationContext private val appContext: Context,
    @Assisted private val navigator: Navigator,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        val state by produceRetainedState<HomeScreen.State>(HomeScreen.State.Loading) {
            val stations = getStationsUseCase().getOrElse {
                // 더 좋은 방법이 없을까?
                Toast.makeText(appContext, "알 수 없는 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                emptyList()
            }

            value = HomeScreen.State.Idle(
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
        }

        return state
    }

    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
