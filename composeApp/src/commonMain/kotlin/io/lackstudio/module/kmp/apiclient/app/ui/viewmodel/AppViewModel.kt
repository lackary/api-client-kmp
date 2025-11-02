package io.lackstudio.module.kmp.apiclient.app.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashSecretKey
import io.lackstudio.module.kmp.apiclient.app.ui.intent.HomeUiIntent
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.ui.viewmodel.BaseViewModel
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.ExchangeOAuthUseCase
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import io.lackstudio.module.kmp.apiclient.app.ui.state.HomeUiState
import io.lackstudio.module.kmp.apiclient.app.utils.Environment
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import kotlinx.coroutines.flow.update

class AppViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val exchangeOAuthUseCase: ExchangeOAuthUseCase,
    private val appLogger: AppLogger,
    private val accessTokenProvider: AccessTokenProvider
) : BaseViewModel() {

    // MVVM uses multiple StateFlows to expose UI state
    private val _photoUiState =
        MutableStateFlow<AppUiState<List<UnsplashPhoto>>>(AppUiState.Default)
    val photoUiState: StateFlow<AppUiState<List<UnsplashPhoto>>> = _photoUiState.asStateFlow()

    private val _oauthUiState =
        MutableStateFlow<AppUiState<UnsplashOAuthToken>>(AppUiState.Default)
    val oauthUiState: StateFlow<AppUiState<UnsplashOAuthToken>> = _oauthUiState.asStateFlow()

    fun loadPhotos() {
        handleUseCaseCall(
            flow = _photoUiState,
            useCase = { getPhotosUseCase.invoke(page=1, perPage=10) },
        )
    }

    fun exchangeOAuth(code: String) {
        // get Access Key, and Secret Key and redirect uri
        val unsplashOAuthCode = UnsplashOAuthCode(
            clientId = getUnsplashAccessKey(),
            clientSecret = getUnsplashSecretKey(),
            redirectUri = Environment.AUTH_REDIRECT_URL,
            code = code
        )
        handleUseCaseCall(
            flow = _oauthUiState,
            useCase = { exchangeOAuthUseCase.invoke(unsplashOAuthCode) }
        )
    }

    // MVI Core: Single StateFlow
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // MVI Core: Function to receive intents
    fun processIntent(intent: HomeUiIntent) {
        when (intent) {
            HomeUiIntent.LoadPhotos -> loadPhotosIntent()
            is HomeUiIntent.ExchangeOAuth -> exchangeOAuthIntent(intent.code)
        }
    }

    private fun loadPhotosIntent() {
        handleUseCaseCall(
            flow = _photoUiState,
            useCase = { getPhotosUseCase.invoke(page=1, perPage=10) },
        )
    }

    private fun exchangeOAuthIntent(code: String) {
        val unsplashOAuthCode = UnsplashOAuthCode(
            clientId = getUnsplashAccessKey(),
            clientSecret = getUnsplashSecretKey(),
            redirectUri = Environment.AUTH_REDIRECT_URL,
            code = code
        )
        // *** Use MVI-specific abstract function ***
        handleUseCaseCall(
            // onLoading: Set loading state
            onLoading = {
                _uiState.update { it.copy(oAuthToken = AppUiState.Loading) }
            },
            // useCase: Execute UseCase
            useCase = {
                exchangeOAuthUseCase.invoke(unsplashOAuthCode)
            },
            // onSuccess: On success, update the accessToken property in MviUiState
            onSuccess = { data ->
                appLogger.info("AppViewModel", "onSuccess data: $data")
                accessTokenProvider.setOAuthToken(newType = data.tokenType, newValue = data.accessToken)
                _uiState.update { it.copy(oAuthToken = AppUiState.Success(data)) }
            },
            // onError: On failure, update the accessToken property in MviUiState with an error message
            onError = { errorMessage ->
                appLogger.debug("AppViewModel", "onError errorMessage: $errorMessage")
                _uiState.update { it.copy(oAuthToken = AppUiState.Error(errorMessage)) }
            }
        )
    }
}