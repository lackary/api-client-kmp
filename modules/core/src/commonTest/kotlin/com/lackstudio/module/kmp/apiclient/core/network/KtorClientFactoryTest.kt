package com.lackstudio.module.kmp.apiclient.core.network

import co.touchlab.kermit.ExperimentalKermitApi
import co.touchlab.kermit.Severity
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.http.HttpMethod
import kotlin.test.BeforeTest
import co.touchlab.kermit.TestLogWriter

class KtorClientFactoryTest {
    // 1. Prepare: Create a MockEngine to simulate HTTP requests and responses.
    private val testBaseUrl = "https://example.com"
    private val testApiKey = "test-api-key"
    private val testAuthToken = "Client $testApiKey"
    private val testUrlPath = "/api/v1/data"

//    @OptIn(ExperimentalKermitApi::class)
//    private lateinit var testLogWriter: TestLogWriter
//
//    @OptIn(ExperimentalKermitApi::class)
//    @BeforeTest
//    fun setup() {
//        testLogWriter = TestLogWriter(loggable = Severity.Verbose)
//        co.touchlab.kermit.Logger.setTag("KtorClient")
//        co.touchlab.kermit.Logger.setLogWriters(listOf(testLogWriter))
//    }

    @Test
    fun `createHttpClient should set up defaultRequest with correct baseUrl and headers`() =
        runTest {


            val mockEngine = MockEngine { request ->
                // 2. Verify: Check the details of the request sent to the MockEngine.
                assertEquals(HttpMethod.Get, request.method)
                assertEquals("$testBaseUrl$testUrlPath", request.url.toString())
                assertEquals(
                    testAuthToken,
                    request.headers[HttpHeaders.Authorization]
                )
                assertEquals(
                    ContentType.Application.Json.toString(),
                    request.headers[HttpHeaders.ContentType]
                )

                // 3. Simulate Response: Pretend the server responded successfully.
                respond(
                    content = "{}",
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }

            // 4. Execute: Create an HttpClient using the MockEngine.
            val client = KtorClientFactory.createHttpClient(
                engineFactory = mockEngine,
                baseUrl = testBaseUrl,
                authToken = testAuthToken
            )

            // 5. Trigger: Send a simple request, which will trigger the verification logic in the MockEngine.
            val response = client.get(testUrlPath)

            // 6. Final Assertion: Confirm that the response status code is as expected.
            assertEquals(HttpStatusCode.OK, response.status)
        }

    @Test
    fun `createHttpClient should not set Authorization header if authToken is null`() =
        runTest {
            // 1. Prepare: Create a MockEngine.
            val mockEngine = MockEngine { request ->
                // 2. Verify: Assert that the Authorization header is not present.
                val authHeader = request.headers[HttpHeaders.Authorization]
                assertNull(authHeader, "Authorization header should not be present when authToken is null")

                // 3. Simulate Response: Pretend the server responded successfully.
                respond(content = "{}", status = HttpStatusCode.OK)
            }

            // 4. Execute: Create an HttpClient without passing an authToken.
            val client = KtorClientFactory.createHttpClient(
                engineFactory = mockEngine,
                baseUrl = testBaseUrl
            )

            // 5. Trigger: Send a simple request.
            val response = client.get(testUrlPath)

            // 6. Final Assertion: Confirm the response status code.
            assertEquals(HttpStatusCode.OK, response.status)
        }
    @Test
    fun `createHttpClient should set up HttpTimeout correctly`() =
        runTest {
            // 1. 準備：創建一個 MockEngine，它的回應會延遲
            val mockEngine = MockEngine { request ->
                // 模擬延遲，讓它超過 15 秒的逾時設定
                delay(20000)
                respond("Success", HttpStatusCode.OK)
            }

            // 2. 執行：使用 MockEngine 創建 HttpClient
            val client = KtorClientFactory.createHttpClient(
                engineFactory = mockEngine,
                baseUrl = testBaseUrl
            )

            // 3. 驗證：斷言會拋出 HttpRequestTimeoutException
            assertFailsWith<HttpRequestTimeoutException> {
                client.get(testUrlPath)
            }
        }

    @Test
    fun `createHttpClient should use the provided log level`() = runTest {
        // Define a test logger that records all received log messages
        val logMessages = mutableListOf<String>()
        val testLogger = object : Logger {
            override fun log(message: String) {
                logMessages.add(message)
            }
        }

        // Prepare MockEngine
        val mockEngine = MockEngine {
            respond(
                content = "{\"status\":\"ok\"}",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }

        // Execute: Create HttpClient with custom logger and lowest log level
        val client = KtorClientFactory.createHttpClient(
            engineFactory = mockEngine,
            baseUrl = testBaseUrl,
            logLevel = LogLevel.INFO,
//            logger = testLogger
        )

        // Trigger request
        client.get(testUrlPath)

        // Final assertion: Confirm that the list of log messages is not empty.
        assertTrue(logMessages.isNotEmpty(), "Logger should have captured log messages.")
        assertTrue(logMessages.first().contains("REQUEST"), "Log should contain request details.")
        // Verify if the logger was called
        assertEquals(2, logMessages.size)
    }

//    @OptIn(ExperimentalKermitApi::class)
//    @Test
//    fun `test successful request logs as INFO`() = runTest {
//        // 模擬一個成功的 HTTP 回應
//        val mockEngine = MockEngine { respond("{}", HttpStatusCode.OK) }
//        val client = KtorClientFactory.createHttpClient(
//            engineFactory = mockEngine,
//            baseUrl = "https://api.example.com"
//        )
//
//        // 進行網路請求
//        client.get("https://api.example.com/test")
//
//        // 驗證日誌列表
//        assertEquals(2, testLogWriter.logs.size)
//
//        // 驗證 REQUEST 日誌
//        val requestLog = testLogWriter.logs.firstOrNull { it.message.contains("REQUEST") }
//        assertTrue(requestLog != null, "REQUEST log not found")
//        assertEquals(Severity.Info, requestLog.severity, "REQUEST log should be INFO")
//
//        // 驗證 RESPONSE 日誌
//        val responseLog = testLogWriter.logs.firstOrNull { it.message.contains("RESPONSE") }
//        assertTrue(responseLog != null, "RESPONSE log not found")
//        assertEquals(Severity.Info, responseLog.severity, "RESPONSE log should be INFO")
//    }
}