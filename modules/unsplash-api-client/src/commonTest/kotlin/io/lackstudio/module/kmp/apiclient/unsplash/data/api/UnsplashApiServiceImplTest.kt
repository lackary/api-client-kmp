package io.lackstudio.module.kmp.apiclient.unsplash.data.api

import io.lackstudio.module.kmp.apiclient.unsplash.di.BaseKoinTest
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.assertNotNull

class UnsplashApiServiceImplTest: BaseKoinTest() {

    private val unsplashApiService: UnsplashApiService by inject()
    override val testModules = listOf(
        module {
            single<UnsplashApiService> { UnsplashApiServiceImpl(get(), get()) }
        }
    )

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