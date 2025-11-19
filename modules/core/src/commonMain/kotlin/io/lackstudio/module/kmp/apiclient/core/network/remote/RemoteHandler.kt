package io.lackstudio.module.kmp.apiclient.core.network.remote

import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import io.lackstudio.module.kmp.apiclient.core.common.error.CommonException
import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException

suspend inline fun <T> toResult(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (e: ResponseException) { // Handle response errors
        val errorBody = e.response.bodyAsText()
        val status = e.response.status
        val exception = when(e) {
            is ClientRequestException -> { // Handle 4xx errors
                when (status) {
                    HttpStatusCode.BadRequest ->
                        RemoteException.Api.BadRequest(cause = e, errorBody = errorBody)
                    HttpStatusCode.Unauthorized ->
                        RemoteException.Api.Unauthorized(cause = e, errorBody = errorBody,)
                    HttpStatusCode.Forbidden ->
                        RemoteException.Api.Forbidden(cause = e, errorBody = errorBody)
                    HttpStatusCode.NotFound ->
                        RemoteException.Api.NotFound(cause = e, errorBody = errorBody)
                    HttpStatusCode.TooManyRequests ->
                        RemoteException.Api.TooManyRequests(cause = e, errorBody = errorBody)
                    else ->
                        RemoteException.Api.UnexpectedStatus(
                            code = status.value,
                            cause = e,
                            errorBody = errorBody
                        )
                }
            }
            is ServerResponseException ->
                RemoteException.Api.Server(code = status.value, cause = e, errorBody = errorBody)
            else ->
                RemoteException.Api.UnexpectedStatus(
                    code =status.value,
                    cause = e,
                    errorBody = errorBody
                )
        }
        Result.failure(exception)
    } catch (e: Exception) { // Catch all other unexpected errors
        val exception = when (e) {
            is HttpRequestTimeoutException,
            is ConnectTimeoutException,
            is SocketTimeoutException -> RemoteException.Network.Timeout(cause = e)
            is SerializationException -> CommonException.Parsing.SerializationFailed(cause = e,)
            else -> RemoteException.RemoteUnknown(cause = e)
        }
        Result.failure(exception)
    }
}
