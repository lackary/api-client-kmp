package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.util.safeUseCaseCall
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class GetPhotoUseCase(private val repository: UnsplashRepository) : UseCase<String, Photo> {
    override suspend operator fun invoke(input: String): UseCaseResult<Photo> {
        return safeUseCaseCall {
            repository.getPhoto(input)
        }
    }
}
