package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow

// Test ViewModel to simulate real business logic
class TestViewModel : BaseViewModel() {
    private val _uiState = MutableStateFlow<AppUiState<String>>(AppUiState.Loading)
    val uiState = _uiState

    // Simulate an actual data request action
    suspend fun fetchData(success: Boolean) {
        handleUseCaseCall(_uiState) {
            if (success) {
                UseCaseResult.Success("Success Data")
            } else {
                UseCaseResult.Error(AppException.Network.Unknown())
            }
        }
    }
}