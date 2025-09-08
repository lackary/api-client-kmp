package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClientEngine(): HttpClientEngine = Darwin.create()