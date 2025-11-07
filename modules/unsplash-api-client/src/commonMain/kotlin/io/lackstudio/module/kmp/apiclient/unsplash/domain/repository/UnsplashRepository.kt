package io.lackstudio.module.kmp.apiclient.unsplash.domain.repository

import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashUser

interface UnsplashRepository {
    suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashPhoto>
    suspend fun getPhoto(id: String): UnsplashPhoto
    suspend fun getMe(): UnsplashUser
    suspend fun exchangeOAuth(oAuthCode: UnsplashOAuthCode): UnsplashOAuthToken
}
