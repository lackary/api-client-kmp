package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SponsorshipScheme(
    @SerialName("impression_urls") val impressionUrls: List<String>,
    val tagline: String,
    @SerialName("tagline_url") val taglineUrl: String,
    val sponsor: SponsorScheme
)
