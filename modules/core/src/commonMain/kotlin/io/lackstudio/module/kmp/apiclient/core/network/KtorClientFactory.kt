package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.HttpClientConfig as KtorHttpClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger as KtorLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.contentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import kotlinx.serialization.json.Json

expect fun provideHttpClientEngine(): HttpClientEngine

object KtorClientFactory {

    // Add logger parameter and provide a default value
    private val defaultLogger = object : KtorLogger {
        override fun log(message: String) {
            println("Ktor Client: $message")
        }
    }

    fun createHttpClient(
        engineFactory: HttpClientEngine,
        ktorConfig: KtorConfig,
        logger: KtorLogger = defaultLogger,
        accessTokenProvider: (() -> AccessTokenProvider)? = null,
        // installed parameter, allows dynamic installation of plugins
        clientConfig: KtorHttpClientConfig<*>.() -> Unit = {}
    ): HttpClient {
        return HttpClient(engineFactory) {

            install(Resources)

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

            // *** Fix: Use the install function to apply the Plugin to HttpClientConfig ***
            accessTokenProvider?.let { providerLambda ->
                install(DynamicAuth) {
                    // The scope of this configuration block is DynamicAuthConfig
                    this.providerLambda = providerLambda
                }
            }
            defaultRequest {
                url(ktorConfig.baseUrl)
                contentType(ktorConfig.contentType)
            }
            // (Optional) Handle response validation (e.g., automatically throw ClientRequestException for 4xx/5xx)
            // Default setting, Ktor exceptions will be thrown when the response status code is not 2xx
            expectSuccess = true

            clientConfig()
        }
    }
}

// A configuration class for the dynamic authorization Ktor Plugin.
private class DynamicAuthConfig {
    // This is the only information that needs to be passed
    var providerLambda: (() -> AccessTokenProvider)? = null
}

private val DynamicAuth = createClientPlugin(
    name = "DynamicAuth",
    createConfiguration = ::DynamicAuthConfig
) {
    val providerLambda = pluginConfig.providerLambda ?: return@createClientPlugin

    onRequest { request, _ ->
        request.headers[HttpHeaders.Authorization]?.let { return@onRequest}

        val provider = providerLambda()
        println("provider.getAuthorizationHeader() ${provider.getAuthorizationHeader()}")
        request.headers.append(
            HttpHeaders.Authorization,
            value = provider.getAuthorizationHeader()

        )
    }
}