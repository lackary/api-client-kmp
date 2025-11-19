package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.logging.LogLevel
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiService
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiServiceImpl
import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.unsplash.di.BaseKoinTest
import io.ktor.http.HttpStatusCode
import io.lackstudio.module.kmp.apiclient.core.common.logging.setupKermitLogger
import io.lackstudio.module.kmp.apiclient.core.di.KTOR_LOGGER_TAG
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import io.lackstudio.module.kmp.apiclient.unsplash.di.provideHttpClientEngineTest
import io.lackstudio.module.kmp.apiclient.unsplash.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import kotlinx.coroutines.test.runTest
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class RemoteUnsplashDataSourceImplTest: BaseKoinTest() {
    override val appLogWriter: LogWriter
        get() = platformLogWriter()
    override val engine: HttpClientEngine
        get() = provideHttpClientEngineTest()
    override val ktorConfig: KtorConfig
        get() = KtorConfig(
            baseUrl = Environment.BASE_API_URL,
            logLevel = LogLevel.ALL
        )
    override val kermitLogger: Logger
        get() = setupKermitLogger(
            tag = KTOR_LOGGER_TAG,
            logWriter = platformLogWriter()
        )
    override val accessTokenProvider: AccessTokenProvider
        get() = AccessTokenProvider(
            initialTokenType = Environment.AUTH_SCHEME_PUBLIC,
            initialToken = getUnsplashAccessKey()
        )
    override val testModules = listOf(
        module {
            single<UnsplashApiService> { UnsplashApiServiceImpl (get(), get()) }
            single<RemoteUnsplashDataSource> { RemoteUnsplashDataSourceImpl(get()) }
        }
    )

    private val remoteUnsplashDataSource: RemoteUnsplashDataSource by inject()

    @Test
    fun `getPhoto should return a successful Result with a list of photos`() =
        runTest {
            val photoId = "4ICax0QMs8U"
            val result = remoteUnsplashDataSource.getPhoto(photoId)
            assertTrue(result.isSuccess)
            val photo = result.getOrThrow()
            assertNotNull(photo)
            assertEquals(photoId, photo.id)
        }

    @Test
    fun `getPhoto should return a failure Result with NotFound AppException for a 404 response`() =
        runTest {
            val result = remoteUnsplashDataSource.getPhoto("non-existent-id")
            assertTrue(result.isFailure)
            val appException = result.exceptionOrNull()
            assertTrue(appException is RemoteException.Api.NotFound)
            assertEquals(HttpStatusCode.NotFound.value, appException.code)
        }

}
