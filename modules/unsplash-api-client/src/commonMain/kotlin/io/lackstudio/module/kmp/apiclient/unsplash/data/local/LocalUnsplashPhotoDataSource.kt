package io.lackstudio.module.kmp.apiclient.unsplash.data.local

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse

interface LocalUnsplashPhotoDataSource {
    suspend fun getPhoto(id: String): PhotoResponse?
    suspend fun savePhoto(photo: PhotoResponse)
}
