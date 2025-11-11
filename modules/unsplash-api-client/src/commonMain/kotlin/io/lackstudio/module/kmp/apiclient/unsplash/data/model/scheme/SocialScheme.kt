package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import io.lackstudio.module.kmp.apiclient.unsplash.utils.constants.JsonKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialScheme(
    @SerialName(JsonKeys.Social.INSTAGRAM_USERNAME) val instagramUsername: String? = null,
    @SerialName(JsonKeys.Social.PORTFOLIO_URL) val portfolioUrl: String? = null,
    @SerialName(JsonKeys.Social.TWITTER_USERNAME) val twitterUsername: String? = null,
    @SerialName(JsonKeys.Social.PAYPAL_EMAIL) val paypalEmail: String? = null
)
