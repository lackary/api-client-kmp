package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashTokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode

interface RemoteUnsplashDataSource {
    suspend fun getPhotos(page: Int, perPage: Int): Result<List<UnsplashPhotoResponse>>
    suspend fun getPhoto(id: String): Result<UnsplashPhotoResponse>
    suspend fun exchangeOAuth(oAuthCode: UnsplashOAuthCode): Result<UnsplashTokenResponse>
}
