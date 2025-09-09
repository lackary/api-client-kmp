package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashProfileImageDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashSocialDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashUserLinksDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashUserResponse(
    @SerialName("accepted_tos")
    val acceptedTos: Boolean,
    val bio: String?,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("for_hire")
    val forHire: Boolean,
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("last_name")
    val lastName: String?,
    val links: UnsplashUserLinksDto,
    val location: String?,
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("profile_image")
    val profileImage: UnsplashProfileImageDto,
    val social: UnsplashSocialDto,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_illustrations")
    val totalIllustrations: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("total_promoted_illustrations")
    val totalPromotedIllustrations: Int,
    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("updated_at")
    val updatedAt: String,
    val username: String
)
