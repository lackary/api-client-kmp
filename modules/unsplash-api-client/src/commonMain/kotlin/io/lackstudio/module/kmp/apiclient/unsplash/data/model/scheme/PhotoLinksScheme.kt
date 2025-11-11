package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoLinksScheme(
    val self: String,
    val html: String,
    val download: String,
    @SerialName("download_location") val downloadLocation: String
)
