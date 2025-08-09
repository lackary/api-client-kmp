package com.lackstudio.module.kmp.apiclient.core.di

import com.lackstudio.module.kmp.apiclient.core.network.KtorClientFactory
import com.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.logging.Logger as KtorLogger
import org.koin.dsl.module

fun provideMockNetworkModule(
    mockEngine: MockEngine,
    ktorConfig: KtorConfig,
    ktorLogger: KtorLogger,
) = module {
    single { ktorLogger } // 提供 Mock 的日誌實例
    single {
        KtorClientFactory.createHttpClient(
            engineFactory = mockEngine,
            ktorConfig = ktorConfig,
            logger = get() // Koin 會自動注入 MockKtorLoggerAdapter
        )
    }
}