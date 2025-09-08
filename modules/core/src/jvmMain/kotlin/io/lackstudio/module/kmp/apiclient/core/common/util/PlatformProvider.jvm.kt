package io.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.LogWriter
import io.lackstudio.module.kmp.apiclient.core.common.logging.LogbackWriter
import java.lang.management.ManagementFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual val isDebuggable: Boolean
    get() = true

actual fun getCurrentTimestamp(format: String): String {
    try {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date())
    } catch (e: IllegalArgumentException) {
        println("Invalid date format pattern: $format. Using default format 'yyyy-MM-dd HH:mm:ss'. e: ${e.message}}")
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return defaultFormat.format(Date())
    }
}

actual fun getCurrentProcessId(): String {
    val jvmName = ManagementFactory.getRuntimeMXBean().name
    return jvmName.split("@").firstOrNull() ?: ""
}

actual fun getCurrentThreadId(): String {
    return Thread.currentThread().threadId().toString()
}

actual fun appPlatformLogWriter(): LogWriter = LogbackWriter()