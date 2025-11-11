package io.lackstudio.module.kmp.apiclient.unsplash.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Tags(
    val custom: List<Tag>,
    val aggregated: List<Tag>
)
