package io.lackstudio.module.kmp.apiclient.unsplash.di

import io.lackstudio.module.kmp.apiclient.core.common.logging.KtorKermitLogger
import io.lackstudio.module.kmp.apiclient.core.common.logging.setupKermitLogger
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.network.KtorClientFactory
import io.lackstudio.module.kmp.apiclient.core.network.KtorConfig
import io.lackstudio.module.kmp.apiclient.core.network.provideHttpClientEngine
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiService
import io.lackstudio.module.kmp.apiclient.unsplash.data.api.UnsplashApiServiceImpl
import io.lackstudio.module.kmp.apiclient.unsplash.data.local.LocalUnsplashPhotoDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.data.local.LocalUnsplashDataSourceImpl
import io.lackstudio.module.kmp.apiclient.unsplash.domain.repository.UnsplashRepository
import io.lackstudio.module.kmp.apiclient.unsplash.data.repository.UnsplashRepositoryImpl
import io.lackstudio.module.kmp.apiclient.unsplash.data.remote.RemoteUnsplashDataSource
import io.lackstudio.module.kmp.apiclient.unsplash.data.remote.RemoteUnsplashDataSourceImpl
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotoUseCase
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotosUseCase
import org.koin.dsl.module

const val TAG_UNSPLASH_KTOR = "UnsplashKtor"

val unsplashModule = module {
    val ktorConfig = KtorConfig(
        baseUrl = Environment.BASE_API_URL,
        authToken = Environment.getAuthToken()
    )
    val kermitLogger = setupKermitLogger(
        tag = TAG_UNSPLASH_KTOR,
        logWriter = appPlatformLogWriter()
    )
    single {
        KtorClientFactory.createHttpClient(
            engineFactory = provideHttpClientEngine(),
            ktorConfig = ktorConfig,
            logger = KtorKermitLogger(kermitLogger)
        )
    }
    single<UnsplashApiService> { UnsplashApiServiceImpl (get(), get()) }
    single<LocalUnsplashPhotoDataSource> { LocalUnsplashDataSourceImpl(/* inject Room DAO */) }
    single<RemoteUnsplashDataSource> { RemoteUnsplashDataSourceImpl(get()) }
    single<UnsplashRepository> { UnsplashRepositoryImpl(get(), get()) }
    factory { GetPhotosUseCase(get()) }
    factory { GetPhotoUseCase(get()) }
}