package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.toUseCaseResult
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class GetMeUseCase(private val repository: UnsplashRepository) : UseCase<Unit, Me> {
    override suspend operator fun invoke(input: Unit): UseCaseResult<Me> {
        return toUseCaseResult { repository.getMe() }
    }
}
