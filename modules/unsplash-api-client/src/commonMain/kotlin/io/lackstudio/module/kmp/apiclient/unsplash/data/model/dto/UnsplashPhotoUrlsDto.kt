package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UnsplashPhotoUrlsDto(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    @SerialName("small_s3")
    val smalls3: String
)
