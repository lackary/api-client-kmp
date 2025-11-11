package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import io.lackstudio.module.kmp.apiclient.core.network.oauth.model.BaseTokenResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("access_token") override val accessToken: String,
    @SerialName("token_type") override val tokenType: String,
    @SerialName("refresh_token") override val refreshToken: String,
    override val scope: String,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("user_id") val userId: Long,
    val username: String
) : BaseTokenResponse
