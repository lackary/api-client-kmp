package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.network.remote.toResult
import io.lackstudio.module.kmp.apiclient.unsplash.data.error.UnsplashApiException
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.ApiErrorResponse
import kotlinx.serialization.json.Json

// Define the toResult function for the Unsplash domain
// This allows direct use of toUnsplashResult { ... } in RemoteUnsplashDataSourceImpl
suspend inline fun <T> toUnsplashResult(call: suspend () -> T): Result<T> {
    // Get the initial Result first
    val initialResult = toResult(call)
    val exception = initialResult.exceptionOrNull() // Check if it's a failure

    // Check if it's the specific API error we're interested in
    if (exception is RemoteException.Api) {
        exception.errorBody?.takeIf { it.isNotBlank() }?.let { bodyString ->
            println("toUnsplashResult e: $exception, code: ${exception.code} errorBody: $bodyString")
            try {
                val json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
                val errorResponse = json.decodeFromString<ApiErrorResponse>(bodyString)

                val unsplashApiException = UnsplashApiException(
                    apiError = errorResponse,
                    originalApiException = exception
                )
                println("toUnsplashResult ${unsplashApiException.apiError}")
                // If parsing is successful, return a new Failure Result containing our custom exception
                return Result.failure(unsplashApiException)

            } catch (e: Exception) {
                // If JSON parsing fails, do nothing; the original Result will be returned later.
                // A log could be added here, but the flow should not be altered.
                println("toUnsplashResult JSON parsing failed: ${e.message}")
            }
        }
    }

    // For all other cases (non-API errors, parsing failures, etc.), safely return the initial Result
    return initialResult
}

