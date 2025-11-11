package io.lackstudio.module.kmp.apiclient.core.network.oauth.model

/**
 * Common interface for authorization requests.
 * * **Important Note:** Any concrete authorization request data class that needs to inherit this interface
 * (e.g., AuthRequest for a specific API Service)
 * **should be defined within the current `io.lackstudio.module.kmp.apiclient.core.network.oauth.model` package.**
 */
interface BaseAuthorizeRequest {
    val clientId: String
    val redirectUri: String
    val responseType: String
    val scope: String
    val state: String?
}
