package io.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import org.slf4j.LoggerFactory
import org.slf4j.MDC

class LogbackWriter : LogWriter() {
    override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        val logger = LoggerFactory.getLogger(tag)

        MDC.put("pid", ProcessHandle.current().pid().toString())
        MDC.put("tid", Thread.currentThread().threadId().toString())

        when (severity) {
            Severity.Verbose, Severity.Debug -> logger.debug(message, throwable)
            Severity.Info -> logger.info(message, throwable)
            Severity.Warn -> logger.warn(message, throwable)
            Severity.Assert, Severity.Error -> logger.error(message, throwable)
        }
    }
}