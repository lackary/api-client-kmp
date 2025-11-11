import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.resources.Resources
import io.ktor.resources.Resource
import kotlin.test.Test
import kotlin.test.assertEquals
import io.lackstudio.module.kmp.apiclient.core.network.extension.hrefWithHost
import kotlin.test.assertFailsWith

@Resource("/api/data")
class MockResource(val id: Int, val sort: String? = null)

class HttpClientExtensionTest {

    // Mock the expected "path and query parameters" returned by HttpClient.href(resource)
    private val expectedPathAndQuery = "/api/data?id=123&sort=name"
    private val testResource = MockResource(id = 123, sort = "name")
    private val testHost = "api.example.com"

    @Test
    fun `hrefWithHost should correctly construct HTTPS URL with path and query`() {
        // 1. Create an HttpClient using MockEngine.
        // Since we are only testing the logic of hrefWithHost and not actually sending a network request,
        // the MockEngine's handler can be empty or used to ensure it is not called unexpectedly.
        val mockEngine = MockEngine { request ->
            // This test should not actually send a request, so if it gets called, it means the test configuration is wrong.
            error("This mock engine handler should not be called.")
        }

        // 2. Create an HttpClient instance and install the Resources plugin.
        val mockHttpClient = HttpClient(mockEngine) {
            install(Resources) {
                // Normally Ktor would handle Resources here, but in unit tests we need to mock it.
            }
        }

        // 3. Since hrefWithHost internally calls this@hrefWithHost.href(resource),
        //    and the Ktor Resources plugin implements the `href` function,
        //    in unit tests, we need to ensure that the behavior of `this@hrefWithHost.href(resource)`
        //    is as expected.
        //    (Note: The internal logic of the Ktor Resources plugin is complex, and a simple MockEngine may not be able to
        //     fully simulate the behavior of `href()`. However, since your function operates on the result of `href`,
        //     **the simplest unit testing method** is to test your logic directly:
        //     combining `URLBuilder` and `takeFrom`.)

        // Since we cannot directly mock the extension function `HttpClient.href(resource)`,
        // a more robust method is to extract the behavior of `href` into an abstraction that can be mocked/controlled.
        // But if your goal is to test that your function behaves correctly when `href` outputs a specific result,
        // you can indirectly verify it by testing whether its output string matches the expectation.

        // --- Corrected test method: indirect output verification ---

        // Note: To allow the hrefWithHost function to compile and run in unit tests,
        // your test project needs to have a dependency on the Ktor Resources plugin.

        val actualUrl = mockHttpClient.hrefWithHost(testHost, testResource)

        val expectedUrl = "https://$testHost$expectedPathAndQuery"

        assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `hrefWithHost should correctly handle resource with no query parameters`() {
        val noQueryResource = MockResource(id = 456)
        val expectedNoQueryPath = "/api/data?id=456"

        val mockEngine = MockEngine { error("Should not be called") }
        val mockHttpClient = HttpClient(mockEngine) {
            install(Resources)
        }

        val actualUrl = mockHttpClient.hrefWithHost(testHost, noQueryResource)
        val expectedUrl = "https://$testHost$expectedNoQueryPath"

        assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `hrefWithHost should ignore protocol if present in host string`() {
        // Although the function explicitly sets HTTPS, this is to check the behavior of URLBuilder
        val hostWithProtocol = "http://api.example.com"
        val testResource = MockResource(id = 123)
        val expectedPathAndQuery = "/api/data?id=123"

        // Setup Mock/HttpClient ... (as above)
        val mockHttpClient = HttpClient(MockEngine { error("Should not be called") }) {
            install(Resources)
        }

        val actualUrl = mockHttpClient.hrefWithHost(hostWithProtocol, testResource)

        // The expected result should be the HTTPS protocol, and the HostName should be correct
        val expectedUrl = "https://api.example.com$expectedPathAndQuery"

        assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `hrefWithHost should throw IllegalArgumentException if host is blank`() {
        val hostBlank = ""
        val testResource = MockResource(id = 123)

        val mockHttpClient = HttpClient(MockEngine { error("Should not be called") }) {
            install(Resources)
        }

        // Use assertFailsWith to assert that it throws an IllegalArgumentException
        assertFailsWith<IllegalArgumentException> {
            mockHttpClient.hrefWithHost(hostBlank, testResource)
        }
    }
}
