package co.kr.hoyaho.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import co.kr.hoyaho.domain.usecase.GetRecentStationPassengerStatsUseCase
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

class DetailPresenter @AssistedInject constructor(
    private val getRecentStationPassengerStatsUseCase: GetRecentStationPassengerStatsUseCase,
    @ApplicationContext private val appContext: Context,
    @Assisted private val screen: DetailScreen,
) : Presenter<DetailScreen.State> {
    @Composable
    override fun present(): DetailScreen.State {
        val state by produceState<DetailScreen.State>(DetailScreen.State.Loading) {
            val stats = getRecentStationPassengerStatsUseCase(
                lineNumber = getFormatedLineNumber(screen.lineNumber),
                stationName = screen.stationName,
            ).getOrElse {
                Toast.makeText(appContext, "알 수 없는 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                null
            }

            value = DetailScreen.State.Idle(
                stats = stats,
            )
        }

        return state
    }

    /**
     * 01호선, 02호선 등의 형태로 저장된 문자열에서 1호선, 2호선의 형태로 변경 후 리턴
     * TODO : enum 형태로 lineNumber 저장 필요
     */
    private fun getFormatedLineNumber(lineNumber: String): String {
        if (lineNumber.first() == '0') {
            return lineNumber.substring(1..lineNumber.lastIndex)
        }

        return lineNumber
    }

    @CircuitInject(DetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: DetailScreen): DetailPresenter
    }
}
