package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow

// Test ViewModel to simulate real business logic
class TestViewModel : BaseViewModel() {
    private val _uiState = MutableStateFlow<AppUiState<String>>(AppUiState.Idle)
    val uiState = _uiState

    // Simulate an actual data request action
    fun fetchData(success: Boolean) {
        handleUseCaseCall(_uiState) {
            if (success) {
                UseCaseResult.Success("Success Data")
            } else {
                UseCaseResult.Error(RemoteException.Network.Unknown("Network unavailable."))
            }
        }
    }

    // MVI Test Method
    // This simulates how a real MVI ViewModel internally uses handleUseCaseCall
    fun fetchDataMvi(success: Boolean) {
        handleUseCaseCall(
            onLoading = {
                // MVI: Manually update state to Loading
                _uiState.value = AppUiState.Loading
            },
            useCase = {
                if (success) {
                    UseCaseResult.Success("MVI Success Data")
                } else {
                    // Here we intentionally pass an error that will be parsed as "Network unavailable."
                    UseCaseResult.Error(RemoteException.Network.Unknown())
                }
            },
            onSuccess = { data ->
                // MVI: Manually update state to Success
                _uiState.value = AppUiState.Success(data)
            },
            onError = { errorMessage ->
                // MVI: Manually update state to Error; the message is parsed by BaseViewModel
                _uiState.value = AppUiState.Error(errorMessage)
            }
        )
    }
}
