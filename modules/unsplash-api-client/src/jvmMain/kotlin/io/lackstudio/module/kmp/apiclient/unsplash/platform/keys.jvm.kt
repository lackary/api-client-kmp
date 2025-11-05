package io.lackstudio.module.kmp.apiclient.unsplash.platform

import io.lackstudio.module.kmp.apiclient.unsplash.config.BuildKonfig

internal actual fun getUnsplashAccessKey() = BuildKonfig.UNSPLASH_ACCESS_KEY
internal actual fun getUnsplashSecretKey() = BuildKonfig.UNSPLASH_SECRET_KEY