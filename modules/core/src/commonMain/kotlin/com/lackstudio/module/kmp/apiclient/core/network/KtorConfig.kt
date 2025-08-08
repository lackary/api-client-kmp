package com.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.plugins.logging.LogLevel

data class KtorConfig(
    val baseUrl:String,
    val authToken: String? = null,
    val connectTimeoutMillis: Long = 15000L,
    val requestTimeoutMillis: Long = 15000L,
    val socketTimeoutMillis: Long = 15000L,
    val logLevel: LogLevel = LogLevel.ALL
)