package io.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.Logger

class AppLoggerImpl(private val kermitLogger: Logger): AppLogger {
    override fun debug(tag: String, message: String, throwable: Throwable?) =
        kermitLogger.d(tag = tag, messageString = message, throwable = throwable)

    override fun info(tag: String, message: String, throwable: Throwable?) =
        kermitLogger.i(tag = tag, messageString = message, throwable = throwable)

    override fun warn(tag: String, message: String, throwable: Throwable?) =
        kermitLogger.w(tag = tag, messageString = message, throwable = throwable)

    override fun error(tag: String, message: String, throwable: Throwable?) =
        kermitLogger.e(tag = tag, messageString = message, throwable = throwable)
}