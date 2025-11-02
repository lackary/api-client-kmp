package io.lackstudio.module.kmp.apiclient.core.network.extension

import io.ktor.http.ParametersBuilder
import kotlinx.serialization.json.*

/**
 * Converts any @Serializable class or interface instance to URL query parameters.
 * @param request The generic type T must be @Serializable or a polymorphically registered interface.
 */
inline fun <reified T : Any> ParametersBuilder.appendParamsFrom(
    request: T,
    json: Json
) {
    val jsonObj = json.encodeToJsonElement(request).jsonObject
    jsonObj.forEach { (key, value) ->
        append(key, value.toString().trim('"'))
    }
}
