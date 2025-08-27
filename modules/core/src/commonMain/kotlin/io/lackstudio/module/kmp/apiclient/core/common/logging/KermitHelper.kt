package io.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig

import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.common.util.isDebuggable

fun setupKermitLogger(tag: String, logWriter: LogWriter) = Logger(
    config = StaticConfig(
        minSeverity = if (isDebuggable) Severity.Verbose else Severity.Info,
        logWriterList = listOf(
            logWriter
        ),

    ),
    tag = tag
)