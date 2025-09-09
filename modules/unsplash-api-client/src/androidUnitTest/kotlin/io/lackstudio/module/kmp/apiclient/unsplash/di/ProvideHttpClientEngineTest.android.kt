package io.lackstudio.module.kmp.apiclient.unsplash.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun provideHttpClientEngineTest(): HttpClientEngine = Android.create()