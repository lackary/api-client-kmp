package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.util.safeUseCaseCall
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class GetMeUseCase(private val repository: UnsplashRepository) : UseCase<Unit, Me> {
    override suspend operator fun invoke(input: Unit): UseCaseResult<Me> {
        return safeUseCaseCall {
            repository.getMe()
        }
    }
}