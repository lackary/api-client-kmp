package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.lackstudio.module.kmp.apiclient.core.network.extension.appendParamsFrom
import kotlinx.serialization.json.Json

inline fun <reified T : Any> buildUrlWithQueryParams(
    host: String,
    pathSegments: List<String>,
    queryParams: T,
    json: Json = Json
): String {
    return URLBuilder(
        protocol = URLProtocol.HTTPS,
        host = host,
        pathSegments = pathSegments
    ).apply {
        // Assume appendParamsFrom is an extension function accessible within this package
        parameters.appendParamsFrom(request = queryParams, json = json)
    }.buildString()
}