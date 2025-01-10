package co.kr.hoyaho.detail

import co.kr.hoyaho.domain.model.StationPassengerStats
import co.kr.hoyaho.ui.base.UiEvent
import co.kr.hoyaho.ui.base.UiSideEffect
import co.kr.hoyaho.ui.base.UiState

internal class DetailContract {
    data class DetailUiState(
        val loadState: LoadState = LoadState.Loading,
        val stats: StationPassengerStats? = null,
    ) : UiState

    enum class LoadState {
        Loading,
        Idle,
    }

    data object DetailUiEvent : UiEvent

    sealed interface DetailSideEffect : UiSideEffect {
        data class ShowToast(val message: String) : DetailSideEffect
    }
}
