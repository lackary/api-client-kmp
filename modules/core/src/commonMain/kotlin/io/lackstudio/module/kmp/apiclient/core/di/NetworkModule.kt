package io.lackstudio.module.kmp.apiclient.core.di

import io.lackstudio.module.kmp.apiclient.core.network.KtorClientFactory
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module


fun provideNetworkModule(
    ktorConfig: KtorConfig,
    engineFactory: HttpClientEngine,
    loggerModule: Module
) = module {
    includes(loggerModule)
    single {
        KtorClientFactory.createHttpClient(
            engineFactory = engineFactory,
            ktorConfig = ktorConfig,
            logger = get()
        )
    }
}