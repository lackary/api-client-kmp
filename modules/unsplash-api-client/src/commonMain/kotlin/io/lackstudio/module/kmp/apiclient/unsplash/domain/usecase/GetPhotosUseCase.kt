package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.util.safeUseCaseCall
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

data class GetPhotosParams(val page: Int, val perPage: Int)

class GetPhotosUseCase(private val repository: UnsplashRepository) : UseCase<GetPhotosParams, List<UnsplashPhoto>> {
    override suspend operator fun invoke(input: GetPhotosParams): UseCaseResult<List<UnsplashPhoto>> {
        return safeUseCaseCall { repository.getPhotos(input.page, input.perPage) }
    }
}