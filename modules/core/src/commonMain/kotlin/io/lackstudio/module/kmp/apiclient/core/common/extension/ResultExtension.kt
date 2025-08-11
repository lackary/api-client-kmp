package io.lackstudio.module.kmp.apiclient.core.common.extension

import io.lackstudio.module.kmp.apiclient.core.common.error.AppException


/**
 *  Generic helper function for handling Result<T> and converting to R
 * If Result is successful, execute the transformer function to convert T to R
 * If Result fails, throw a standardized AppException
 */
suspend fun <T, R> Result<T>.toDomainAndThrowIfFailed(
    transformer: suspend (T) -> R
): R {
    return this.fold(
        onSuccess = { data ->
            // If successful, execute the transformer function
            transformer(data)
        },
        onFailure = { exception ->
            // If it fails, rethrow the standardized AppException
            throw exception as? AppException ?: AppException.Unknown("An unexpected error occurred", exception)
        }
    )
}