package io.lackstudio.module.kmp.apiclient.app.platform

import io.lackstudio.module.kmp.apiclient.app.config.BuildKonfig

actual fun getUnsplashAccessKey() = BuildKonfig.UNSPLASH_ACCESS_KEY
actual fun getUnsplashSecretKey() = BuildKonfig.UNSPLASH_SECRET_KEY