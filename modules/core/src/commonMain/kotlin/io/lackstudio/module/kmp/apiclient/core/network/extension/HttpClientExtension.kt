package io.lackstudio.module.kmp.apiclient.core.network.extension

import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.href
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.takeFrom

inline fun <reified T : Any> HttpClient.hrefWithHost(host: String, resource: T): String =
    URLBuilder(protocol = URLProtocol.HTTPS, host).apply {
        // 1. URLBuilder(host) has already set the protocol and host.

        // 2. this@hrefWithHost.href(resource)
        //    This part returns a Path (e.g., /oauth/authorize) and Query Params (?client_id=...).
        val pathAndQuery = this@hrefWithHost.href(resource)

        // 3. takeFrom merges the path and query params into the URLBuilder.
        //    (Note: If the resource is a @Resource, href might return a full URL,
        //     but typically under the Resources plugin, it only returns the Path and Query,
        //     unless you haven't configured a base URL).
        takeFrom(pathAndQuery)

        // Ktor's takeFrom(String) intelligently parses and merges the path and query parameters.
    }.buildString()