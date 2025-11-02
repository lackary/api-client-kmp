package io.lackstudio.module.kmp.apiclient.core.network.oauth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthToken(val type: String, val value: String)

class AccessTokenProvider(
    initialTokenType: String,
    initialToken: String
) {
    // Use StateFlow to store the current authorization state, with the initial value from the constructor
    private val _authToken = MutableStateFlow(
        AuthToken(initialTokenType, initialToken)
    )
    // For Ktor to read the latest authorization state
    val authToken: StateFlow<AuthToken> = _authToken.asStateFlow()

    // Call this function to update to a new Access Token (e.g., after a successful OAuth)
    fun setOAuthToken(newType: String, newValue: String) {
        // The OAuth standard uses the Bearer type
        println("setOAuthToken: $newType, $newValue")
        _authToken.value = AuthToken(type = newType, value = newValue)
    }

    // For Ktor to read the latest Token/Auth information
    fun getOAuthToken(): AuthToken = _authToken.value

    // return whole Header string
    fun getAuthorizationHeader(): String {
        val auth = getOAuthToken()
        return "${auth.type} ${auth.value}" // e.g., "Client-ID KEY" or "Bearer TOKEN"
    }
}