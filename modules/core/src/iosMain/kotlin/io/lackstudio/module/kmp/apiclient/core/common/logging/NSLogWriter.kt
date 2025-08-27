package io.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import platform.Foundation.NSLog

class NSLogWriter : LogWriter() {

    override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {

        // Check if throwable exists
        val formattedMessage = if (throwable != null) {
            // Add throwable's message and stack trace to the log
            val throwableInfo = throwable.stackTraceToString()
            "$message\n$throwableInfo"
        } else {
            message
        }

        NSLog("[%s] [%s] %s", tag, severity.name, formattedMessage)
    }
}