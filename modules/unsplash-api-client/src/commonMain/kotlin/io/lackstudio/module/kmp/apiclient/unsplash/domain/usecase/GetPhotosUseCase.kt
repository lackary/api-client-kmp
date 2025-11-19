package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.toUseCaseResult
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

data class GetPhotosParams(val page: Int, val perPage: Int)

class GetPhotosUseCase(private val repository: UnsplashRepository) : UseCase<GetPhotosParams, List<Photo>> {
    override suspend operator fun invoke(input: GetPhotosParams): UseCaseResult<List<Photo>> {
        return toUseCaseResult { repository.getPhotos(input.page, input.perPage) }
    }
}
