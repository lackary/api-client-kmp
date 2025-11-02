package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun getAppErrorMessage(exception: AppException): String {
        return when(exception) {
            // Network errors
            is AppException.Network -> when (exception) {
                is AppException.Network.Timeout -> "Connection timeout, please check your network connection."
                is AppException.Network.Unknown -> "Network connection error, please try again later."
            }

            is AppException.Api -> {
                // Use the Elvis operator to provide a fallback message if message is null or blank
                exception.message.takeIf { it?.isNotBlank() == true } ?:
                when (exception.code) {
                    401 -> "Authentication failed, please log in again."
                    404 -> "Resource not found."
                    in 500..599 -> "Server error, please try again later."
                    else -> "API error: ${exception.code}"
                }
            }

            // Business logic errors
            is AppException.Business.ResourceNotFound -> {
                val type = when (exception.resourceType.lowercase()) {
                    "user" -> "user"
                    "image" -> "image"
                    "product" -> "product"
                    else -> "resource"
                }
                "The $type you're looking for was not found."
            }

            // Add more Business error handling here
            is AppException.Business.InvalidData -> "Invalid data."
            is AppException.Business.Serialization -> "Data serialization error."

            // Unknown error
            is AppException.Unknown -> "An unknown error occurred."
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
                    println("result: $result")
                    val errorMessage = getAppErrorMessage(result.exception) // Use BaseViewModel's error logic.
                    onError(errorMessage) // Call the Error logic passed in from the outside.
                }
            }
        }
    }
}