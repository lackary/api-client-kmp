package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashProfileImage(
    val large: String,
    val medium: String,
    val small: String
) {

}
