package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiService
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.MeProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.TokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.mapper.toTokenRequest

class RemoteUnsplashDataSourceImpl(
    private val unsplashApiService: UnsplashApiService,
) : RemoteUnsplashDataSource {
    override suspend fun getPhotos(page: Int, perPage: Int): Result<List<PhotoResponse>> {
        return  toUnsplashResult { unsplashApiService.getPhotos(page, perPage) }
    }

    override suspend fun getPhoto(id: String): Result<PhotoResponse> {
        return toUnsplashResult { unsplashApiService.getPhoto(id) }
    }

    override suspend fun getMe(): Result<MeProfileResponse> {
        return toUnsplashResult { unsplashApiService.getMe() }
    }

    override suspend fun exchangeOAuth(oAuthCode: OAuthCode): Result<TokenResponse> {
        return toUnsplashResult {
            val unsplashTokenRequest = oAuthCode.toTokenRequest()
            unsplashApiService.postOauthToken(unsplashTokenRequest)
        }
    }
}
