package com.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.lackstudio.module.kmp.apiclient.core.common.logging.AppLogWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual val isDebuggable: Boolean
    get() = true

actual fun providerFormattedTimestamp(format: String): String {
    try {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date())
    } catch (e: IllegalArgumentException) {
        println("Invalid date format pattern: $format. Using default format 'yyyy-MM-dd HH:mm:ss'.")
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return defaultFormat.format(Date())
    }
}

actual fun getKermitLogger(tag: String): Logger {
    return Logger(
        config = StaticConfig(
            minSeverity = if (isDebuggable) Severity.Verbose else Severity.Info,
            logWriterList = listOf(
                AppLogWriter()
            )
        ),
        tag = tag
    )
}