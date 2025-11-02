package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.plugins.logging.LogLevel
import io.ktor.http.ContentType

data class KtorConfig(
    val baseUrl:String,
    val connectTimeoutMillis: Long = 15000L,
    val requestTimeoutMillis: Long = 15000L,
    val socketTimeoutMillis: Long = 15000L,
    val logLevel: LogLevel = LogLevel.ALL,
    val contentType: ContentType = ContentType.Application.Json,
)