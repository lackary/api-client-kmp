package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(
    val impressionUrls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    val taglineUrl: String
)
