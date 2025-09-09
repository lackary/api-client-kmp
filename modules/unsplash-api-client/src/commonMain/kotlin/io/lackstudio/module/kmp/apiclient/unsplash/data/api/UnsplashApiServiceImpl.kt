package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

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
}
