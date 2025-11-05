package io.lackstudio.module.kmp.apiclient.app.ui.screen

import androidx.compose.ui.test.*
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment.AUTH_SCHEME_PUBLIC
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * For the record
 * Currently, you cannot run common Compose Multiplatform tests using android (local) test configurations,
 * so gutter icons in Android Studio, for example, won't be helpful.
 * reference by https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html#d6lghh_55
 * 2025-9-22
 */

class AppTest : KoinTest {

    @BeforeTest
    fun setupKoin() {
        startKoin {
            modules(
                listOf(
                    appLoggerModule(appPlatformLogWriter()),
                    unsplashModule(AUTH_SCHEME_PUBLIC, getUnsplashAccessKey()),
                    viewModelModule
                )
            )
        }
    }

    @AfterTest
    fun tearDownKoin() {
        stopKoin()
    }

    @ExperimentalTestApi
    @Test
    fun testAppScreen() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag("display_button").assertExists()
        onNodeWithText("Show Me Example!!").assertDoesNotExist()

        onNodeWithTag("display_button").performClick()

        onNodeWithTag("display_text").assertExists()
        onNodeWithText("Show Me Example!!").assertIsDisplayed()
        onNodeWithTag("multiplatform_logo").assertIsDisplayed()

    }

}