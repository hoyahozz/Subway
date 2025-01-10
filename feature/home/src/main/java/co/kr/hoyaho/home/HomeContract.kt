package co.kr.hoyaho.home

import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.ui.base.UiEvent
import co.kr.hoyaho.ui.base.UiSideEffect
import co.kr.hoyaho.ui.base.UiState

internal class HomeContract {
    data class HomeUiState(
        val loadState: LoadState = LoadState.Loading,
        val stations: List<Station> = emptyList(),
    ) : UiState

    enum class LoadState {
        Loading,
        Idle,
    }

    sealed interface HomeUiEvent : UiEvent {
        data class OnStationClicked(val station: Station) : HomeUiEvent
    }

    sealed interface HomeSideEffect : UiSideEffect {
        data class ShowToast(val message: String) : HomeSideEffect
        data class NavigateToDetail(val station: Station) : HomeSideEffect
    }
}
