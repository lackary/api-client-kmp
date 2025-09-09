package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSponsorshipDto(
    @SerialName("impression_urls")
    val impressionUrls: List<String>,
    val sponsor: UnsplashSponsorDto,
    val tagline: String,
    @SerialName("tagline_url")
    val taglineUrl: String
)