package co.kr.hoyaho.detail

import co.kr.hoyaho.ui.base.UiEvent
import co.kr.hoyaho.ui.base.UiSideEffect
import co.kr.hoyaho.ui.base.UiState

internal class DetailContract {
    data object DetailUiState : UiState

    data object DetailUiEvent : UiEvent

    data object DetailSideEffect : UiSideEffect
}
