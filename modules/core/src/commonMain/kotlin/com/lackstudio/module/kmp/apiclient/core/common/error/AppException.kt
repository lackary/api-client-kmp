package com.lackstudio.module.kmp.apiclient.core.common.error

sealed class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    // Network related errors
    sealed class Network : AppException() {
        class Unknown(message: String? = "An unknown network error occurred.", cause: Throwable? = null) : Network()
        class Timeout(message: String? = "Request timed out.", cause: Throwable? = null) : Network()
    }

    // HTTP status codes
    sealed class Api(
        val code: Int,
        message: String? = "API error",
        cause: Throwable? = null
    ) : AppException(message, cause) {
        class BadRequest(message: String? = "Bad request.", cause: Throwable? = null) : Api(400, message, cause)
        class Unauthorized(message: String? = "Authentication failed.", cause: Throwable? = null) : Api(401, message, cause)
        class Forbidden(message: String? = "Access denied.", cause: Throwable? = null) : Api(403, message, cause)
        class NotFound(message: String? = "Resource not found.", cause: Throwable? = null) : Api(404, message, cause)
        class Server(code: Int, message: String? = "Server error occurred.", cause: Throwable? = null) : Api(code, message, cause) {
            init { require(code in 500..599) { "Server error code must be in 5xx range" } }
        }
        class Unknown(code: Int, message: String? = "Unknown API error.", cause: Throwable? = null) : Api(code, message, cause)
    }

    // Business logic related errors
    sealed class Business : AppException() {
        class UserNotFound(val userId: String, message: String? = "User not found.", cause: Throwable? = null) : Business() // 特定業務錯誤
        class InvalidData(message: String? = "Invalid data provided.", cause: Throwable? = null) : Business()
        class Serialization(message: String? = "Data format error.", cause: Throwable? = null) : Network()
    }

    // Unknown error (catches all unclassified Exceptions)
    class Unknown(message: String? = "An unexpected error occurred.", cause: Throwable? = null) : AppException(message, cause)
}