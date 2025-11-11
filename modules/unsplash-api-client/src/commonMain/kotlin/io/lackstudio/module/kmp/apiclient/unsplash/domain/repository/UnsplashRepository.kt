package io.lackstudio.module.kmp.apiclient.unsplash.domain.repository

import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo

interface UnsplashRepository {
    suspend fun getPhotos(page: Int, perPage: Int): List<Photo>
    suspend fun getPhoto(id: String): Photo
    suspend fun getMe(): Me
    suspend fun exchangeOAuth(oAuthCode: OAuthCode): OAuthToken
}
