package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lackstudio.module.kmp.apiclient.core.common.error.CommonException
import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.network.error.StructuredApiException
import io.lackstudio.module.kmp.apiclient.core.persistence.LocalException
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun getAppErrorMessage(e: Exception): String {
        return when(e) {
            // Prioritize structured API exceptions (e.g., UnsplashApiException)
            is StructuredApiException -> {
                // Regardless of the original error code (400, 401, 403...),
                // if the Feature layer has parsed a structuredMessage, prioritize displaying it.
                // For example: If Unsplash returns "Access Key Invalid", it will be displayed directly here.
                e.structuredMessage ?: getApiDefaultMessage(e.originalApiException)
            }

            // 2. Handle raw API exceptions not wrapped in StructuredApiException
            // (e.g., simple API calls that don't undergo special parsing)
            is RemoteException.Api -> getApiDefaultMessage(e)

            // 3. Handle network connection issues
            is RemoteException.Network.Timeout -> "Connection timeout. Please check your internet."
            is RemoteException.Network.Unknown -> "Network unavailable."

            // 4. Local and other errors
            is LocalException.Persistence.DatabaseError -> "Database error."
            is LocalException.Persistence.ResourceNotFound -> "Resource not found."
            is CommonException.Parsing.InvalidDataFormat -> "Data format error."

            else -> "An unexpected error occurred."
        }
    }

    // Extract common default message logic to avoid code duplication
    private fun getApiDefaultMessage(apiException: RemoteException.Api): String {
        return when (apiException) {
            is RemoteException.Api.BadRequest -> apiException.message ?: "Invalid request." // 400
            is RemoteException.Api.Unauthorized -> "Session expired. Please login again." // 401
            is RemoteException.Api.Forbidden -> "Access denied." // 403
            is RemoteException.Api.NotFound -> "Resource not found." // 404
            is RemoteException.Api.TooManyRequests -> "Too many requests. Please try again later." // 429
            is RemoteException.Api.Server -> "Server error. Please try again later." // 5xx
            is RemoteException.Api.UnexpectedStatus -> "Unexpected error (${apiException.code})."
        }
    }

    /**
     * Defines a generic function in BaseViewModel to handle UseCaseResult.
     * It takes a suspend function (representing the UseCase call) and automatically handles
     * both Success and Error states.
     */
    protected fun <T> handleUseCaseCall(
        flow: MutableStateFlow<AppUiState<T>>,
        useCase: suspend () -> UseCaseResult<T>,
    ) {
        viewModelScope.launch {
            flow.value = AppUiState.Loading

            when (val result = useCase()) {
                is UseCaseResult.Success -> {
                    flow.value = AppUiState.Success(result.data)
                }
                is UseCaseResult.Error -> {
                    val errorMessage = getAppErrorMessage(result.exception)
                    flow.value = AppUiState.Error(errorMessage)
                }
            }
        }
    }

    /**
     * [MVI-specific] Handles a UseCase call and delegates the state update logic to the caller.
     * This approach allows BaseViewModel to handle coroutine launching, setting the Loading state,
     * error catching, and error message generation, while the AppViewModel is responsible for
     * merging the result into a single MviUiState.
     */
    protected fun <T> handleUseCaseCall(
        // 1. How to set the Loading state.
        onLoading: () -> Unit,
        // 2. The UseCase to be executed.
        useCase: suspend () -> UseCaseResult<T>,
        // 3. On success, how to update the MviUiState (receives the success data T).
        onSuccess: (T) -> Unit,
        // 4. On failure, how to update the MviUiState (receives the error message String).
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            onLoading() // First, call the Loading logic passed in from the outside.

            when (val result = useCase()) {
                is UseCaseResult.Success -> {
                    onSuccess(result.data) // Call the Success logic passed in from the outside.
                }
                is UseCaseResult.Error -> {
                    println("result: ${result.exception}")
                    val errorMessage = getAppErrorMessage(result.exception) // Use BaseViewModel's error logic.
                    onError(errorMessage) // Call the Error logic passed in from the outside.
                }
            }
        }
    }
}
