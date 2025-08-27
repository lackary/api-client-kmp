package io.lackstudio.module.kmp.apiclient.core.network.error

import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException
import io.ktor.client.network.sockets.ConnectTimeoutException

suspend fun <T> toResult(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (e: ClientRequestException) { // Handle 4xx errors
        val errorBody = e.response.bodyAsText()
        val status = e.response.status
        val appException = when (status) {
            HttpStatusCode.BadRequest -> AppException.Api.BadRequest(errorBody)
            HttpStatusCode.Unauthorized -> AppException.Api.Unauthorized(errorBody)
            HttpStatusCode.Forbidden -> AppException.Api.Forbidden(errorBody)
            HttpStatusCode.NotFound -> AppException.Api.NotFound(errorBody)
            else -> AppException.Api.Unknown(status.value, errorBody)
        }
        Result.failure(appException)
    } catch (e: Exception) { // Catch all other unexpected errors
        val appException = when (e) {
            is ServerResponseException -> {
                val errorBody = e.response.bodyAsText()
                AppException.Api.Server(e.response.status.value, errorBody)
            }
            is HttpRequestTimeoutException -> AppException.Network.Timeout("request time out: ${e.message}", e)
            is ConnectTimeoutException -> AppException.Network.Timeout("connection time out ${e.message}", e)
            is SerializationException -> AppException.Business.Serialization(cause = e)
            else -> AppException.Unknown("An unexpected error occurred: ${e.message}", e)
        }
        Result.failure(appException)
    }
}