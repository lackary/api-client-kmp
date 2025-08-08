package com.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.Logger

class AppLoggerImpl(private val kermitLogger: Logger): AppLogger {
    override fun debug(message: String, throwable: Throwable?) =
        kermitLogger.d(message, throwable)

    override fun info(message: String, throwable: Throwable?) =
        kermitLogger.i(message, throwable)

    override fun warn(message: String, throwable: Throwable?) =
        kermitLogger.w(message, throwable)

    override fun error(message: String, throwable: Throwable?) =
        kermitLogger.e(message, throwable)
}