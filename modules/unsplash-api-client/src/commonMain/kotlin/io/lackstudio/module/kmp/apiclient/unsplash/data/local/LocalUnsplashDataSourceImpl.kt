package io.lackstudio.module.kmp.apiclient.unsplash.data.local

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.PhotoResponse

class LocalUnsplashDataSourceImpl(/* inject your Room DAO here */) : LocalUnsplashPhotoDataSource {
    override suspend fun getPhoto(id: String): PhotoResponse? {
        // TODO: implement with Room DAO
        return null
    }

    override suspend fun savePhoto(photo: PhotoResponse) {
        TODO("Not yet implemented")
    }

}
