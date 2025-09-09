package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPreviewPhotoDto(
    val id: String,
    val slug: String,
    @SerialName("create_at")
    val createAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("blur_hash")
    val blurHash: String,
    @SerialName("asset_type")
    val assetType: String,
    val urls: UnsplashPhotoUrlsDto
)
