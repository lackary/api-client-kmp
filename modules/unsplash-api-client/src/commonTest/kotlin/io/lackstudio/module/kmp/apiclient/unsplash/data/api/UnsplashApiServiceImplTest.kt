package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.logging.LogLevel
import io.lackstudio.module.kmp.apiclient.core.common.logging.setupKermitLogger
import io.lackstudio.module.kmp.apiclient.core.di.KTOR_LOGGER_TAG
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import io.lackstudio.module.kmp.apiclient.unsplash.di.BaseKoinTest
import io.lackstudio.module.kmp.apiclient.unsplash.di.provideHttpClientEngineTest
import io.lackstudio.module.kmp.apiclient.unsplash.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.assertNotNull

class UnsplashApiServiceImplTest: BaseKoinTest() {

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
            single<UnsplashApiService> { UnsplashApiServiceImpl(get(), get()) }
        }
    )

    private val unsplashApiService: UnsplashApiService by inject()

    @Test
    fun `getPhotos should return a photo list with the correct Authorization header`() =
        runTest {
            val pageSize = 10
            val photos = unsplashApiService.getPhotos(page = 1, perPage = pageSize)

            assertEquals(pageSize, photos.size)
        }

    @Test
    fun `getPhoto should return a single photo with the correct Authorization header`() =
        runTest {
            val photoId = "4ICax0QMs8U"
            val photo = unsplashApiService.getPhoto(id = "4ICax0QMs8U")

            assertNotNull(photo, "Photo should be not present null")
            assertEquals(photoId, photo.id)
        }
}