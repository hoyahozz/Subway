package co.kr.hoyaho.home

import co.kr.hoyaho.ui.base.UiEvent
import co.kr.hoyaho.ui.base.UiSideEffect
import co.kr.hoyaho.ui.base.UiState

internal class HomeContract {
    data object HomeUiState : UiState

    data object HomeUiEvent : UiEvent

    sealed class HomeSideEffect : UiSideEffect {
        object showToast : HomeSideEffect()
    }
}
