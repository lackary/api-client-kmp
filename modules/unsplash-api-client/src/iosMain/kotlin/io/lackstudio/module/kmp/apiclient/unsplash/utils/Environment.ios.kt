package io.lackstudio.module.kmp.apiclient.unsplash.utils

import platform.Foundation.NSBundle
import io.lackstudio.module.kmp.apiclient.unsplash.config.BuildKonfig

actual fun getUnsplashApiKey(): String {
    return BuildKonfig.UNSPLASH_API_KEY
}