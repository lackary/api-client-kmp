package io.lackstudio.module.kmp.apiclient.app.ui.state

import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.unsplash.data.model.response.UnsplashApiErrorResponse
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto

data class HomeUiState(
    val photos: AppUiState<List<UnsplashPhoto>> = AppUiState.Default,
    val oAuthToken: AppUiState<UnsplashOAuthToken> = AppUiState.Default,
)
