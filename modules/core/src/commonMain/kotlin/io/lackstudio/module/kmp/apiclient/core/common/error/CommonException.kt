package io.lackstudio.module.kmp.apiclient.core.common.error

sealed class CommonException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    // Data I/O conversion errors (Parsing)
    sealed class Parsing(message: String? = null, cause: Throwable? = null) : CommonException(message, cause) {
        class SerializationFailed(message: String? = null, cause: Throwable? = null) : Parsing(message, cause)
        class InvalidDataFormat(message: String? = null, cause: Throwable? = null) : Parsing(message, cause)
    }

    class Unknown(message: String? = null, cause: Throwable? = null): CommonException(message, cause)
}
