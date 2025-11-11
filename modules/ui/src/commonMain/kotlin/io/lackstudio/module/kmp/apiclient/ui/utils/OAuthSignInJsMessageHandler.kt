package io.lackstudio.module.kmp.apiclient.ui.utils

import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.web.WebViewNavigator

class OAuthSignInJsMessageHandler(
    private val jsCallback: (isConfirm: String) -> Unit
) : IJsMessageHandler {
    override fun methodName(): String = "OAuthSignIn"

    override fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ) {
        // This message is form the params of webview callNative
        val responseText = "KMM Received message. Params: ${message.params}"

        println(responseText)
        this.jsCallback(message.params)

        // **Execute Callback**: Pass the result back to the JavaScript function on the web side.
        // The input must be a string.
        callback("call back done")
    }
}