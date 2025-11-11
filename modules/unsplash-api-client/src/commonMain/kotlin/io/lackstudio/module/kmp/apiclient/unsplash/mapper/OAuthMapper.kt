package io.lackstudio.module.kmp.apiclient.unsplash.mapper

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.TokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.TokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken

fun OAuthCode.toTokenRequest(): TokenRequest {
    return TokenRequest(
        clientId = this.clientId,
        clientSecret = this.clientSecret,
        redirectUri = this.redirectUri,
        code = this.code,
        grantType = this.grantType
    )
}

fun TokenResponse.toOAuthToken(): OAuthToken {
    return OAuthToken(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        refreshToken = this.refreshToken,
        scope = this.scope,
        createdAt = this.createdAt,
        userId = this.userId,
        username = this.username
    )
}
