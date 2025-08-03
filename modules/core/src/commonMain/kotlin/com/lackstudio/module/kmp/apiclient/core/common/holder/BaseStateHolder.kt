package com.lackstudio.module.kmp.apiclient.core.common.holder

import com.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import com.lackstudio.module.kmp.apiclient.core.common.error.AppException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Define a common UI state interface or abstract class if all UI states share common properties
interface CommonUiState {
    val isLoading: Boolean
    val error: String?
}

// Abstract base class to handle common logic for all StateHolders
abstract class BaseStateHolder(
    // Inject CoroutineScope, its lifecycle is managed by the platform
    protected val scope: CoroutineScope
) {
    // Each subclass will manage its own specific UI state.
    // This can be implemented by subclasses, or BaseStateHolder can internally manage common states like isLoading/error
    // Here we demonstrate managing only common error and loading states
    protected val _commonLoadingErrorState = MutableStateFlow(CommonLoadingErrorState())
    val commonLoadingErrorState: StateFlow<CommonLoadingErrorState> = _commonLoadingErrorState.asStateFlow()

    data class CommonLoadingErrorState(
        val isLoading: Boolean = false,
        val error: String? = null
    )

    // Common method to handle UseCaseResult, for subclasses to call
    protected suspend fun <T> handleUseCaseResult(
        result: UseCaseResult<T>,
        onSuccess: (T) -> Unit, // Callback on success
        onError: ((AppException) -> Unit)? = null // Optional additional callback on error
    ) {
        when (result) {
            is UseCaseResult.Success -> {
                onSuccess(result.data)
                _commonLoadingErrorState.value = _commonLoadingErrorState.value.copy(error = null, isLoading = false)
            }
            is UseCaseResult.Error -> {
                val errorMessage = when (result.exception) {
                    is AppException.Network -> "Network connection failed, please check your network!"
                    is AppException.Api.NotFound -> "Content not found."
                    is AppException.Api.Unauthorized -> "You are not authorized, please log in again."
                    is AppException.Api.Server -> "Server error (${result.exception.code}), please try again later."
                    is AppException.Api.BadRequest -> "Bad request: ${result.exception.message}"
                    else -> "An unknown error occurred: ${result.exception.message ?: "Please try again later."}"
                }
                _commonLoadingErrorState.value = _commonLoadingErrorState.value.copy(isLoading = false, error = errorMessage)
                onError?.invoke(result.exception) // Trigger additional error handling callback
            }
        }
    }

    // When the StateHolder is destroyed, cancel all coroutines
    open fun dispose() {
        scope.cancel()
    }
}
