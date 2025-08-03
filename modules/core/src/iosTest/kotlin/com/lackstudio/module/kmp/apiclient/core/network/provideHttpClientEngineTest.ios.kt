package com.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClientEngineTest(): HttpClientEngine = Darwin.create()