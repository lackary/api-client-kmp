package io.lackstudio.module.kmp.apiclient.unsplash.di

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import io.ktor.client.engine.HttpClientEngine
import io.lackstudio.module.kmp.apiclient.core.common.logging.KtorKermitLogger
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.ktorClientModule
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseKoinTest: KoinTest {

    protected abstract val testModules: List<Module>
    protected abstract val appLogWriter: LogWriter
    protected abstract val engine: HttpClientEngine
    protected abstract val ktorConfig: KtorConfig
    protected abstract val kermitLogger: Logger
    protected abstract val accessTokenProvider: AccessTokenProvider

    @BeforeTest
    fun setupKoin() {
        stopKoin()
        startKoin {
            modules(
                // api-client-unsplash need AppLogger for
                appLoggerModule(appLogWriter),
                ktorClientModule(
                    engineFactory = engine,
                    ktorConfig = ktorConfig,
                    // ktor client need KtorLogger
                    logger = KtorKermitLogger(kermitLogger)
                ),
                module {
                    single {
                        accessTokenProvider
                    }
                },
                *testModules.toTypedArray()
            )
        }
    }

    @AfterTest
    fun tearDownKoin() {
        stopKoin()
    }
}