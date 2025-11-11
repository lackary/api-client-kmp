package io.lackstudio.module.kmp.apiclient.unsplash.data.model.request

import io.lackstudio.module.kmp.apiclient.core.network.oauth.model.BaseTokenRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    @SerialName("client_id") override val clientId: String,
    @SerialName("redirect_uri") override val redirectUri: String,
    @SerialName("client_secret") override val clientSecret: String? = null,
    override val code: String,
    @SerialName("grant_type") override val grantType: String
) : BaseTokenRequest
