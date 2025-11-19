package io.lackstudio.module.kmp.apiclient.ui.state

sealed interface AppUiState<out T> {
    object Idle : AppUiState<Nothing>
    object Loading : AppUiState<Nothing>
    data class Success<out T>(val data: T) : AppUiState<T>
    data class Error(val message: String) : AppUiState<Nothing>
}
