package io.lackstudio.module.kmp.apiclient.app

import androidx.compose.ui.window.ComposeUIViewController
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.app.ui.screen.App
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.initializeKoin
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment.AUTH_SCHEME_PUBLIC
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController {
    initializeKoin(
        allModules = listOf(
            appLoggerModule(appPlatformLogWriter()),
            unsplashModule(tokenType = AUTH_SCHEME_PUBLIC, token = getUnsplashAccessKey()),
            viewModelModule
        )
    )
    return ComposeUIViewController { App() }
}