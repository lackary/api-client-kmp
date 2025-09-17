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
            assertEquals(AppUiState.Loading, awaitItem())
            viewModel.fetchData(success = true)
            val successState = awaitItem()
            assertTrue(successState is AppUiState.Success)
            assertEquals("Success Data", successState.data)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `fetchData error should emit Loading then Error state`() = runTest(testDispatcher) {
        viewModel.uiState.test {
            assertEquals(AppUiState.Loading, awaitItem())
            viewModel.fetchData(success = false)
            val errorState = awaitItem()
            assertTrue(errorState is AppUiState.Error)
            assertEquals("Network connection error, please try again later.", errorState.message)
            cancelAndConsumeRemainingEvents()
        }
    }
}