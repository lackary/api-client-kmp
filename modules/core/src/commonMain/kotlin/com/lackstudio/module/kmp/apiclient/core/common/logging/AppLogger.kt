package com.lackstudio.module.kmp.apiclient.core.common.logging

interface AppLogger {
    fun debug(message: String, throwable: Throwable? = null)
    fun info(message: String, throwable: Throwable? = null)
    fun warn(message: String, throwable: Throwable? = null)
    fun error(message: String, throwable: Throwable? = null)
}
