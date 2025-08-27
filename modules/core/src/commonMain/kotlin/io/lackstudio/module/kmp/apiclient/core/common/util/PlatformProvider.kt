package io.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger

expect val isDebuggable: Boolean

expect fun getCurrentTimestamp(format: String): String

expect fun getCurrentProcessId(): String

expect fun getCurrentThreadId(): String

expect fun appPlatformLogWriter(): LogWriter
