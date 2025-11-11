package io.lackstudio.module.kmp.apiclient.core.domain

import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult

interface UseCase<in Input, out Output> {
    suspend operator fun invoke(input: Input): UseCaseResult<Output>
}