package com.lackstudio.module.kmp.apiclient.core.network.extension

import co.touchlab.kermit.Logger
import com.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException

/***
 *  for the response of the api in ApiService
 *  ex: fun getItem(): Result<data model>
 */
suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return try {
        // 1. Check HTTP status code
        val statusCode = this.status.value
        if (statusCode >= 400) {
            val errorBody = try {
                this.bodyAsText() // Attempt to read the error response body
            } catch (e: Exception) {
                Logger.e(e) { "Failed to read error body for status $statusCode" }
                "Could not read error body." // Fallback message if reading fails
            }

            Logger.w { "API Response Failed: ${this.status.value} - Body: $errorBody" }

            // Throw different AppExceptions.Api subclasses based on the status code
            val apiError = when (this.status) {
                HttpStatusCode.BadRequest -> AppException.Api.BadRequest(errorBody)
                HttpStatusCode.Unauthorized -> AppException.Api.Unauthorized(errorBody)
                HttpStatusCode.Forbidden -> AppException.Api.Forbidden(errorBody)
                HttpStatusCode.NotFound -> AppException.Api.NotFound(errorBody)
                in HttpStatusCode.InternalServerError..HttpStatusCode.GatewayTimeout ->
                    AppException.Api.Server(statusCode, errorBody)
                else -> AppException.Api.Unknown(statusCode, errorBody)
            }
            Result.failure(apiError) // Return failure result
        } else {
            // 2. If HTTP status code is 2xx, try to parse the response body
            val data: T = this.body()
            Result.success(data) // Return success result
        }
    } catch (e: Exception) {
        // 3. Catch other possible exceptions (network errors, serialization errors, etc.)
        Logger.e(e) { "Error during API call or response processing: ${e.message}" }

        val appException = when (e) {
            is HttpRequestTimeoutException -> AppException.Network.Timeout(cause = e)
            is SerializationException -> AppException.Business.Serialization(cause = e)
            // Ktor's ClientRequestException (4xx) and ServerResponseException (5xx)
            // are usually handled in the validateResponse phase, but can also be caught here as a fallback
            is ClientRequestException -> AppException.Api.Unknown(e.response.status.value, e.message, e)
            is ServerResponseException -> AppException.Api.Server(e.response.status.value, e.message, e)
            else -> AppException.Network.Unknown("An unexpected network or client error occurred.", e)
        }
        // Return failure result
        Result.failure(appException)
    }
}