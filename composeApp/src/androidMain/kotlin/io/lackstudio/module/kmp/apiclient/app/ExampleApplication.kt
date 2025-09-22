package io.lackstudio.module.kmp.apiclient.app

import android.app.Application
import io.lackstudio.module.kmp.apiclient.app.di.presentationModule
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
                presentationModule
            )
        )
    }
}