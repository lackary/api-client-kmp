package io.lackstudio.module.kmp.apiclient.core.network.oauth

interface TokenResponse {
    val accessToken: String
    val tokenType: String
    val refreshToken: String?
    val scope: String?
}