package com.lackstudio.module.kmp.apiclient.core.common.util

import co.touchlab.kermit.Logger

expect val isDebuggable: Boolean

expect fun providerFormattedTimestamp(format: String): String

expect fun getKermitLogger(tag: String): Logger