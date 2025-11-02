package io.lackstudio.module.kmp.apiclient.core.network.oauth.model

interface TokenRequest {
    val clientId: String
    val redirectUri: String
    val clientSecret: String?
    val code: String
    val grantType: String
}