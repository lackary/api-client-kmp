package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.MeProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.TokenResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode

interface RemoteUnsplashDataSource {
    suspend fun getPhotos(page: Int, perPage: Int): Result<List<PhotoResponse>>
    suspend fun getPhoto(id: String): Result<PhotoResponse>
    suspend fun getMe(): Result<MeProfileResponse>
    suspend fun exchangeOAuth(oAuthCode: OAuthCode): Result<TokenResponse>
}
