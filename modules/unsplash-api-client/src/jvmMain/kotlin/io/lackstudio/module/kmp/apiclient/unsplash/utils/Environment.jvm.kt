package io.lackstudio.module.kmp.apiclient.unsplash.utils

import io.lackstudio.module.kmp.apiclient.unsplash.config.BuildKonfig

actual fun getUnsplashApiKey(): String = BuildKonfig.UNSPLASH_API_KEY