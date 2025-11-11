package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotoUser(
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: UserLinks,
    val profileImage: ProfileImage,
    val instagramUsername: String?,
    val totalCollections: Long,
    val totalLikes: Long,
    val totalPhotos: Long,
    val totalPromotedPhotos: Long,
    val totalIllustrations: Long,
    val totalPromotedIllustrations: Long,
    val acceptedTos: Boolean,
    val forHire: Boolean,
    val social: Social
)
