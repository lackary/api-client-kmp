package io.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import platform.Foundation.*

actual val isDebuggable: Boolean
    get() = true //Platform.isDebugBinary()

actual fun providerFormattedTimestamp(format: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
    return dateFormatter.stringFromDate(NSDate())
}

actual fun getKermitLogger(tag: String): Logger {
    return Logger(
        config = StaticConfig(
            minSeverity = if (isDebuggable) Severity.Verbose else Severity.Info,
            logWriterList = listOf(
                platformLogWriter(),
//                AppLogWriter()
            )
        ),
        tag = tag
    )
}