package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashAlternativeSlugs(
    val german: String,
    val english: String,
    val spanish: String,
    val french: String,
    val indonesian: String,
    val italian: String,
    val japanese: String,
    val korean: String,
    val portuguese: String
) {

}
