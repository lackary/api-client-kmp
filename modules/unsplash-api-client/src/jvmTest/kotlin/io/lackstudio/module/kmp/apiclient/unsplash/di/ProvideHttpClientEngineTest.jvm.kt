package io.lackstudio.module.kmp.apiclient.unsplash.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun provideHttpClientEngineTest(): HttpClientEngine = CIO.create()