package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UserDto
import io.lackstudio.module.kmp.apiclient.unsplash.utils.constants.JsonKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SponsorScheme(
    override val id: String,
    @SerialName(JsonKeys.Common.UPDATED_AT) override val updatedAt: String,
    override val username: String,
    override val name: String,
    @SerialName(JsonKeys.User.FIRST_NAME) override val firstName: String,
    @SerialName(JsonKeys.User.LAST_NAME) override val lastName: String? = null,
    @SerialName(JsonKeys.Social.TWITTER_USERNAME) override val twitterUsername: String? = null,
    @SerialName(JsonKeys.Social.PORTFOLIO_URL) override val portfolioUrl: String? = null,
    override val bio: String? = null,
    override val location: String? = null,
    override val links: UserLinksScheme,
    @SerialName(JsonKeys.Common.PROFILE_IMAGE) override val profileImage: ProfileImageScheme,
    @SerialName(JsonKeys.Social.INSTAGRAM_USERNAME) override val instagramUsername: String? = null,
    @SerialName(JsonKeys.Statistics.TOTAL_COLLECTIONS) override val totalCollections: Long,
    @SerialName(JsonKeys.Statistics.TOTAL_LIKES) override val totalLikes: Long,
    @SerialName(JsonKeys.Statistics.TOTAL_PHOTOS) override val totalPhotos: Long,
    @SerialName(JsonKeys.Statistics.TOTAL_PROMOTED_PHOTOS) override val totalPromotedPhotos: Long,
    @SerialName(JsonKeys.Statistics.TOTAL_ILLUSTRATIONS) override val totalIllustrations: Long,
    @SerialName(JsonKeys.Statistics.TOTAL_PROMOTED_ILLUSTRATIONS) override val totalPromotedIllustrations: Long,
    @SerialName(JsonKeys.User.ACCEPTED_TOS) override val acceptedTos: Boolean,
    @SerialName(JsonKeys.Common.FOR_HIRE) override val forHire: Boolean,
    override val social: SocialScheme,
) : UserDto
