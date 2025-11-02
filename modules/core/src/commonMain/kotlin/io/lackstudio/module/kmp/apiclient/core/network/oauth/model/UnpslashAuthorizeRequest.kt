package io.lackstudio.module.kmp.apiclient.core.network.oauth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashAuthorizeRequest(
    @SerialName("client_id") override val clientId: String,
    @SerialName("redirect_uri") override val redirectUri: String,
    @SerialName("response_type") override val responseType: String,
    override val scope: String,
    override val state: String? = null

) : AuthorizeRequest
