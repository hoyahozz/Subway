package co.kr.hoyaho.detail

import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.detail.DetailContract.DetailSideEffect
import co.kr.hoyaho.detail.DetailContract.DetailUiEvent
import co.kr.hoyaho.detail.DetailContract.DetailUiState
import co.kr.hoyaho.detail.DetailContract.LoadState
import co.kr.hoyaho.domain.usecase.GetRecentStationPassengerStatsUseCase
import co.kr.hoyaho.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getRecentStationPassengerStatsUseCase: GetRecentStationPassengerStatsUseCase,
) : BaseViewModel<DetailUiState, DetailUiEvent, DetailSideEffect>(DetailUiState()) {
    override suspend fun handleEvent(event: DetailUiEvent) = Unit

    fun getRecentStationPassenger(
        lineNumber: String,
        stationName: String,
    ) {
        viewModelScope.launch {
            getRecentStationPassengerStatsUseCase(
                lineNumber = getFormatedLineNumber(lineNumber),
                stationName = stationName,
            ).fold(
                onSuccess = { stats ->
                    updateState {
                        copy(
                            loadState = LoadState.Idle,
                            stats = stats,
                        )
                    }
                },
                onFailure = { throwable ->
                    sendEffect(DetailSideEffect.ShowToast(throwable.message ?: "알 수 없는 오류가 발생하였습니다."))
                },
            )
        }
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
}
