package io.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import io.lackstudio.module.kmp.apiclient.core.common.util.providerFormattedTimestamp

class AppLogWriter : LogWriter() {
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        val timestamp =
            providerFormattedTimestamp(LogConfiguration.timestampFormat)
        val formattedLog = "[$timestamp][${severity.name}][$tag] - $message"

        println(formattedLog)

        throwable?.printStackTrace()
    }
}
