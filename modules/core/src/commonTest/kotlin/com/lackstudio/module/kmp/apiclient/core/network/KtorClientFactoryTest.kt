package com.lackstudio.module.kmp.apiclient.core.network

import com.lackstudio.module.kmp.apiclient.core.common.logging.MockKtorLoggerAdapter
import com.lackstudio.module.kmp.apiclient.core.di.provideMockNetworkModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as KtorLogger
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
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.pluginOrNull
import io.ktor.http.HttpMethod
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.Serializable
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertNotNull

// Test data model
@Serializable
data class TestData(val status: String, val message: String)

class KtorClientFactoryTest: KoinTest {

    // 1. Prepare: Create a MockEngine to simulate HTTP requests and responses.
    private val testBaseUrl = "https://example.com"
    private val testApiKey = "test-api-key"
    private val testAuthToken = "Client $testApiKey"
    private val testUrlPath = "/success"

    private val httpClient: HttpClient by inject()
    private val mockLogger: KtorLogger by inject()
    private val mockEngine: MockEngine = MockEngine { request ->
        when (request.url.encodedPath) {
            "/success" -> respond(
                content = ByteReadChannel("{\"status\":\"OK\",\"message\":\"Success!\"}"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
            "/not-found" -> respond(
                content = ByteReadChannel("{\"error\":\"Not Found\"}"),
                status = HttpStatusCode.NotFound,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
            "/server-error" -> respond(
                content = ByteReadChannel("{\"error\":\"Internal Server Error\"}"),
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
            else -> error("Unhandled ${request.url.encodedPath}")
        }
    }

    private val testKtorConfig = KtorConfig(
        baseUrl = testBaseUrl,
        authToken = testAuthToken,
        logLevel = LogLevel.ALL
    )

    @BeforeTest
    fun setupKoin() {
        stopKoin()
        startKoin {
            modules(
                provideMockNetworkModule(
                    ktorConfig = testKtorConfig,
                    ktorLogger = MockKtorLoggerAdapter(),
                    mockEngine = mockEngine
                )
            )
        }
    }

    @AfterTest
    fun tearDownKoin() {
        stopKoin()
    }

    @Test
    fun `client should be configured with correct base URL and authorization header`() = runTest {
        httpClient.get(testUrlPath)

        val request = mockEngine.requestHistory.last()

        assertEquals("$testBaseUrl$testUrlPath", request.url.toString())
        val authHeader = request.headers[HttpHeaders.Authorization]
        assertNotNull(authHeader)
        assertEquals(testAuthToken, authHeader)
    }

    @Test
    fun `client should handle successful response and deserialize data`() = runTest {
        val response: TestData = httpClient.get(testUrlPath).body()
        assertEquals(HttpStatusCode.OK.description, response.status)
        assertEquals("Success!", response.message)
    }

    @Test
    fun `client should throw ClientRequestException for 4xx status codes`() = runTest {
        assertFailsWith<ClientRequestException> {
            httpClient.get("/not-found")
        }
    }

    @Test
    fun `client should throw ServerResponseException for 5xx status codes`() = runTest {
        assertFailsWith<ServerResponseException> {
            httpClient.get("/server-error")
        }
    }

    @Test
    fun `client should have HttpTimeout plugin configured`() = runTest {
        // Check if HttpTimeout plugin is installed
        val httpTimeout = httpClient.pluginOrNull(HttpTimeout)
        assertNotNull(httpTimeout)

        // Check if timeout settings are correct
//        assertEquals(testKtorConfig.requestTimeoutMillis, httpTimeout.requestTimeoutMillis)
//        assertEquals(testKtorConfig.connectTimeoutMillis, httpTimeout.connectTimeoutMillis)
//        assertEquals(testKtorConfig.socketTimeoutMillis, httpTimeout.socketTimeoutMillis)
    }

    @Test
    fun `client should log requests with correct level and format`() = runTest {
        val mockKtorLogger = mockLogger as MockKtorLoggerAdapter
        mockKtorLogger.loggedMessages.clear()

        httpClient.get("/success")

        val requestLog = mockKtorLogger.loggedMessages.firstOrNull { it.contains("REQUEST") }
        val responseLog = mockKtorLogger.loggedMessages.firstOrNull { it.contains("RESPONSE") }
        val bodyLog = mockKtorLogger.loggedMessages.firstOrNull { it.contains("BODY") }

        assertNotNull(requestLog, "Request log message not found")
        assertNotNull(responseLog, "Response log message not found")
        // Since we set LogLevel.ALL, BODY information should be logged
        assertNotNull(bodyLog, "Body log message not found, LogLevel.ALL should be working.")
    }

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
                ktorConfig = testKtorConfig,
                logger = MockKtorLoggerAdapter()
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
                ktorConfig = testKtorConfig.copy(authToken = null),
                logger = MockKtorLoggerAdapter()
            )

            // 5. Trigger: Send a simple request.
            val response = client.get(testUrlPath)

            // 6. Final Assertion: Confirm the response status code.
            assertEquals(HttpStatusCode.OK, response.status)
        }
    @Test
    fun `createHttpClient should set up HttpTimeout correctly`() =
        runTest {
            // 1. Prepare: Create a MockEngine that delays its response
            val mockEngine = MockEngine { request ->
                // Simulate a delay that exceeds the 15-second timeout setting
                delay(20000)
                respond("Success", HttpStatusCode.OK)
            }

            // 2. Execute: Create HttpClient using MockEngine
            val client = KtorClientFactory.createHttpClient(
                engineFactory = mockEngine,
                ktorConfig = testKtorConfig,
                logger = MockKtorLoggerAdapter()
            )

            // 3. Verify: Assert that HttpRequestTimeoutException is thrown
            assertFailsWith<HttpRequestTimeoutException> {
                client.get(testUrlPath)
            }
        }

    @Test
    fun `createHttpClient should use the provided log level`() = runTest {
        // Define a test logger that records all received log messages
        val logMessages = mutableListOf<String>()
        val testLogger = object : KtorLogger {
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
            ktorConfig = testKtorConfig,
            logger = testLogger
        )

        // Trigger request
        client.get(testUrlPath)

        // Final assertion: Confirm that the list of log messages is not empty.
        assertTrue(logMessages.isNotEmpty(), "Logger should have captured log messages.")
        assertTrue(logMessages.first().contains("REQUEST"), "Log should contain request details.")
        // Verify if the logger was called
        assertEquals(2, logMessages.size)
    }
}