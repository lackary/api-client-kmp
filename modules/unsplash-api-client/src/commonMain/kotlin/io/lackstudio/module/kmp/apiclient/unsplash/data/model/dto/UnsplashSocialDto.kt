package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSocialDto(
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("paypal_email")
    val paypalEmail: String?,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?
)