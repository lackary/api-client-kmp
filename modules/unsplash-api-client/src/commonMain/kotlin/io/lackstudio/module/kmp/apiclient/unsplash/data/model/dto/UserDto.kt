package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.ProfileImageScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.SocialScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.UserLinksScheme

interface UserDto {
    val id: String
    val updatedAt: String
    val username: String
    val name: String
    val firstName: String
    val lastName: String?
    val twitterUsername: String?
    val portfolioUrl: String?
    val bio: String?
    val location: String?
    val links: UserLinksScheme
    val profileImage: ProfileImageScheme
    val instagramUsername: String?
    val totalCollections: Long
    val totalLikes: Long
    val totalPhotos: Long
    val totalPromotedPhotos: Long
    val totalIllustrations: Long
    val totalPromotedIllustrations: Long
    val acceptedTos: Boolean
    val forHire: Boolean
    val social: SocialScheme
}
