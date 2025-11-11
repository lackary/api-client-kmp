package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoUrlsScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.AlternativeSlugsScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoLinksScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.SponsorshipScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoUserScheme
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    @SerialName("alt_description")
    val altDescription: String?,
    @SerialName("alternative_slugs")
    val alternativeSlugs: AlternativeSlugsScheme,
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
    val links: PhotoLinksScheme,
    @SerialName("promoted_at")
    val promotedAt: String?,
    val slug: String,
    val sponsorship: SponsorshipScheme?,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissions,
    @SerialName("updated_at")
    val updatedAt: String,
    val urls: PhotoUrlsScheme,
    val user: PhotoUserScheme,
    val width: Int
)
