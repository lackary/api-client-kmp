package io.lackstudio.module.kmp.apiclient.unsplash.mapper

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoUrlsScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.AlternativeSlugsScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoLinksScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.SponsorScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.SponsorshipScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.UserLinksScheme
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme.PhotoUserScheme
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.AlternativeSlugs
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.PhotoLinks
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.PhotoUrls
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.PhotoUser
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Sponsor
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Sponsorship
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UserLinks

fun PhotoResponse.toPreviewPhoto(): Photo {
    return Photo(
        altDescription = this.altDescription,
        alternativeSlugs = this.alternativeSlugs.toAlternativeSlugs(),
        assetType = this.assetType,
        blurHash = this.blurHash,
        color = this.color,
        createdAt = this.createdAt,
        description = this.description,
        height = height,
        id = this.id,
        likedByUser = this.likedByUser,
        likes = this.likes,
        links = this.links.toPhotoLinks(),
        promotedAt = this.promotedAt,
        slug = this.slug,
        sponsorship = this.sponsorship?.toSponsorship(),
        updatedAt = this.updatedAt,
        urls = this.urls.toPhotoUrls(),
        user = this.user.toPhotoUser(),
        width = this.width,
    )
}

fun AlternativeSlugsScheme.toAlternativeSlugs(): AlternativeSlugs {
    return AlternativeSlugs(
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

fun PhotoLinksScheme.toPhotoLinks(): PhotoLinks {
    return PhotoLinks(
        self = this.self,
        html = this.html,
        download = this.download,
        downloadLocation = this.downloadLocation
    )
}

fun SponsorshipScheme.toSponsorship(): Sponsorship {
    return Sponsorship(
        impressionUrls = this.impressionUrls,
        sponsor = this.sponsor.toSponsor(),
        tagline = this.tagline,
        taglineUrl = this.taglineUrl
    )
}

fun PhotoUrlsScheme.toPhotoUrls(): PhotoUrls {
    return PhotoUrls(
        raw = this.raw,
        full = this.full,
        regular = this.regular,
        small = this.small,
        thumb = this.thumb,
        smallS3 = smallS3
    )
}

fun UserLinksScheme.toUserLinks(): UserLinks {
    return UserLinks(
        html = this.html,
        likes = this.photos,
        photos = this.photos,
        portfolio = this.portfolio,
        self = this.self
    )
}

fun SponsorScheme.toSponsor(): Sponsor {
    return Sponsor(
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
    )
}

fun PhotoUserScheme.toPhotoUser(): PhotoUser {
    return PhotoUser(
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
    )
}
