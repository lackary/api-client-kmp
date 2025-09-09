package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.util.safeUseCaseCall
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class   GetPhotoUseCase(private val repository: UnsplashRepository) {
    suspend operator fun invoke(id: String): UseCaseResult<UnsplashPhoto> {
        return safeUseCaseCall {
            repository.getPhoto(id)
        }
    }
}
