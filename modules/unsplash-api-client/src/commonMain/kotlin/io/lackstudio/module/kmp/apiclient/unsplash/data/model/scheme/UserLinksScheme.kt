package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import kotlinx.serialization.Serializable

@Serializable
data class UserLinksScheme(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String
)
