package io.lackstudio.module.kmp.apiclient.core.network.extension

import io.ktor.http.ParametersBuilder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ParametersBuilderExtensionTest {
    @Serializable
    data class TestRequest(
        @SerialName("user_id") val userId: String,
        val name: String,
        val age: Int,
        val enabled: Boolean
    )

    @Serializable
    data class NullableRequest(
        val name: String?,
        val level: Int
    )

    @Test
    fun `appendParamsFrom should convert request to parameters`() {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val request = TestRequest(
            userId = "1234567890abcdef",
            name = "Henry",
            age = 30,
            enabled = true
        )

        val builder = ParametersBuilder()

        builder.appendParamsFrom(request, json)

        val params = builder.build()

        assertEquals("1234567890abcdef", params["user_id"])
        assertEquals("Henry", params["name"])
        assertEquals("30", params["age"])
        assertEquals("true", params["enabled"])
    }

    @Test
    fun `null field should not cause crash`() {
        val json = Json
        val builder = ParametersBuilder()

        val request = NullableRequest(null, 1)
        builder.appendParamsFrom(request, json)

        val params = builder.build()

        // null may be encoded → JSON null → "null"
        assertEquals("null", params["name"]) // or check you want null trimmed out
        assertEquals("1", params["level"])
    }
}