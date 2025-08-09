package com.lackstudio.module.kmp.apiclient.core.common.logging

import io.ktor.client.plugins.logging.Logger as KtorLogger

class MockKtorLoggerAdapter : KtorLogger {
    val loggedMessages = mutableListOf<String>()
    override fun log(message: String) {
        loggedMessages.add(message)
    }
}