package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashAlternativeSlugsDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashPhotoLinksDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashPhotoUrlsDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashSponsorshipDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPhotoResponse(
    @SerialName("alt_description")
    val altDescription: String?,
    @SerialName("alternative_slugs")
    val alternativeSlugs: UnsplashAlternativeSlugsDto,
    @SerialName("asset_type")
    val assetType: String,
    @SerialName("blur_hash")
    val blurHash: String,
//    val breadcrumbs: List<Any?>,
    val color: String,
    @SerialName("created_at")
    val createdAt: String,
//    @SerialName("current_user_collections")
//    val currentUserCollections: List<Any?>,
    val description: String?,
    val height: Int,
    val id: String,
    @SerialName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: UnsplashPhotoLinksDto,
    @SerialName("promoted_at")
    val promotedAt: String?,
    val slug: String,
    val sponsorship: UnsplashSponsorshipDto?,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissions,
    @SerialName("updated_at")
    val updatedAt: String,
    val urls: UnsplashPhotoUrlsDto,
    val user: UnsplashUserResponse,
    val width: Int
)