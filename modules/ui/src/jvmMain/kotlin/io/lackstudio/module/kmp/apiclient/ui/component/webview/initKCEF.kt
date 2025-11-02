package io.lackstudio.module.kmp.apiclient.ui.component.webview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multiplatform.webview.util.addTempDirectoryRemovalHook
import dev.datlag.kcef.KCEF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

const val JbrVersion = "jbr-release-17.0.12b1207.37"

@Composable
fun initKCEF(
    content: @Composable ()-> Unit
) {
    addTempDirectoryRemovalHook()
    val restartRequired = remember { mutableStateOf(false) }
    val downloading = remember { mutableStateOf(0F) }
    val initialized = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            KCEF.init(builder = {
                installDir(File("kcef-bundle"))
                progress {
                    onDownloading {
                        downloading.value = it
                    }
                    onInitialized {
                        initialized.value = true
                    }
                }
                download {
                    github {
                        release(JbrVersion)
                    }
                }

                settings {
                    cachePath = File("cache").absolutePath
                }
            }, onError = {
                it?.printStackTrace()
            }, onRestartRequired = {
                restartRequired.value = true
            })
        }
    }
    if (restartRequired.value) {
        RestartRequiredScreen()
    } else {
        if (initialized.value) {
            content()
        } else {
            LoadingScreen(downloadProgress = downloading.value)
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            KCEF.disposeBlocking()
        }
    }
}

@Composable
private fun LoadingScreen(downloadProgress: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = Color.Black,
                strokeWidth = 2.dp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "KCEF",
                fontSize = 18.sp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Initializing...",
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.7f),
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = downloadProgress / 100f,
                modifier =
                    Modifier
                        .size(
                            width = 200.dp,
                            height = 4.dp,
                        ),
                color = Color.Black,
                backgroundColor = Color.Black.copy(alpha = 0.2f),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${downloadProgress.toInt()}%",
                fontSize = 12.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun RestartRequiredScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Restart Required",
                fontSize = 18.sp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Please restart the application to continue.",
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.7f),
            )
        }
    }
}