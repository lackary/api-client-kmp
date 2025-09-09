package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse

interface UnsplashApiService {
    suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashPhotoResponse>
    suspend fun getPhoto(id: String): UnsplashPhotoResponse
}
