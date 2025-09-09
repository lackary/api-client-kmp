package io.lackstudio.module.kmp.apiclient.unsplash.domain.repository

import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto

interface UnsplashRepository {
    suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashPhoto>
    suspend fun getPhoto(id: String): UnsplashPhoto
}
