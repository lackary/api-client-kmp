package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AlternativeSlugs(
    val english: String,
    val spanish: String,
    val japanese: String,
    val french: String,
    val italian: String,
    val korean: String,
    val german: String,
    val portuguese: String,
    val indonesian: String
)
