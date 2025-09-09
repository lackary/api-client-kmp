package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSocial(
    val instagramUsername: String?,
    val paypalEmail: String?,
    val portfolioUrl: String?,
    val twitterUsername: String?
) {

}
