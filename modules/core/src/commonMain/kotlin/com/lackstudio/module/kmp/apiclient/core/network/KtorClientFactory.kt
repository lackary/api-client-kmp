package com.lackstudio.module.kmp.apiclient.core.network

import co.touchlab.kermit.Severity
import com.lackstudio.module.kmp.apiclient.core.common.logging.KtorKermitLoggerAdapter
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as KtorLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientFactory {

    private val defaultLogger = object : KtorLogger { // Add logger parameter and provide a default value
        override fun log(message: String) {
            println("Ktor Client: $message")
        }
    }

    fun createHttpClient(
        engineFactory: HttpClientEngine,
        ktorConfig: KtorConfig,
        logger: KtorLogger = defaultLogger
    ): HttpClient {
        return HttpClient(engineFactory) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                this.logger = logger
                this.level = ktorConfig.logLevel
            }
            install(HttpTimeout) {
                requestTimeoutMillis = ktorConfig.requestTimeoutMillis
                connectTimeoutMillis = ktorConfig.connectTimeoutMillis
                socketTimeoutMillis = ktorConfig.socketTimeoutMillis
            }
            defaultRequest {
                url(ktorConfig.baseUrl)
                contentType(ContentType.Application.Json)
                ktorConfig.authToken?.let { token ->
                    header(HttpHeaders.Authorization, token)
                }
            }
            // (Optional) Handle response validation (e.g., automatically throw ClientRequestException for 4xx/5xx)
            // Default setting, Ktor exceptions will be thrown when the response status code is not 2xx
            expectSuccess = true
        }
    }
}