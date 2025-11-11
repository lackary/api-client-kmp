package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotoLinks(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
) {

}
