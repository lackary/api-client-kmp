package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.TokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.MeProfileResponse
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.TokenResponse

class UnsplashApiServiceImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger
) : UnsplashApiService {
    val TAG = "UnsplashApiServiceImpl"
    override suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoResponse> {
        appLogger.debug(tag = TAG, message = "getPhotos page $page, perPage $perPage")
        return httpClient.get(Environment.API_PHOTOS) {
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }

    override suspend fun getPhoto(id: String): PhotoResponse {
        appLogger.debug(tag = TAG, message = "getPhoto id $id")
        return httpClient.get("${Environment.API_PHOTOS}/$id").body()
    }

    override suspend fun getMe(): MeProfileResponse {
        appLogger.debug(tag = TAG, message = "getMe")
        return httpClient.get(Environment.API_ME).body()
    }

    override suspend fun postOauthToken(
        unsplashTokenRequest: TokenRequest
    ): TokenResponse {
        return httpClient.post(urlString = Environment.OAUTH_TOKEN) {
            setBody(unsplashTokenRequest)
        }.body()
    }
}
