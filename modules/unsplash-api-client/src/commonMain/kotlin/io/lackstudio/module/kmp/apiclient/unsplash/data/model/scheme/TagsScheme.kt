package io.lackstudio.module.kmp.apiclient.unsplash.data.model.scheme

import io.lackstudio.module.kmp.apiclient.unsplash.data.model.dto.TagDto
import kotlinx.serialization.Serializable

@Serializable
data class TagsScheme(
    val custom: List<TagScheme>,
    val aggregated: List<TagScheme>
)
