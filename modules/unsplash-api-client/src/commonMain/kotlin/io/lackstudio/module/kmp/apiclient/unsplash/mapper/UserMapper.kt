package io.lackstudio.module.kmp.apiclient.unsplash.mapper

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.BadgeScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.MetaScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PreviewPhotoScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.ProfileImageScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.SocialScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.MeProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UserProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.TagScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.TagsScheme
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Badge
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Meta
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.PreviewPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.ProfileImage
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Social
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Tag
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Tags
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UserProfile

fun MeProfileResponse.toMe(): Me {
    return Me(
        id = this.id,
        updatedAt = this.updatedAt,
        username = this.username,
        name = this.name,
        firstName = this.firstName,
        lastName = this.lastName,
        twitterUsername = this.twitterUsername,
        portfolioUrl = this.portfolioUrl,
        bio = this.bio,
        location = this.location,
        links = this.links.toUserLinks(),
        profileImage = this.profileImage.toProfileImage(),
        instagramUsername = this.instagramUsername,
        totalCollections = this.totalCollections,
        totalLikes = this.totalLikes,
        totalPhotos = this.totalPhotos,
        totalPromotedPhotos = this.totalPromotedPhotos,
        totalIllustrations = this.totalIllustrations,
        totalPromotedIllustrations = this.totalPromotedIllustrations,
        acceptedTos = this.acceptedTos,
        forHire = this.forHire,
        social = this.social.toSocial(),
        photos = this.photos.map { previewPhotoScheme -> previewPhotoScheme.toPreviewPhoto() },
        badge = this.badge?.toBadge(),
        tags = this.tags.toTags(),
        allowMessages = this.allowMessages,
        numericId = this.numericId,
        downloads = this.downloads,
        meta = this.meta.toMeta(),
        uid = this.uid,
        confirmed = this.confirmed
    )
}

fun UserProfileResponse.toUserProfile(): UserProfile {
    return UserProfile(
        id = this.id,
        updatedAt = this.updatedAt,
        username = this.username,
        name = this.name,
        firstName = this.firstName,
        lastName = this.lastName,
        twitterUsername = this.twitterUsername,
        portfolioUrl = this.portfolioUrl,
        bio = this.bio,
        location = this.location,
        links = this.links.toUserLinks(),
        profileImage = this.profileImage.toProfileImage(),
        instagramUsername = this.instagramUsername,
        totalCollections = this.totalCollections,
        totalLikes = this.totalLikes,
        totalPhotos = this.totalPhotos,
        totalPromotedPhotos = this.totalPromotedPhotos,
        totalIllustrations = this.totalIllustrations,
        totalPromotedIllustrations = this.totalPromotedIllustrations,
        acceptedTos = this.acceptedTos,
        forHire = this.forHire,
        social = this.social.toSocial(),
        photos = this.photos.map { previewPhotoScheme -> previewPhotoScheme.toPreviewPhoto() },
        badge = this.badge?.toBadge(),
        tags = this.tags.toTags(),
        allowMessages = this.allowMessages,
        numericId = this.numericId,
        downloads = this.downloads,
        meta = this.meta.toMeta()
    )
}

fun ProfileImageScheme.toProfileImage(): ProfileImage {
    return ProfileImage(
        large = this.large,
        medium = this.medium,
        small = this.small
    )
}

fun BadgeScheme.toBadge(): Badge {
    return Badge(
        title = this.title,
        primary = this.primary,
        slug = this.slug,
        link = this.link
    )
}

fun PreviewPhotoScheme.toPreviewPhoto(): PreviewPhoto {
    return PreviewPhoto(
        id = this.id,
        slug = this.slug,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        blurHash = this.blurHash,
        assetType = this.assetType,
        urls = this.urls.toPhotoUrls()
    )
}

fun MetaScheme.toMeta(): Meta {
    return Meta(
        index = this.index
    )
}

fun SocialScheme.toSocial(): Social {
    return Social(
        instagramUsername = this.instagramUsername,
        portfolioUrl = this.portfolioUrl,
        twitterUsername = this.twitterUsername,
        paypalEmail = this.paypalEmail
    )
}

fun TagScheme.toTag(): Tag {
    return Tag(
        type = this.type,
        title = this.title
    )
}

fun TagsScheme.toTags(): Tags {
    return Tags(
        custom = this.custom.map { tagScheme -> tagScheme.toTag() },
        aggregated = this.aggregated.map { tagScheme -> tagScheme.toTag() }
    )
}
