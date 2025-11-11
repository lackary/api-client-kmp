package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.TokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.MeProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.TokenResponse

interface UnsplashApiService {
    suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse>
    suspend fun getPhoto(id: String): PhotoResponse
    suspend fun getMe(): MeProfileResponse

    /**
     *  Get an access token by making a POST request according to the Unsplash API documentation.
     *  https://unsplash.com/documentation/user-authentication-workflow#step-2-the-user-approves-the-application
     */
    suspend fun postOauthToken(
        unsplashTokenRequest: TokenRequest
    ): TokenResponse
}
