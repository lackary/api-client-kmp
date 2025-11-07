package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.util.safeUseCaseCall
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class ExchangeOAuthUseCase(private val repository: UnsplashRepository) :
    UseCase<UnsplashOAuthCode, UnsplashOAuthToken> {
        override suspend operator fun invoke(input: UnsplashOAuthCode): UseCaseResult<UnsplashOAuthToken> {
        return safeUseCaseCall {
            repository.exchangeOAuth(input)
        }
    }
}