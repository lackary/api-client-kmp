package io.lackstudio.module.kmp.apiclient.unsplash.mapper

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.UnsplashTokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashTokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken

fun UnsplashOAuthCode.toUnsplashTokenRequest(): UnsplashTokenRequest {
    return UnsplashTokenRequest(
        clientId = this.clientId,
        clientSecret = this.clientSecret,
        redirectUri = this.redirectUri,
        code = this.code,
        grantType = this.grantType
    )
}

fun UnsplashTokenResponse.toUnsplashOAuthToken(): UnsplashOAuthToken {
    return UnsplashOAuthToken(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        refreshToken = this.refreshToken,
        scope = this.scope,
        createdAt = this.createdAt,
        userId = this.userId,
        username = this.username
    )
}