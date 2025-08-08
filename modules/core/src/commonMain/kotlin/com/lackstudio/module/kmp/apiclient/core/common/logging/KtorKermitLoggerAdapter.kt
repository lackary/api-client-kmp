package com.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.Logger
import io.ktor.client.plugins.logging.Logger as KtorLogger


class KtorKermitLoggerAdapter(private val kermitLogger: Logger): KtorLogger {
    override fun log(message: String) {
        when {
            message.contains("RESPONSE: 5") -> kermitLogger.e(message)
            message.contains("RESPONSE: 4") -> kermitLogger.w(message)
            message.contains("REQUEST") -> kermitLogger.i(message)
            message.contains("RESPONSE") -> kermitLogger.i(message)
            else -> kermitLogger.d(message)
        }
    }

}