package com.lackstudio.module.kmp.apiclient.core.common.logging

import co.touchlab.kermit.Logger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import io.ktor.client.plugins.logging.Logger as KtorLogger

class KtorKermitLoggerAdapter(private val kermitLogger: Logger): KtorLogger {

    private val prettyJson = Json { prettyPrint = true }

    /**
     * Tries to parse the string as JSON and format it.
     * If parsing fails, returns the original string.
     */
    private fun prettyPrintJson(jsonString: String): String {
        return try {
            val jsonElement = Json.decodeFromString<JsonElement>(jsonString)
            prettyJson.encodeToString(jsonElement)
        } catch (e: Exception) {
            // If parsing fails, return the original string
            jsonString
        }
    }

    override fun log(message: String) {
        message.lines().forEach { line ->

            val formattedLine = if (
                (line.trim().startsWith("{") && line.trim().endsWith("}")) ||
                (line.trim().startsWith("[") && line.trim().endsWith("]"))
            ) {
                prettyPrintJson(line)
            } else {
                line
            }

            when  {
                line.contains("RESPONSE: 5") -> kermitLogger.e { formattedLine }
                line.contains("RESPONSE: 4") ->  kermitLogger.w { formattedLine }
                line.contains("REQUEST") ->  kermitLogger.i { formattedLine }
                line.contains("RESPONSE") ->  kermitLogger.i { formattedLine }
                line.contains("HEADERS") ->  kermitLogger.d { formattedLine }
                line.contains("BODY") ->  kermitLogger.d { formattedLine }
                line.contains("->") ->  kermitLogger.d { formattedLine }
                line.contains("{") ->  kermitLogger.d { formattedLine }
                else -> kermitLogger.v { formattedLine }
            }
        }
    }
}