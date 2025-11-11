package io.lackstudio.module.kmp.apiclient.core.network.extension

import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.href
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.takeFrom

// Helper function: Cleans the Host string to ensure only the hostname is retained (without protocol or trailing slash)
fun cleanHost(host: String): String {
    // Remove all protocol prefixes (http://, https://, ftp://, //)
    var cleaned = host.replace(Regex("^\\w+://"), "")
    // Remove all leading or trailing slashes
    cleaned = cleaned.trimStart('/').trimEnd('/')
    return cleaned
}

inline fun <reified T : Any> HttpClient.hrefWithHost(hostname: String, resource: T): String {

    // If the host is null, an empty string, or contains only whitespace characters, an IllegalArgumentException is thrown.
    require(hostname.isNotBlank()) { "Host cannot be blank or empty for hrefWithHost function." }

    val cleanedHostname = cleanHost(hostname)

    // Try to create a URLBuilder with a full URL string that includes the host
    return URLBuilder(protocol = URLProtocol.HTTPS, host = cleanedHostname).apply {

        val pathAndQuery = this@hrefWithHost.href(resource)

        takeFrom(pathAndQuery)

    }.buildString()
}


