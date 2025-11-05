package io.lackstudio.module.kmp.apiclient.core.common.util

import android.annotation.SuppressLint
import android.os.Build
import co.touchlab.kermit.platformLogWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.os.Process
import co.touchlab.kermit.LogWriter

actual val isDebuggable: Boolean
    get() = true

actual fun getCurrentTimestamp(format: String): String {
    try {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date())
    } catch (e: IllegalArgumentException) {
        println("Invalid date format pattern: $format. Using default format 'yyyy-MM-dd HH:mm:ss'.")
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return defaultFormat.format(Date())
    }
}

actual fun getCurrentProcessId(): String {
    return Process.myPid().toString()
}

@SuppressLint("NewApi")
actual fun getCurrentThreadId(): String {

    return if (Build.VERSION.SDK_INT >= 36) {
        Thread.currentThread().threadId().toString()
    } else {
        Thread.currentThread().id.toString()
    }
}

actual fun appPlatformLogWriter(): LogWriter = platformLogWriter()