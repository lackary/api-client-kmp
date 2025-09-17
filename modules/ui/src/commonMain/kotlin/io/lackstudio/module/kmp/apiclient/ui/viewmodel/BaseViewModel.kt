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
}