package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.toUseCaseResult
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class GetPhotoUseCase(private val repository: UnsplashRepository) : UseCase<String, Photo> {
    override suspend operator fun invoke(input: String): UseCaseResult<Photo> {
        return toUseCaseResult { repository.getPhoto(input) }
    }
}
