package io.lackstudio.module.kmp.apiclient.unsplash.data.error

import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.network.error.StructuredApiException
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.ApiErrorResponse

class UnsplashApiException(
    val apiError: ApiErrorResponse,
    override val originalApiException: RemoteException.Api // Implement the interface property
) : Exception(originalApiException.cause), StructuredApiException{ // Inherit Exception and implement the interface
    // Implement the interface property to provide a formatted message
//    apiError.errorDescription.takeUnless { it.isNullOrBlank() } ?: errorsMessage
    override val structuredMessage: String
        get() {
            // First, handle the single error format (e.g., OAuth "invalid_grant" or the custom 500 error)
            if (!apiError.error.isNullOrBlank()) {
                // If `error` exists, combine `error` and `error_description`
                return "${apiError.error}${
                    apiError.errorDescription?.let { ": $it" } ?: ""
                }"
            }

            // Second, handle the array format for multiple errors (e.g., validation errors)
            if (!apiError.errors.isNullOrEmpty()) {
                return apiError.errors.joinToString(", ")
            }

            // If neither format is present, provide a default unknown API error message
            return "Unknown Unsplash API error occurred. ${originalApiException.message}"
        }
}
