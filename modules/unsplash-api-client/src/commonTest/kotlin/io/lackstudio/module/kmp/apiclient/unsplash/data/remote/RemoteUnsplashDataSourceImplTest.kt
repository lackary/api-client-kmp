package io.lackstudio.module.kmp.apiclient.unsplash.data.remote

import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiService
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiServiceImpl
import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.lackstudio.module.kmp.apiclient.unsplash.di.BaseKoinTest
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class RemoteUnsplashDataSourceImplTest: BaseKoinTest() {
    private val remoteUnsplashDataSource: RemoteUnsplashDataSource by inject()
    override val testModules = listOf(
        module {
            single<UnsplashApiService> { UnsplashApiServiceImpl (get(), get()) }
            single<RemoteUnsplashDataSource> { RemoteUnsplashDataSourceImpl(get()) }
        }
    )

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
            assertTrue(appException is AppException.Api.NotFound)
            assertEquals(HttpStatusCode.NotFound.value, appException.code)
        }

}