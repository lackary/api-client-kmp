package io.lackstudio.module.kmp.apiclient.unsplash.data.local

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse

interface LocalUnsplashPhotoDataSource {
    suspend fun getPhoto(id: String): UnsplashPhotoResponse?
    suspend fun savePhoto(photo: UnsplashPhotoResponse)
}
