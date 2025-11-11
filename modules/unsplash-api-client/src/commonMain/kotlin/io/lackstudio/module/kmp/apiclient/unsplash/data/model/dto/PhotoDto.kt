package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoUrlsScheme

interface PhotoDto {
    val id: String
    val slug: String
    val createdAt: String
    val updatedAt: String
    val blurHash: String
    val assetType: String
    val urls: PhotoUrlsScheme
}
