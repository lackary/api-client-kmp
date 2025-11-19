package io.lackstudio.module.kmp.apiclient.core.network.error

import org.koin.core.logger.MESSAGE

sealed class RemoteException(
    message: String? = null, 
    cause: Throwable? = null
) : Exception(message, cause) {

    // Remote I/O - Connection level errors
    sealed class Network(
        message: String? = null,
        cause: Throwable? = null
    ) : RemoteException(message, cause) {
        class Unknown(message: String? = null, cause: Throwable? = null) : Network(message, cause)
        class Timeout(message: String? = null, cause: Throwable? = null) : Network(message, cause)
    }

    // Remote I/O - Protocol level errors (HTTP status codes)
    sealed class Api(
        val code: Int,
        val errorBody: String? = null,
        message: String? = null,
        cause: Throwable? = null
    ) : RemoteException(message, cause) {
        class BadRequest(
            message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = 400, errorBody = errorBody, message = message, cause = cause)
        class Unauthorized(
            message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = 401, errorBody = errorBody, message = message, cause = cause)
        class Forbidden(
            message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = 403, errorBody = errorBody, message = message, cause = cause)
        class NotFound(
            message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = 404, errorBody = errorBody, message = message, cause = cause)
        class TooManyRequests(
            message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = 429, errorBody = errorBody, message = message, cause = cause)
        class Server(
            code: Int, message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = code, errorBody = errorBody, message = message, cause = cause) {
            init { require(code in 500..599) { "Server error code must be in 5xx range" } }
        }

        // Replaces the original Unknown for unexpected and unhandled HTTP status codes
        class UnexpectedStatus(
            code: Int, message: String? = null, cause: Throwable? = null, errorBody: String? = null
        ) : Api(code = code, errorBody = errorBody, message = message, cause = cause)
    }

    // Unknown error (catches all unclassified Exceptions)
    class RemoteUnknown(message: String? = null, cause: Throwable? = null) : RemoteException(message, cause)
}
