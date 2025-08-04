package com.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientFactory {

    private val defaultLogger = object : io.ktor.client.plugins.logging.Logger { // Add logger parameter and provide a default value
        override fun log(message: String) {
            println("Ktor Client: $message")
        }
    }

    fun createHttpClient(
        engineFactory: HttpClientEngine,
        baseUrl: String,
        authToken: String? = null,
        authHeaderName: String = HttpHeaders.Authorization,
        logLevel: LogLevel = LogLevel.ALL,
        logger: Logger = defaultLogger
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
                this.level = logLevel
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                authToken?.let { token ->
                    header(authHeaderName, token)
                }
            }
            // (Optional) Handle response validation (e.g., automatically throw ClientRequestException for 4xx/5xx)
            // Default setting, Ktor exceptions will be thrown when the response status code is not 2xx
            expectSuccess = true
        }
    }
}