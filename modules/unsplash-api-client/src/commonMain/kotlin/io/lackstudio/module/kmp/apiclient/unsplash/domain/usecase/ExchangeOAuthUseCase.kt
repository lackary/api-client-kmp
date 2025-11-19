package io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase

import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCase
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.toUseCaseResult
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository

class ExchangeOAuthUseCase(private val repository: UnsplashRepository) :
    UseCase<OAuthCode, OAuthToken> {
        override suspend operator fun invoke(input: OAuthCode): UseCaseResult<OAuthToken> {
        return toUseCaseResult {
            repository.exchangeOAuth(input)
        }
    }
}
