package io.lackstudio.module.kmp.apiclient.unsplash.mapper

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashAlternativeSlugsDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashPhotoLinksDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashPhotoUrlsDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashProfileImageDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashSocialDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashSponsorDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashSponsorshipDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.UnsplashUserLinksDto
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashUserResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashAlternativeSlugs
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhotoLinks
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhotoUrls
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashProfileImage
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashSocial
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashSponsor
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashSponsorship
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashUser
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashUserLinks


fun UnsplashPhotoResponse.toUnsplashPhoto(): UnsplashPhoto {
    return UnsplashPhoto(
        altDescription = this.altDescription,
        alternativeSlugs = this.alternativeSlugs.toUnsplashAlternativeSlugs(),
        assetType = this.assetType,
        blurHash = this.blurHash,
        color = this.color,
        createdAt = this.createdAt,
        description = this.description,
        height = height,
        id = this.id,
        likedByUser = this.likedByUser,
        likes = this.likes,
        links = this.links.toUnsplashPhotoLinks(),
        promotedAt = this.promotedAt,
        slug = this.slug,
        sponsorship = this.sponsorship?.toUnsplashSponsorship(),
        updatedAt = this.updatedAt,
        urls = this.urls.toUnsplashPhotoUrls(),
        user = this.user.toUnsplashUser(),
        width = this.width,
    )
}

fun UnsplashUserResponse.toUnsplashUser(): UnsplashUser {
    return UnsplashUser(
        acceptedTos = this.acceptedTos,
        bio = this.bio,
        firstName = this.firstName,
        forHire = this.forHire,
        id = this.id,
        instagramUsername = this.instagramUsername,
        lastName = this.lastName,
        links = this.links.toUnsplashUserLinks(),
        location = this.location,
        name = this.name,
        portfolioUrl = this.portfolioUrl,
        profileImage = this.profileImage.toUnsplashProfileImage(),
        social = this.social.toUnsplashSocial(),
        totalCollections = this.totalCollections,
        totalIllustrations = this.totalIllustrations,
        totalLikes = this.totalLikes,
        totalPhotos = this.totalPhotos,
        totalPromotedIllustrations = this.totalPromotedIllustrations,
        totalPromotedPhotos = this.totalPromotedPhotos,
        twitterUsername = this.twitterUsername,
        updatedAt = this.updatedAt,
        username = this.username
    )
}

fun UnsplashAlternativeSlugsDto.toUnsplashAlternativeSlugs(): UnsplashAlternativeSlugs {
    return UnsplashAlternativeSlugs(
        german = this.german,
        english = this.english,
        spanish = this.spanish,
        french = this.french,
        indonesian = this.indonesian,
        italian = this.italian,
        japanese = this.japanese,
        korean = this.korean,
        portuguese = this.portuguese
    )
}

fun UnsplashPhotoLinksDto.toUnsplashPhotoLinks(): UnsplashPhotoLinks {
    return UnsplashPhotoLinks(
        self = this.self,
        html = this.html,
        download = this.download,
        downloadLocation = this.downloadLocation
    )
}

fun UnsplashSponsorshipDto.toUnsplashSponsorship(): UnsplashSponsorship {
    return UnsplashSponsorship(
        impressionUrls = this.impressionUrls,
        sponsor = this.sponsor.toUnsplashSponsor(),
        tagline = this.tagline,
        taglineUrl = this.taglineUrl
    )
}

fun UnsplashPhotoUrlsDto.toUnsplashPhotoUrls(): UnsplashPhotoUrls {
    return UnsplashPhotoUrls(
        raw = this.raw,
        full = this.full,
        regular = this.regular,
        small = this.small,
        thumb = this.thumb,
        smalls3 = this.smalls3
    )
}

fun UnsplashUserLinksDto.toUnsplashUserLinks(): UnsplashUserLinks {
    return UnsplashUserLinks(
        html = this.html,
        likes = this.photos,
        photos = this.photos,
        portfolio = this.portfolio,
        self = this.self
    )
}

fun UnsplashProfileImageDto.toUnsplashProfileImage(): UnsplashProfileImage {
    return UnsplashProfileImage(
        large = this.large,
        medium = this.medium,
        small = this.small
    )
}

fun UnsplashSocialDto.toUnsplashSocial(): UnsplashSocial {
    return UnsplashSocial(
        instagramUsername = this.instagramUsername,
        paypalEmail = this.paypalEmail,
        portfolioUrl = this.portfolioUrl,
        twitterUsername = this.twitterUsername
    )
}

fun UnsplashSponsorDto.toUnsplashSponsor(): UnsplashSponsor {
    return UnsplashSponsor(
        acceptedTos = this.acceptedTos,
        bio = this.bio,
        firstName = this.firstName,
        forHire = this.forHire,
        id = this.id,
        instagramUsername = this.instagramUsername,
        lastName = this.lastName,
        links = this.links.toUnsplashUserLinks(),
        location = this.location,
        name = this.name,
        portfolioUrl = this.portfolioUrl,
        profileImage = this.profileImage.toUnsplashProfileImage(),
        social = this.social.toUnsplashSocial(),
        totalCollections = this.totalCollections,
        totalIllustrations = this.totalPromotedIllustrations,
        totalLikes = this.totalLikes,
        totalPhotos = this.totalPhotos,
        totalPromotedIllustrations = this.totalPromotedIllustrations,
        totalPromotedPhotos = this.totalPromotedPhotos,
        twitterUsername = this.twitterUsername,
        updatedAt = this.updatedAt,
        username = this.username
    )
}
