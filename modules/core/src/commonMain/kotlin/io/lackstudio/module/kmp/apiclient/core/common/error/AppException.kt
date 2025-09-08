package io.lackstudio.module.kmp.apiclient.core.common.error

sealed class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    // Network related errors
    sealed class Network: AppException() {
        class Unknown(message: String? = null, cause: Throwable? = null) : Network()
        class Timeout(message: String? = null, cause: Throwable? = null) : Network()
    }

    // HTTP status codes
    sealed class Api(
        val code: Int,
        message: String? = "API error",
        cause: Throwable? = null
    ) : AppException(message, cause) {
        class BadRequest(message: String?, cause: Throwable? = null) : Api(400, message, cause)
        class Unauthorized(message: String? , cause: Throwable? = null) : Api(401, message, cause)
        class Forbidden(message: String?, cause: Throwable? = null) : Api(403, message, cause)
        class NotFound(message: String?, cause: Throwable? = null) : Api(404, message, cause)
        class Server(code: Int, message: String?, cause: Throwable? = null) : Api(code, message, cause) {
            init { require(code in 500..599) { "Server error code must be in 5xx range" } }
        }
        class Unknown(code: Int, message: String?, cause: Throwable? = null) : Api(code, message, cause)
    }

    // Business logic related errors
    sealed class Business : AppException() {
        class ResourceNotFound(val resourceType: String, message: String? = null, cause: Throwable? = null) : Business() // 特定業務錯誤
        class InvalidData(message: String? = null, cause: Throwable? = null) : Business()
        class Serialization(message: String? = null, cause: Throwable? = null) : Business()
    }

    // Unknown error (catches all unclassified Exceptions)
    class Unknown(message: String? = "An unexpected error occurred.", cause: Throwable? = null) : AppException()
}