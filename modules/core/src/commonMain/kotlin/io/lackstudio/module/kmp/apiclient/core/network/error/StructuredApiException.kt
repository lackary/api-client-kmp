package io.lackstudio.module.kmp.apiclient.core.network.error

/**
 * An interface to identify API exceptions that have successfully parsed their error body
 * with structured data.
 *
 * Any module can create its own exception class and implement this interface. This allows
 * upper layers (like a ViewModel) to handle these detailed errors without knowing their
 * specific implementation classes, thus maintaining decoupling.
 */
interface StructuredApiException {
    /**
     * The original, unparsed instance of `RemoteException.Api`.
     * It retains the original HTTP status code and error semantics (e.g., Unauthorized, TooManyRequests).
     */
    val originalApiException: RemoteException.Api

    /**
     * A formatted, human-readable error message generated from the parsed structured data.
     */
    val structuredMessage: String?
}
