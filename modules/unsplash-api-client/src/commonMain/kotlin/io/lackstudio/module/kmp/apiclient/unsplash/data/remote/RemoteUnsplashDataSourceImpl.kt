package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.core.network.error.toResult
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiService
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashTokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toUnsplashTokenRequest

class RemoteUnsplashDataSourceImpl(
    private val unsplashApiService: UnsplashApiService,
) : RemoteUnsplashDataSource {
    override suspend fun getPhotos(page: Int, perPage: Int): Result<List<UnsplashPhotoResponse>> {
        return  toResult { unsplashApiService.getPhotos(page, perPage) }
    }

    override suspend fun getPhoto(id: String): Result<UnsplashPhotoResponse> {
        return toResult { unsplashApiService.getPhoto(id) }
    }

    override suspend fun exchangeOAuth(oAuthCode: UnsplashOAuthCode): Result<UnsplashTokenResponse> {
        return toResult {
            val unsplashTokenRequest = oAuthCode.toUnsplashTokenRequest()
            unsplashApiService.postOauthToken(unsplashTokenRequest)
        }
    }
}
