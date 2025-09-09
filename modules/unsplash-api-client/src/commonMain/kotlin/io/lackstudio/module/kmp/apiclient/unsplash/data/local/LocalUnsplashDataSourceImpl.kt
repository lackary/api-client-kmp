package io.lackstudio.module.kmp.apiclient.unsplash.data.local

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashPhotoResponse

class LocalUnsplashDataSourceImpl(/* inject your Room DAO here */) : LocalUnsplashPhotoDataSource {
    override suspend fun getPhoto(id: String): UnsplashPhotoResponse? {
        // TODO: implement with Room DAO
        return null
    }

    override suspend fun savePhoto(photo: UnsplashPhotoResponse) {
        TODO("Not yet implemented")
    }

}
