package io.lackstudio.module.kmp.apiclient.core.persistence

sealed class LocalException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    // Local I/O errors (Persistence)
    sealed class Persistence(message: String? = null, cause: Throwable? = null) : LocalException(message, cause) {
        // Database operation failed (e.g., Room's SQLException)
        class DatabaseError(message: String? = null, cause: Throwable? = null) : Persistence(message, cause)
        // File read/write or DataStore failed (e.g., IOException)
        class FileIOError(message: String? = null, cause: Throwable? = null) : Persistence(message, cause)
        // Data not found in local storage
        class ResourceNotFound(message: String? = null, cause: Throwable? = null) : Persistence(message, cause)
    }

    class LocalUnknown(message: String? = null, cause: Throwable? = null) : LocalException(message, cause)
}
