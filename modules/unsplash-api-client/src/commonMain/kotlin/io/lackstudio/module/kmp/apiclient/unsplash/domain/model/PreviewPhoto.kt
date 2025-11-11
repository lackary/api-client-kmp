package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PreviewPhoto (
    val id: String,
    val slug: String,
    val createdAt: String,
    val updatedAt: String,
    val blurHash: String,
    val assetType: String,
    val urls: PhotoUrls
)
