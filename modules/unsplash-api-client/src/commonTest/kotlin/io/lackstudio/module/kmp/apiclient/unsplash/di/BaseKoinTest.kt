package io.lackstudio.module.kmp.apiclient.unsplash.di

import co.touchlab.kermit.platformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.lackstudio.module.kmp.apiclient.core.di.ktorLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.provideNetworkModule
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.ktor.client.plugins.logging.LogLevel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class
BaseKoinTest: KoinTest {

    protected abstract val testModules: List<Module>
    private val ktorConfig =
        KtorConfig(
            baseUrl = Environment.BASE_API_URL,
            authToken = Environment.getAuthToken(),
            logLevel = LogLevel.ALL)

    @BeforeTest
    fun setupKoin() {
        stopKoin()
        startKoin {
            modules(
                appLoggerModule(platformLogWriter()),
                provideNetworkModule(
                    engineFactory = provideHttpClientEngineTest(),
                    ktorConfig = ktorConfig,
                    loggerModule = ktorLoggerModule(platformLogWriter())
                ),
                *testModules.toTypedArray()
            )
        }
    }

    @AfterTest
    fun tearDownKoin() {
        stopKoin()
    }
}