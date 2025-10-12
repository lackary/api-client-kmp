package io.lackstudio.module.kmp.apiclient.app

import android.app.Application
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.helper.AppInitializer
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.initializeKoin
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule

class ExampleApplication : Application() {
    // init the modules of DI
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            allModules = listOf(
                appLoggerModule(appPlatformLogWriter()),
                unsplashModule,
                viewModelModule,
            )
        )

        AppInitializer.onApplicationStart(applicationContext.getString(R.string.default_web_client_id))

    }
}