package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashUserLinksDto(
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String
)