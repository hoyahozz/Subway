package co.kr.hoyaho.home

import co.kr.hoyaho.home.HomeContract.HomeSideEffect
import co.kr.hoyaho.home.HomeContract.HomeUiEvent
import co.kr.hoyaho.home.HomeContract.HomeUiState
import co.kr.hoyaho.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeUiState, HomeUiEvent, HomeSideEffect>(HomeUiState()) {
        override suspend fun handleEvent(event: HomeUiEvent) {
            // TODO
        }
    }
