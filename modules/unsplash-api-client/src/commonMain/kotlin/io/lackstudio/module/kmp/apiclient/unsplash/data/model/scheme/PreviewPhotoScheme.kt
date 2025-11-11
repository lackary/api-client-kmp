package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.PhotoDto
import io.lackstudio.module.kmp.apiclient.unsplash.utils.constants.JsonKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviewPhotoScheme(
    override val id: String,
    override val slug: String,
    @SerialName(JsonKeys.Common.CREATED_AT) override val createdAt: String,
    @SerialName(JsonKeys.Common.UPDATED_AT) override val updatedAt: String,
    @SerialName(JsonKeys.Photo.BLUR_HASH) override val blurHash: String,
    @SerialName(JsonKeys.Photo.ASSET_TYPE) override val assetType: String,
    override val urls: PhotoUrlsScheme
) : PhotoDto
