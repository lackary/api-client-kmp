package io.lackstudio.module.kmp.apiclient.unsplash.data.repository

import io.lackstudio.module.kmp.apiclient.core.common.extension.toDomainAndThrowIfFailed
import io.lackstudio.module.kmp.apiclient.unsplash.data.local.LocalUnsplashPhotoDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.data.remote.RemoteUnsplashDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toMe
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toPreviewPhoto

internal class UnsplashRepositoryImpl(
    private val remoteUnsplashDataSource: RemoteUnsplashDataSource,
    private val localUnsplashPhotoDataSource: LocalUnsplashPhotoDataSource
) : UnsplashRepository {

    override suspend fun getPhotos(page: Int, perPage: Int): List<Photo> {
        return remoteUnsplashDataSource.getPhotos(page, perPage).toDomainAndThrowIfFailed { dtoList ->
            dtoList.map { it.toPreviewPhoto() } //
        }
    }

    override suspend fun getPhoto(id: String): Photo {
        return remoteUnsplashDataSource.getPhoto(id).toDomainAndThrowIfFailed { photo ->
//            localUnsplashPhotoDataSource.savePhoto(photo)
            photo.toPreviewPhoto()
        }
    }

    override suspend fun getMe(): Me {
        return remoteUnsplashDataSource.getMe().toDomainAndThrowIfFailed { me ->
            me.toMe()
        }
    }

    override suspend fun exchangeOAuth(oAuthCode: OAuthCode): OAuthToken {
        return remoteUnsplashDataSource.exchangeOAuth(oAuthCode).toDomainAndThrowIfFailed { oAuthToken ->
            oAuthToken.toOAuthToken()
        }
    }
}
