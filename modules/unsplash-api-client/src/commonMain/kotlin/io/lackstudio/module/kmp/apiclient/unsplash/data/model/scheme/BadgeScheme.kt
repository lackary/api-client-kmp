package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import kotlinx.serialization.Serializable

@Serializable
data class BadgeScheme(
    val title: String,
    val primary: Boolean,
    val slug: String,
    val link: String? = null
)
