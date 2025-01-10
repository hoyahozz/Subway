package co.kr.hoyaho.detail

import co.kr.hoyaho.detail.DetailContract.DetailSideEffect
import co.kr.hoyaho.detail.DetailContract.DetailUiEvent
import co.kr.hoyaho.detail.DetailContract.DetailUiState
import co.kr.hoyaho.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor() : BaseViewModel<DetailUiState, DetailUiEvent, DetailSideEffect>(
    DetailUiState(),
) {
    override suspend fun handleEvent(event: DetailUiEvent) {
        // TODO
    }
}
