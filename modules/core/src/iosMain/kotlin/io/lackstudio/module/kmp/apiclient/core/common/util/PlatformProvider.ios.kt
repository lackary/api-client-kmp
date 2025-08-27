package io.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.LogWriter
import io.lackstudio.module.kmp.apiclient.core.common.logging.NSLogWriter
import platform.Foundation.*
import platform.posix.getpid
import platform.darwin.mach_thread_self

actual val isDebuggable: Boolean
    get() = true //Platform.isDebugBinary()

actual fun getCurrentTimestamp(format: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
    return dateFormatter.stringFromDate(NSDate())
}

actual fun getCurrentProcessId(): String {
    return getpid().toString()
}

actual fun getCurrentThreadId(): String {
    val machThreadID = mach_thread_self()
    return machThreadID.toString()
}

actual fun appPlatformLogWriter(): LogWriter = NSLogWriter()