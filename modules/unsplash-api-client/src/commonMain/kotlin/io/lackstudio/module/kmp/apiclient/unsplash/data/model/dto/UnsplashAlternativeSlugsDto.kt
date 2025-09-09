package io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashAlternativeSlugsDto(
    @SerialName("de")
    val german: String,
    @SerialName("en")
    val english: String,
    @SerialName("es")
    val spanish: String,
    @SerialName("fr")
    val french: String,
    @SerialName("id")
    val indonesian: String,
    @SerialName("it")
    val italian: String,
    @SerialName("ja")
    val japanese: String,
    @SerialName("ko")
    val korean: String,
    @SerialName("pt")
    val portuguese: String
)