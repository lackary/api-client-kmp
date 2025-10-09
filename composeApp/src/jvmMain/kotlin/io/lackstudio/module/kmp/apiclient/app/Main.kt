package io.lackstudio.module.kmp.apiclient.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.ui.screen.App
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.initializeKoin
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule

fun main() = application {
    initializeKoin(
        allModules = listOf(
            appLoggerModule(appPlatformLogWriter()),
            unsplashModule,
            viewModelModule
        )
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "Example App",
    ) {
        App()
    }
}