package io.lackstudio.module.kmp.apiclient.core.domain.usecase

interface UseCase<in Input, out Output> {
    suspend operator fun invoke(input: Input): UseCaseResult<Output>
}
