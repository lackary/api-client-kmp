package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPhoto(
    val altDescription: String?,
    val alternativeSlugs: UnsplashAlternativeSlugs?,
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
    val links: UnsplashPhotoLinks,
    val promotedAt: String?,
    val slug: String,
    val sponsorship: UnsplashSponsorship?,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissions,
    val updatedAt: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser,
    val width: Int
) {

}
