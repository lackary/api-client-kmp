package io.lackstudio.module.kmp.apiclient.core.common.extension


/**
 *  Generic helper function for handling Result<T> and converting to R
 * If Result is successful, execute the transformer function to convert T to R
 * If Result fails, throw a standardized AppException
 */
suspend fun <T, R> Result<T>.toDomain(transformer: suspend (T) -> R): R {
    return this.fold(
        onSuccess = { data ->
            // If successful, execute the transformer function
            transformer(data)
        },
        onFailure = { exception ->
            // If it fails, rethrow the standardized AppException
            println("toDomain e: $exception")
            throw exception
        }
    )
}
