package io.lackstudio.module.kmp.apiclient.core.domain.usecase

/**
 * Encapsulates the result of a Use Case operation.
 *
 * @param T The type of data returned on success.
 */
sealed class UseCaseResult<out T> {
    /**
     * Represents a successful Use Case operation and contains the returned data.
     * @param data The data from the successful operation.
     */
    data class Success<out T>(val data: T) : UseCaseResult<T>()

    /**
     * Represents a failed Use Case operation and contains the AppException that caused the failure.
     * @param exception The AppException instance that caused the operation to fail.
     */
    data class Error(val exception: Exception) : UseCaseResult<Nothing>() // Nothing indicates that this branch does not return any data
}
