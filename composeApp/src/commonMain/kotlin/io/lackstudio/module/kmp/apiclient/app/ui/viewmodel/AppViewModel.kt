package io.lackstudio.module.kmp.apiclient.app.ui.viewmodel

import androidx.lifecycle.viewModelScope
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashSecretKey
import io.lackstudio.module.kmp.apiclient.app.ui.event.HomeUiEvent
import io.lackstudio.module.kmp.apiclient.app.ui.intent.HomeUiIntent
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.ui.viewmodel.BaseViewModel
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthCode as UnsplashOAuthCode
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken as UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo as UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.ExchangeOAuthUseCase
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import io.lackstudio.module.kmp.apiclient.app.ui.state.HomeUiState
import io.lackstudio.module.kmp.apiclient.app.utils.Environment
import io.lackstudio.module.kmp.apiclient.core.network.oauth.AccessTokenProvider
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetMeUseCase
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotosParams
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val exchangeOAuthUseCase: ExchangeOAuthUseCase,
    private val getMeUseCase: GetMeUseCase,
    private val appLogger: AppLogger,
    private val accessTokenProvider: AccessTokenProvider
) : BaseViewModel() {

    private val TAG = AppViewModel::class::simpleName

    // MVVM uses multiple StateFlows to expose UI state
    private val _photoUiState =
        MutableStateFlow<AppUiState<List<UnsplashPhoto>>>(AppUiState.Idle)
    val photoUiState: StateFlow<AppUiState<List<UnsplashPhoto>>> = _photoUiState.asStateFlow()

    private val _oauthUiState =
        MutableStateFlow<AppUiState<UnsplashOAuthToken>>(AppUiState.Idle)
    val oauthUiState: StateFlow<AppUiState<UnsplashOAuthToken>> = _oauthUiState.asStateFlow()

    fun loadPhotos() {
        val params = GetPhotosParams(page = 1, perPage = 10)
        handleUseCaseCall(
            flow = _photoUiState,
            useCase = { getPhotosUseCase(params) },
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
            useCase = { exchangeOAuthUseCase(unsplashOAuthCode) }
        )
    }

    // MVI Core: Single StateFlow
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HomeUiEvent>(
        replay = 0,              // Do not replay historical events, keep it as a one-time event
        extraBufferCapacity = 1, // Set a buffer to prevent losing events during the gap when LaunchedEffect starts
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventsFlow: Flow<HomeUiEvent> = _eventFlow.asSharedFlow()

    private fun sendEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    // MVI Core: Function to receive intents
    fun processIntent(intent: HomeUiIntent) {
        when (intent) {
            is HomeUiIntent.LoadPhotos -> loadPhotosIntent()
            is HomeUiIntent.ExchangeOAuth -> exchangeOAuthIntent(intent.code)
            is HomeUiIntent.GetProfile ->  getProfileIntent()
        }
    }

    private fun loadPhotosIntent() {
        val params = GetPhotosParams(page = 1, perPage = 10)

        handleUseCaseCall(
            onLoading = {
                _uiState.update { state -> state.copy(photos = AppUiState.Loading) }
            },
            useCase = { getPhotosUseCase(params)},
            onSuccess = { data ->
                appLogger.debug("AppViewModel", "on onSuccess data $data")
                _uiState.update { state -> state.copy(photos = AppUiState.Success(data)) }
            },
            onError = { errorMessage ->
                _uiState.update { state -> state.copy(oAuthToken = AppUiState.Error(errorMessage)) }

            }
        )
    }

    private fun getProfileIntent() {
        handleUseCaseCall(
            onLoading = {
                _uiState.update { state -> state.copy(profile = AppUiState.Loading) }
            },
            useCase = {
                getMeUseCase(input = Unit)
            },
            onSuccess = { data ->
                appLogger.debug("AppViewModel","Profile: $data")
                _uiState.update { state -> state.copy(profile = AppUiState.Success(data)) }
                sendEvent(
                    HomeUiEvent.ShowAuthProfile(
                        profileImageUrl = data.profileImage.large,
                        username = data.username
                    )
                )
            },
            onError = { errorMessage ->
                appLogger.error("AppViewModel", "onError errorMessage: $errorMessage")
                _uiState.update { state -> state.copy(profile = AppUiState.Error(errorMessage)) }
            }
        )
    }

    private fun exchangeOAuthIntent(code: String) {
        val unsplashOAuthCode = UnsplashOAuthCode(
            clientId = getUnsplashAccessKey(),
            clientSecret = getUnsplashSecretKey(),
            redirectUri = Environment.AUTH_REDIRECT_URL,
            code = "X"
        )
        // *** Use MVI-specific abstract function ***
        handleUseCaseCall(
            // onLoading: Set loading state
            onLoading = {
                _uiState.update { state -> state.copy(oAuthToken = AppUiState.Loading) }
            },
            // useCase: Execute UseCase
            useCase = {
                exchangeOAuthUseCase(unsplashOAuthCode)
            },
            // onSuccess: On success, update the accessToken property in MviUiState
            onSuccess = { data ->
                appLogger.info("AppViewModel", "onSuccess data: $data")

                accessTokenProvider.setOAuthToken(newType = data.tokenType, newValue = data.accessToken)
                _uiState.update { state -> state.copy(oAuthToken = AppUiState.Success(data)) }
                sendEvent(HomeUiEvent.ShowAuthSuccess(data.tokenType))

                getProfileIntent()
            },
            // onError: On failure, update the accessToken property in MviUiState with an error message
            onError = { errorMessage ->
                appLogger.error("AppViewModel", "onError errorMessage: $errorMessage")
                _uiState.update { state -> state.copy(oAuthToken = AppUiState.Error(errorMessage)) }
                sendEvent(HomeUiEvent.ShowAuthError("Token exchange failed: $errorMessage"))
            }
        )
    }
}
