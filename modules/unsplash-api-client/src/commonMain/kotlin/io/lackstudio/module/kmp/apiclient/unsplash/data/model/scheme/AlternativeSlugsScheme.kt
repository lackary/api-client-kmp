package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlternativeSlugsScheme(
    @SerialName("en") val english: String,
    @SerialName("es") val spanish: String,
    @SerialName("ja") val japanese: String,
    @SerialName("fr") val french: String,
    @SerialName("it") val italian: String,
    @SerialName("ko") val korean: String,
    @SerialName("de") val german: String,
    @SerialName("pt") val portuguese: String,
    @SerialName("id") val indonesian: String
)
