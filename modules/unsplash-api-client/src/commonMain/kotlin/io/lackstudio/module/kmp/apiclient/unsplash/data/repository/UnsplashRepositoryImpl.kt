package io.lackstudio.module.kmp.apiclient.unsplash.data.repository

import io.lackstudio.module.kmp.apiclient.core.common.extension.toDomainAndThrowIfFailed
import io.lackstudio.module.kmp.apiclient.unsplash.data.local.LocalUnsplashPhotoDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.data.remote.RemoteUnsplashDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toUnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toUnsplashPhoto

internal class UnsplashRepositoryImpl(
    private val remoteUnsplashDataSource: RemoteUnsplashDataSource,
    private val localUnsplashPhotoDataSource: LocalUnsplashPhotoDataSource
) : UnsplashRepository {

    override suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashPhoto> {
        return remoteUnsplashDataSource.getPhotos(page, perPage).toDomainAndThrowIfFailed { dtoList ->
            dtoList.map { it.toUnsplashPhoto() } //
        }
    }

    override suspend fun getPhoto(id: String): UnsplashPhoto {
        return remoteUnsplashDataSource.getPhoto(id).toDomainAndThrowIfFailed { photo ->
//            localUnsplashPhotoDataSource.savePhoto(photo)
            photo.toUnsplashPhoto()
        }
    }

    override suspend fun exchangeOAuth(oAuthCode: UnsplashOAuthCode): UnsplashOAuthToken {
        return remoteUnsplashDataSource.exchangeOAuth(oAuthCode).toDomainAndThrowIfFailed { oAuthToken ->
            oAuthToken.toUnsplashOAuthToken()
        }
    }
}