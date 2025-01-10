package co.kr.hoyaho.home

import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.domain.usecase.GetStationsUseCase
import co.kr.hoyaho.home.HomeContract.HomeSideEffect
import co.kr.hoyaho.home.HomeContract.HomeUiEvent
import co.kr.hoyaho.home.HomeContract.HomeUiState
import co.kr.hoyaho.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO 구조 개선
@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
) : BaseViewModel<HomeUiState, HomeUiEvent, HomeSideEffect>(HomeUiState()) {
    init {
        viewModelScope.launch {
            getStationsUseCase().fold(
                onSuccess = { stations ->
                    updateState {
                        copy(
                            loadState = HomeContract.LoadState.Idle,
                            stations = stations,
                        )
                    }
                },
                onFailure = { throwable ->
                    sendEffect(HomeSideEffect.ShowToast(throwable.message ?: "알 수 없는 오류가 발생하였습니다."))
                },
            )
        }
    }

    override suspend fun handleEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStationClicked -> sendEffect(HomeSideEffect.NavigateToDetail(event.station))
        }
    }
}
