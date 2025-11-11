package io.lackstudio.module.kmp.apiclient.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.jsbridge.rememberWebViewJsBridge
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewFileReadType
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLFile
import io.ktor.http.Url
import io.lackstudio.module.kmp.apiclient.ui.generated.resources.Res
import io.lackstudio.module.kmp.apiclient.ui.utils.OAuthSignInJsMessageHandler
import kotlinx.coroutines.launch

const val CODE = "code"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  OAuthWebViewBottomSheet(
    url: String,
    // Used to control the opening and closing of the Bottom Sheet
    onDismissRequest: () -> Unit,
    onAuthCodeReceived: (code: String) -> Unit,
    content: @Composable (onExecuteJavascript: (String) -> Unit) -> Unit = {}
) {
    // Create the state of the Bottom Sheet
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Allows semi-expanded state
    )

    // Create the state of the WebView
    val DEBUG = false
    val webViewState = if (DEBUG) {
        val filName = Res.getUri("files/index.html")
        println("load the html file: $filName")
        rememberWebViewStateWithHTMLFile(
            fileName = filName,
            readType = WebViewFileReadType.COMPOSE_RESOURCE_FILES,
        )
    } else {
        println("WebView load url: $url")
        rememberWebViewState(url = url)
    }.apply {
        webSettings.apply {
            this.backgroundColor = Color.White

            // Ensure other common settings still apply
            isJavaScriptEnabled = true
            desktopWebSettings.apply {
                transparent = false
            }
        }
    }

    val webViewNavigator = rememberWebViewNavigator()

    val currentUrlString = webViewState.lastLoadedUrl

    val coroutineScope = rememberCoroutineScope()

    // *** Logic for clear/logout operation ***
    val onClearAndReloadClicked: () -> Unit = {
        coroutineScope.launch {
            // 1. Clear WebView Cookies
            webViewState.cookieManager.removeAllCookies()
//            webViewState.cookieManager.removeCookies(url) it's doesn't work
            // 2. Reload the original URL (force reload of the login page)
            println("Reload Url: $url")
            webViewNavigator.loadUrl(url)
        }
    }

    // Create a JavaScript Bridge
    val jsBridge = rememberWebViewJsBridge()

    // Register the Native message handler (only executed when the Composable is first created)
    LaunchedEffect(jsBridge) {
        println("KMP JS Bridge registering handler for OAuthSignIn")
        jsBridge.register(OAuthSignInJsMessageHandler { isConfirm ->
            if (isConfirm == "true") {
                println("Sign In")
                onDismissRequest()
            } else {
                println("Sign Out")
                onClearAndReloadClicked()
            }
        })
    }

    // *** JS executor (for error handling) ***
    val onExecuteJavascript: (String) -> Unit = { jsCode ->
        webViewNavigator.evaluateJavaScript(jsCode)
    }

    val queryParams: Map<String, String> = remember(currentUrlString) {
        if (currentUrlString.isNullOrBlank()) {
            emptyMap()
        } else {
            try {
                val url = Url(currentUrlString)
                // Use Ktor's parseUrlEncodedParameters to get a Parameters instance
                val parameters = url.parameters

                // Convert Ktor's Parameters to a Map<String, String> (if only the first value is needed)
                // Note: Ktor's Parameters support multiple parameters with the same name
                parameters.entries().associate {
                    it.key to (it.value.firstOrNull() ?: "")
                }
            } catch (e: Exception) {
                println("the error exception: ${e.message}")
                // Handle invalid URL format errors
                emptyMap()
            }
        }
    }

    // watch the change
    LaunchedEffect(queryParams) {
        if (queryParams.containsKey(CODE)) {
            val code = queryParams[CODE]
            if (!code.isNullOrBlank()) {
                onAuthCodeReceived(code)
            } else {
                println("Authorize Code is null or blank")
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        // You can adjust the Modifier of the Bottom Sheet
        modifier = Modifier.fillMaxWidth()
    ) {
        // Place the WebView inside the Bottom Sheet
        Column(
            // *** Key fix: apply fillMaxHeight(0.8f) to the root container of the content ***
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f) // <--- Set to 80% (0.8f) here
        ) {
            // --- Top area: place the clear button ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center // Center the button
            ) {
                Button(
                    onClick = onClearAndReloadClicked,
                    // Slightly shrink the button
                    modifier = Modifier.height(40.dp)
                ) {
                    Text("Clear Cache and Reload")
                }
            }

            // Check if loadingState is LoadingState.Loading
            val loadingState = webViewState.loadingState
            if (loadingState is LoadingState.Loading) {
                println("Loading...")
                // Use LinearProgressIndicator to display progress
                LinearProgressIndicator(
                    progress = {
                        loadingState.progress // Use the progress provided by WebViewState
                    },
                    modifier = Modifier.fillMaxWidth(),
                    color = ProgressIndicatorDefaults.linearColor,
                    trackColor = ProgressIndicatorDefaults.linearTrackColor,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            } else {
                println("Loading complete.")
            }
            // WebView component
            WebView(
                state = webViewState,
                navigator = webViewNavigator,
                webViewJsBridge = jsBridge,
                // Let WebView fill the remaining space of the Column
                modifier = Modifier.fillMaxSize()
            )

            content(onExecuteJavascript)
        }

        // Note: WebView handling of nested scrolling in Compose,
        // especially on the iOS platform, can sometimes cause issues (e.g., swiping the WebView closes the Bottom Sheet).
        // This is due to the complexity of interaction between native Views and Compose.
        //
        // If you encounter scrolling issues, ensure you are using a newer version of the WebView library,
        // and you may need to check the library's or Compose Multiplatform's Issue Tracker for a workaround.
        //
        // For Android, `AndroidView` (in the Android implementation) is typically used to handle nested scrolling issues.
    }
}
