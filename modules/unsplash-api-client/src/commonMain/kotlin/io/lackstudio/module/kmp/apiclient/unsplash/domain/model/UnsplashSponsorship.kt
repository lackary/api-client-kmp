package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSponsorship(
    val impressionUrls: List<String>,
    val sponsor: UnsplashSponsor,
    val tagline: String,
    val taglineUrl: String
) {

}
