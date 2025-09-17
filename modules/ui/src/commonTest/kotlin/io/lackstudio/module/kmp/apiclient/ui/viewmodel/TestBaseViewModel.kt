package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow

// A helper class created for testing purposes to expose protected methods
class TestBaseViewModel : BaseViewModel() {

    // Expose handleUseCaseCall for testing
    fun <T> callHandleUseCaseCall(
        flow: MutableStateFlow<AppUiState<T>>,
        useCase: suspend () -> UseCaseResult<T>,
    ) {
        super.handleUseCaseCall(flow, useCase)
    }

    // Expose getAppErrorMessage for testing
    fun getAppErrorMessageForTest(exception: AppException): String {
        return super.getAppErrorMessage(exception)
    }
}