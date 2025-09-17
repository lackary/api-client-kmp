package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import app.cash.turbine.test
import io.lackstudio.module.kmp.apiclient.core.common.error.AppException
import io.lackstudio.module.kmp.apiclient.core.domain.result.UseCaseResult
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

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelLogicTest {

    private lateinit var viewModel: TestBaseViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TestBaseViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `handleUseCaseCall success should update flow to Success`() = runTest {
        val testFlow = MutableStateFlow<AppUiState<String>>(AppUiState.Loading)
        val useCase: suspend () -> UseCaseResult<String> = { UseCaseResult.Success("Test Data") }

        viewModel.callHandleUseCaseCall(testFlow, useCase)
        advanceUntilIdle()

        val currentState = testFlow.value
        assertEquals(true, currentState is AppUiState.Success)
        assertEquals("Test Data", (currentState as AppUiState.Success).data)
    }

    @Test
    fun `handleUseCaseCall error should update flow to Error`() = runTest {
        val testFlow = MutableStateFlow<AppUiState<String>>(AppUiState.Loading)
        val useCase: suspend () -> UseCaseResult<String> = { UseCaseResult.Error(AppException.Network.Unknown()) }

        viewModel.callHandleUseCaseCall(testFlow, useCase)
        advanceUntilIdle()

        val currentState = testFlow.value
        assertEquals(true, currentState is AppUiState.Error)
        assertEquals("Network connection error, please try again later.", (currentState as AppUiState.Error).message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Network Timeout`() {
        val exception = AppException.Network.Timeout()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Connection timeout, please check your network connection.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Network Unknown`() {
        val exception = AppException.Network.Unknown()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Network connection error, please try again later.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Api 401`() {
        val exception = AppException.Api.Unauthorized(message = "Authentication failed, please log in again.")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Authentication failed, please log in again.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Api 404`() {
        val exception = AppException.Api.NotFound(message = "Resource not found.")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Resource not found.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Api 500`() {
        val exception = AppException.Api.Server(code = 500, message = "Server error, please try again later.")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Server error, please try again later.", message)
    }

    @Test
    fun `getAppErrorMessage returns default message for other Api codes`() {
        val exception = AppException.Api.BadRequest(message = "Bad Request.")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Bad Request.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for ResourceNotFound`() {
        val exception = AppException.Business.ResourceNotFound("user")
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("The user you're looking for was not found.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for InvalidData`() {
        val exception = AppException.Business.InvalidData()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Invalid data.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Serialization`() {
        val exception = AppException.Business.Serialization()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("Data serialization error.", message)
    }

    @Test
    fun `getAppErrorMessage returns correct message for Unknown error`() {
        val exception = AppException.Unknown()
        val message = viewModel.getAppErrorMessageForTest(exception)
        assertEquals("An unknown error occurred.", message)
    }
}