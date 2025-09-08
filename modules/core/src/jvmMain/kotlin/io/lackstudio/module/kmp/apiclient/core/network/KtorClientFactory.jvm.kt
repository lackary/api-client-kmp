package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun provideHttpClientEngine(): HttpClientEngine = CIO.create()