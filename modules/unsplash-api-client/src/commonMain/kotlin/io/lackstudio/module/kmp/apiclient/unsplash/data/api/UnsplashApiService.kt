package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.UnsplashTokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashTokenResponse

interface UnsplashApiService {
    suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashPhotoResponse>
    suspend fun getPhoto(id: String): UnsplashPhotoResponse

    /**
     *  Get an access token by making a POST request according to the Unsplash API documentation.
     *  https://unsplash.com/documentation/user-authentication-workflow#step-2-the-user-approves-the-application
     */
    suspend fun postOauthToken(
        unsplashTokenRequest: UnsplashTokenRequest
    ): UnsplashTokenResponse
}
