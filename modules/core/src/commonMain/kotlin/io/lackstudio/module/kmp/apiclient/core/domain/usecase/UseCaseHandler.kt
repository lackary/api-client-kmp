package io.lackstudio.module.kmp.apiclient.core.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.common.error.CommonException
import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.network.error.StructuredApiException
import io.lackstudio.module.kmp.apiclient.core.persistence.LocalException

/**
 * Executes a suspend function and wraps its result in a UseCaseResult.
 * Catches AppException and its subclasses, wrapping them as UseCaseResult.Error.
 * For other unexpected Exceptions, wraps them as UnknownApiException.
 *
 * @param block The suspend function to execute, which should return data of type T.
 * @return UseCaseResult.Success(T) or UseCaseResult.Error(AppException).
 */
suspend fun <T> toUseCaseResult(block: suspend () -> T): UseCaseResult<T> {
    return try {
        val data = block() // Execute the passed-in suspend function
        UseCaseResult.Success(data)
    } catch (e: Exception) {
        // Catch all expected AppExceptions and their subclasses
        println("safeUseCaseCall e: $e")
        when(e) {
            is StructuredApiException,
            is RemoteException,
            is LocalException,
            is CommonException ->
                UseCaseResult.Error(exception = e)
            else -> UseCaseResult.Error(exception = e)
        }
    }
}
