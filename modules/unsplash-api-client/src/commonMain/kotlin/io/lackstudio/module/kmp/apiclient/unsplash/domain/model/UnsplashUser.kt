package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashUser(
    val acceptedTos: Boolean,
    val bio: String?,
    val firstName: String,
    val forHire: Boolean,
    val id: String,
    val instagramUsername: String?,
    val lastName: String?,
    val links: UnsplashUserLinks,
    val location: String?,
    val name: String,
    val portfolioUrl: String?,
    val profileImage: UnsplashProfileImage,
    val social: UnsplashSocial,
    val totalCollections: Int,
    val totalIllustrations: Int,
    val totalLikes: Int,
    val totalPhotos: Int,
    val totalPromotedIllustrations: Int,
    val totalPromotedPhotos: Int,
    val twitterUsername: String?,
    val updatedAt: String,
    val username: String
) {

}
