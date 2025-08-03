package com.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun provideHttpClientEngine(): HttpClientEngine = Android.create()