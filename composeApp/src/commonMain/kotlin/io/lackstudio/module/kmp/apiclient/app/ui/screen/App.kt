package io.lackstudio.module.kmp.apiclient.app.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import io.lackstudio.module.kmp.apiclient.app.di.presentationModule
import io.lackstudio.module.kmp.apiclient.app.ui.viewmodel.AppViewModel
import io.lackstudio.module.kmp.apiclient.composeapp.generated.resources.Res
import io.lackstudio.module.kmp.apiclient.composeapp.generated.resources.compose_multiplatform
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    val appLogger: AppLogger = koinInject()
    val appViewModel: AppViewModel = koinInject()
    appLogger.info("AppKt", "App MaterialTheme create")

    val uiState by appViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        appViewModel.loadPhotos()
    }
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { showContent = !showContent },
                modifier = Modifier.testTag("display_button")
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                        modifier =  Modifier.testTag("multiplatform_logo")
                    )
                    Text(
                        text = "Show Me Example!!",
                        modifier = Modifier.testTag("display_text")
                    )
                }
            }
        }
    }
}

/**
 * This is a Composable function designed specifically for Jetpack Compose previews.
 *
 * This function addresses a common error encountered in the preview environment:
 * `java.lang.IllegalStateException: KoinApplication has not been started`。
 *
 * The error occurs because the Android Studio preview engine does not launch the full Android application
 * (the `Application` class), so the Koin dependency injection framework does not have a chance to be initialized.
 *
 * The solution is to use the `KoinApplication` Composable. This Composable can independently start a Koin instance
 * within the preview environment and load the necessary modules for the application. This ensures that when your `App`
 * Composable is rendered, its dependencies (such as ViewModel, Repository, etc.) can be correctly injected by Koin.
 *
 * By using this approach, you can avoid Koin-related errors during UI previews and render the screen correctly.
 */
@Preview()
@Composable
fun AppPreview() {
    KoinApplication(application = {
        // Configure your Koin modules here
        modules(
            listOf(
                appLoggerModule(appPlatformLogWriter()),
                unsplashModule,
                presentationModule
            )
        )
    }) {
        // Within this block, the App Composable can now use Koin normally.
        App()
    }
}