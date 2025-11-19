package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.core.common.error.CommonException
import io.lackstudio.module.kmp.apiclient.core.network.error.RemoteException
import io.lackstudio.module.kmp.apiclient.core.network.error.StructuredApiException
import io.lackstudio.module.kmp.apiclient.core.domain.usecase.UseCaseResult
import io.lackstudio.module.kmp.apiclient.core.persistence.LocalException
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelLogicTest {

    private lateinit var viewModel: TestBaseViewModel
    private val testDispatcher = StandardTestDispatcher()

    // --- Mock Object for Testing Structured Exceptions ---
    // We define a mock structured exception here so we don't need to depend on the Unsplash module.
    private class MockStructuredException(
        override val structuredMessage: String?,
        override val originalApiException: RemoteException.Api
    ) : Exception(), StructuredApiException

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TestBaseViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // --- Flow State Tests ---

    @Test
    fun `handleUseCaseCall success should update flow to Success`() = runTest {
        val testFlow = MutableStateFlow<AppUiState<String>>(AppUiState.Loading)
        val useCase: suspend () -> UseCaseResult<String> = { UseCaseResult.Success("Test Data") }

        viewModel.callHandleUseCaseCall(testFlow, useCase)
        advanceUntilIdle()

        val currentState = testFlow.value
        assertTrue(currentState is AppUiState.Success)
        assertEquals("Test Data", currentState.data)
    }

    @Test
    fun `handleUseCaseCall error should update flow to Error`() = runTest {
        val testFlow = MutableStateFlow<AppUiState<String>>(AppUiState.Loading)
        // Test using a common network error
        val useCase: suspend () -> UseCaseResult<String> = { UseCaseResult.Error(RemoteException.Network.Unknown()) }

        viewModel.callHandleUseCaseCall(testFlow, useCase)
        advanceUntilIdle()

        val currentState = testFlow.value
        assertTrue(currentState is AppUiState.Error)
        // This must match the text in BaseViewModel
        assertEquals("Network unavailable.", currentState.message)
    }

    // --- Error Message Logic Tests ---

    // Test StructuredApiException (Highest Priority)
    @Test
    fun `getAppErrorMessage returns structuredMessage when StructuredApiException provides one`() {
        val customMsg = "Custom parsed error message"
        val originalError = RemoteException.Api.BadRequest(message = "Original HTTP message")
        val exception = MockStructuredException(structuredMessage = customMsg, originalApiException = originalError)

        val message = viewModel.getAppErrorMessageForTest(exception)

        // Expect to get the parsed customMsg, not the original message
        assertEquals(customMsg, message)
    }

    // Test StructuredApiException Fallback (Secondary Priority)
    @Test
    fun `getAppErrorMessage falls back to original message when StructuredApiException message is null`() {
        val originalMsg = "Original HTTP message"
        val originalError = RemoteException.Api.BadRequest(message = originalMsg)
        // Simulate parsing failure, structuredMessage is null
        val exception = MockStructuredException(structuredMessage = null, originalApiException = originalError)

        val message = viewModel.getAppErrorMessageForTest(exception)

        // Expect to fall back to originalMsg
        assertEquals(originalMsg, message)
    }

    // Test Network Errors
    @Test
    fun `getAppErrorMessage returns correct message for Network Timeout`() {
        val exception = RemoteException.Network.Timeout()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Connection timeout. Please check your internet.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Network Unknown`() {
        val exception = RemoteException.Network.Unknown()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Network unavailable.", message)
    }

    // Test API Errors (RemoteException.Api)
    @Test
    fun `getAppErrorMessage returns custom message for Api BadRequest if present`() {
        val customMsg = "Email format is invalid"
        val exception = RemoteException.Api.BadRequest(message = customMsg)
        val message = viewModel.getAppErrorMessageForTest(exception)
        // BadRequest logic is: apiException.message ?: "Invalid request."
        assertEquals(customMsg, message)
    }

    @Test
    fun `getAppErrorMessage returns default message for Api BadRequest if message is null`() {
        val exception = RemoteException.Api.BadRequest(message = null)
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Invalid request.", message)
    }

    @Test
    fun `getAppErrorMessage returns hardcoded message for Api Unauthorized`() {
        // Even if message is passed, BaseViewModel currently hardcodes the return to "Session expired..."
        val exception = RemoteException.Api.Unauthorized(message = "Token expired")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Session expired. Please login again.", message)
    }

    @Test
    fun `getAppErrorMessage returns hardcoded message for Api NotFound`() {
        val exception = RemoteException.Api.NotFound(message = "User not found")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Resource not found.", message)
    }

    @Test
    fun `getAppErrorMessage returns hardcoded message for Api Server Error`() {
        val exception = RemoteException.Api.Server(code = 500)
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Server error. Please try again later.", message)
    }

    // 5. Test Local Errors
    @Test
    fun `getAppErrorMessage returns correct message for ResourceNotFound`() {
        val exception = LocalException.Persistence.ResourceNotFound("user")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Resource not found.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for DatabaseError`() {
        val exception = LocalException.Persistence.DatabaseError()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Database error.", message)
    }

    // 6. Test Common Errors
    @Test
    fun `getAppErrorMessage returns correct message for InvalidDataFormat`() {
        val exception = CommonException.Parsing.InvalidDataFormat()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Data format error.", message)
    }

    @Test
    fun `getAppErrorMessage returns default message for unknown exceptions`() {
        // Test Exception not in the list
        val exception = Exception("Some random crash")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("An unexpected error occurred.", message)
    }
}
