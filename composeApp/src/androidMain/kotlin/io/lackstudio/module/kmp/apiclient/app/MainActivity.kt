package io.lackstudio.module.kmp.apiclient.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.lackstudio.module.kmp.apiclient.app.ui.screen.App
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val appLogger: AppLogger by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        appLogger.info(tag = "MainActivity", message = "App MainActivity onCreate")
        setContent {
            App()
        }
    }
}
