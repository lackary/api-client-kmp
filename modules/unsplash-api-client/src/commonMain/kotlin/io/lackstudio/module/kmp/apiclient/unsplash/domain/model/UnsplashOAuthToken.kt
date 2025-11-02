package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashOAuthToken(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String? = null,
    val scope: String? = null,
    val createdAt: Long,
    val userId: Long,
    val username: String
)