package io.lackstudio.module.kmp.apiclient.core.common.logging

import android.util.Log
import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.LogcatWriter
import co.touchlab.kermit.Severity

class LogcatWriter : LogWriter() {
    override fun log(
        severity: Severity,
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        if (throwable == null) {
            when (severity) {
                Severity.Verbose -> Log.v(tag, message)
                Severity.Debug -> Log.d(tag, message)
                Severity.Info -> Log.i(tag, message)
                Severity.Warn -> Log.w(tag, message)
                Severity.Error -> Log.e(tag, message)
                Severity.Assert -> Log.wtf(tag, message)
            }
        } else {
            when (severity) {
                Severity.Verbose -> Log.v(tag, message, throwable)
                Severity.Debug -> Log.d(tag, message, throwable)
                Severity.Info -> Log.i(tag, message, throwable)
                Severity.Warn -> Log.w(tag, message, throwable)
                Severity.Error -> Log.e(tag, message, throwable)
                Severity.Assert -> Log.wtf(tag, message, throwable)
            }
        }
    }
}