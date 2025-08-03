package com.lackstudio.module.kmp.apiclient.core.domain.util

import com.lackstudio.module.kmp.apiclient.core.common.error.AppException
import com.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult


/**
 * Executes a suspend function and wraps its result in a UseCaseResult.
 * Catches AppException and its subclasses, wrapping them as UseCaseResult.Error.
 * For other unexpected Exceptions, wraps them as UnknownApiException.
 *
 * @param block The suspend function to execute, which should return data of type T.
 * @return UseCaseResult.Success(T) or UseCaseResult.Error(AppException).
 */
suspend fun <T> safeUseCaseCall(block: suspend () -> T): UseCaseResult<T> {
    return try {
        val data = block() // Execute the passed-in suspend function
        UseCaseResult.Success(data)
    } catch (e: AppException) {
        // Catch all expected AppExceptions and their subclasses
        UseCaseResult.Error(e)
    } catch (e: Exception) {
        // Catch all other unexpected Exceptions and convert them to UnknownApiException
        UseCaseResult.Error(AppException.Unknown("An unexpected error occurred: ${e.message}", e))
    }
}