package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashProfileImageDto(
    val large: String,
    val medium: String,
    val small: String
)