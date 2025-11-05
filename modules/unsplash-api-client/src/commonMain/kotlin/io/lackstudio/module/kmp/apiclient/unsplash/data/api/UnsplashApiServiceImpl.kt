package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.request.UnsplashTokenRequest
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashTokenResponse

class UnsplashApiServiceImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger
) : UnsplashApiService {
    val TAG = "UnsplashApiServiceImpl"
    override suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<UnsplashPhotoResponse> {
        appLogger.info(tag = TAG, message = "getPhotos page $page, perPage $perPage")
        return httpClient.get(Environment.API_PHOTOS) {
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }

    override suspend fun getPhoto(id: String): UnsplashPhotoResponse {
        appLogger.info(tag = TAG, message = "getPhoto id $id")
        return httpClient.get("${Environment.API_PHOTOS}/$id").body()
    }

    override suspend fun postOauthToken(
        unsplashTokenRequest: UnsplashTokenRequest
    ): UnsplashTokenResponse {
        return httpClient.post(urlString = Environment.OAUTH_TOKEN) {
            setBody(unsplashTokenRequest)
        }.body()
    }
}
