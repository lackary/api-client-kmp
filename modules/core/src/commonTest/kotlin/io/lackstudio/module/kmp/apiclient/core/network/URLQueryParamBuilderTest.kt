package io.lackstudio.module.kmp.apiclient.core.network

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class URLQueryParamBuilderTest {

    // Define a data class for testing, which will act as query parameters
    @Serializable
    private data class SampleQueryParams(
        val userId: Int,
        val isActive: Boolean,
        val filter: String? = null // Test optional parameter (Nullable field)
    )

    @Test
    fun `buildUrlWithQueryParams should correctly build URL with host and path`() {
        // Arrange
        val host = "api.example.com"
        val pathSegments = listOf("v1", "users", "search")
        val expectedBaseUrl = "https://$host/v1/users/search"

        // Act
        val result = buildUrlWithQueryParams(
            host = host,
            pathSegments = pathSegments,
            // Pass a Unit object if there are no query parameters
            queryParams = Unit
        )

        // Assert
        assertEquals(expectedBaseUrl, result, "The URL should contain the correct host and path segments without any query parameters.")
    }

    @Test
    fun `buildUrlWithQueryParams should correctly append query parameters from data class`() {
        // Arrange
        val host = "api.example.com"
        val pathSegments = listOf("data")
        val queryParams = SampleQueryParams(
            userId = 1001,
            isActive = true,
            filter = "latest"
        )
        // The expected URL format. The order of parameters might vary depending on the implementation of appendParamsFrom.
        // Here, we assume a common and reasonable order.
        val expectedUrl = "https://api.example.com/data?userId=1001&isActive=true&filter=latest"

        // Act
        val result = buildUrlWithQueryParams(
            host = host,
            pathSegments = pathSegments,
            queryParams = queryParams
        )

        // Assert
        assertEquals(expectedUrl, result, "The URL should contain all query parameters from the data class.")
    }

    @Test
    fun `buildUrlWithQueryParams should exclude null query parameters`() {
        // Arrange
        val host = "api.example.com"
        val pathSegments = listOf("data")
        val queryParams = SampleQueryParams(
            userId = 200,
            isActive = false,
            filter = null // 'filter' is null
        )
        // The expected result should not include the 'filter' parameter
        val expectedUrl = "https://api.example.com/data?userId=200&isActive=false"

        // Act
        val result = buildUrlWithQueryParams(
            host = host,
            pathSegments = pathSegments,
            queryParams = queryParams
        )

        // Assert
        assertEquals(expectedUrl, result, "The URL should exclude null/omitted query parameters.")
    }

    @Test
    fun `buildUrlWithQueryParams should handle multiple path segments correctly`() {
        // Arrange
        val host = "api.example.com"
        val pathSegments = listOf("path", "to", "resource")
        val queryParams = SampleQueryParams(1, true, null)
        val expectedUrl = "https://api.example.com/path/to/resource?userId=1&isActive=true"

        // Act
        val result = buildUrlWithQueryParams(
            host = host,
            pathSegments = pathSegments,
            queryParams = queryParams
        )

        // Assert
        assertEquals(expectedUrl, result, "Multiple path segments should be joined by slashes.")
    }
}