package io.lackstudio.module.kmp.apiclient.app

import android.app.Application
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.helper.AppInitializer
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.initializeKoin
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment.AUTH_SCHEME_PUBLIC

class ExampleApplication : Application() {
    // init the modules of DI
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            allModules = listOf(
                appLoggerModule(appPlatformLogWriter()),
                unsplashModule(tokenType = AUTH_SCHEME_PUBLIC, token = getUnsplashAccessKey()),
                viewModelModule,
            )
        )

        AppInitializer.onApplicationStart(applicationContext.getString(R.string.default_web_client_id))

    }
}