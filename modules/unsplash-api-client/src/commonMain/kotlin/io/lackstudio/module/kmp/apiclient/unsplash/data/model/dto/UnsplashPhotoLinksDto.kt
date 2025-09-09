package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPhotoLinksDto(
    val self: String,
    val html: String,
    val download: String,
    @SerialName("download_location")
    val downloadLocation: String
)
