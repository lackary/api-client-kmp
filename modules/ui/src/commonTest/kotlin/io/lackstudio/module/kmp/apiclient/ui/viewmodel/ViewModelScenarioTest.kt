package io.lackstudio.module.kmp.apiclient.ui.viewmodel

import app.cash.turbine.test
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelScenarioTest {
    private lateinit var viewModel: TestViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TestViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchData success should emit Loading then Success state`() = runTest(testDispatcher) {
        viewModel.uiState.test {
            // Initial state
            assertEquals(AppUiState.Idle, awaitItem())

            // Trigger action
            viewModel.fetchData(success = true)

            // Expect Loading state first
            assertEquals(AppUiState.Loading, awaitItem())

            val successState = awaitItem()
            assertTrue(successState is AppUiState.Success)
            assertEquals("Success Data", successState.data)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `fetchData error should emit Loading then Error state`() = runTest(testDispatcher) {
        viewModel.uiState.test {
            // Initial state
            assertEquals(AppUiState.Idle, awaitItem())

            // Trigger action
            viewModel.fetchData(success = false)

            // Expect Loading state first
            assertEquals(AppUiState.Loading, awaitItem())

            val errorState = awaitItem()
            assertTrue(errorState is AppUiState.Error)

            // --- Key Modification ---
            // Originally: "Network connection error, please try again later."
            // Now BaseViewModel returns "Network unavailable." for RemoteException.Network.Unknown
            assertEquals("Network unavailable.", errorState.message)

            cancelAndConsumeRemainingEvents()
        }
    }

    // MVI Success Scenario Test
    @Test
    fun `fetchDataMvi success should update state to Success`() = runTest(testDispatcher) {
        viewModel.uiState.test {
            // Initial state verification
            assertEquals(AppUiState.Idle, awaitItem())

            // Trigger MVI action
            viewModel.fetchDataMvi(success = true)

            // Expect Loading state first
            assertEquals(AppUiState.Loading, awaitItem())

            val successState = awaitItem()
            assertTrue(successState is AppUiState.Success)
            assertEquals("MVI Success Data", successState.data)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `fetchDataMvi error should update state to Error with parsed message`() = runTest(testDispatcher) {
        viewModel.uiState.test {
            // Initial state verification
            assertEquals(AppUiState.Idle, awaitItem())

            // Trigger MVI action (simulate failure)
            viewModel.fetchDataMvi(success = false)

            // Expect Loading state first
            assertEquals(AppUiState.Loading, awaitItem())

            val errorState = awaitItem()
            assertTrue(errorState is AppUiState.Error)

            // Verify if the error message is parsed by BaseViewModel
            // (fetchDataMvi internally throws RemoteException.Network.Unknown, should be parsed as "Network unavailable.")
            assertEquals("Network unavailable.", errorState.message)

            cancelAndConsumeRemainingEvents()
        }
    }
}
