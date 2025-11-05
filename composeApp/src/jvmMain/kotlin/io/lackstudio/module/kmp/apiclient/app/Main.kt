package io.lackstudio.module.kmp.apiclient.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mmk.kmpauth.core.KMPAuthInternalApi
import io.lackstudio.module.kmp.apiclient.app.config.BuildKonfig
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.helper.AppInitializer
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.app.ui.screen.App
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.di.initializeKoin
import io.lackstudio.module.kmp.apiclient.ui.component.webview.initKCEF
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment.AUTH_SCHEME_PUBLIC

@OptIn(KMPAuthInternalApi::class)
fun main() = application {
    AppInitializer.onApplicationStart(BuildKonfig.GOOGLE_SERVICES_WEB_CLIENT_ID)

    initializeKoin(
        allModules = listOf(
            appLoggerModule(appPlatformLogWriter()),
            unsplashModule(tokenType = AUTH_SCHEME_PUBLIC, token = getUnsplashAccessKey()),
            viewModelModule
        )
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Example App",
    ) {
        initKCEF {
            App()
        }
    }
}
