package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val altDescription: String?,
    val alternativeSlugs: AlternativeSlugs?,
    val assetType: String,
    val blurHash: String,
//    val breadcrumbs: List<Any?>,
    val color: String,
    val createdAt: String,
//    val currentUserCollections: List<Any?>,
    val description: String?,
    val height: Int,
    val id: String,
    val likedByUser: Boolean,
    val likes: Int,
    val links: PhotoLinks,
    val promotedAt: String?,
    val slug: String,
    val sponsorship: Sponsorship?,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissions,
    val updatedAt: String,
    val urls: PhotoUrls,
    val user: PhotoUser,
    val width: Int
)
