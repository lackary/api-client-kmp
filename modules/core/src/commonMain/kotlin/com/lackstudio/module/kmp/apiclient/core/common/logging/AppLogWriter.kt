package com.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import com.lackstudio.module.kmp.apiclient.core.common.util.providerFormattedTimestamp
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AppLogWriter : LogWriter() {
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        val timestamp =
            providerFormattedTimestamp(LogConfiguration.timestampFormat)
        val formattedLog = "[$timestamp][${severity.name}][$tag] - $message"

        println(formattedLog)

        throwable?.printStackTrace()
    }
}
