package io.lackstudio.module.kmp.apiclient.core.ui

sealed interface AppUiState<out T> {
    object Loading : AppUiState<Nothing>
    data class Success<out T>(val data: T) : AppUiState<T>
    data class Error(val message: String) : AppUiState<Nothing>
}