package io.lackstudio.module.kmp.apiclient.unsplash.di

import co.touchlab.kermit.platformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.lackstudio.module.kmp.apiclient.core.di.ktorLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.ktorClientModule
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.http.auth.AuthScheme
import io.lackstudio.module.kmp.apiclient.core.common.logging.KtorKermitLogger
import io.lackstudio.module.kmp.apiclient.core.common.logging.LogConfiguration
import io.lackstudio.module.kmp.apiclient.core.common.logging.setupKermitLogger
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.KTOR_LOGGER_TAG
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import io.lackstudio.module.kmp.apiclient.unsplash.platform.getUnsplashAccessKey
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class
BaseKoinTest: KoinTest {

    protected abstract val testModules: List<Module>
    private val ktorConfig =
        KtorConfig(
            baseUrl = Environment.BASE_API_URL,
            logLevel = LogLevel.ALL)

    val kermitLogger = setupKermitLogger(
        tag = KTOR_LOGGER_TAG,
        logWriter = platformLogWriter()
    )

    @BeforeTest
    fun setupKoin() {
        stopKoin()
        startKoin {
            modules(
                // api-client-unsplash need AppLogger for
                appLoggerModule(platformLogWriter()),
                ktorClientModule(
                    engineFactory = provideHttpClientEngineTest(),
                    ktorConfig = ktorConfig,
                    // ktor client need KtorLogger
                    logger = KtorKermitLogger(kermitLogger)
                ),
                module {
                    single {
                        AccessTokenProvider(
                            initialTokenType = Environment.AUTH_SCHEME_PUBLIC,
                            initialToken = getUnsplashAccessKey()
                        )
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