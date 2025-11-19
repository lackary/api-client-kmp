package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val errors: List<String>? = emptyList(),
    val status: Int? = null,
    val error: String? = null,
    @SerialName("error_description") val errorDescription: String? = null
)
