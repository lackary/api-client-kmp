package io.lackstudio.module.kmp.apiclient.app.ui.state

import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Me as UnsplashMe
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.OAuthToken as UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.Photo as UnsplashPhoto

data class HomeUiState(
    val photos: AppUiState<List<UnsplashPhoto>> = AppUiState.Default,
    val profile: AppUiState<UnsplashMe> = AppUiState.Default,
    val oAuthToken: AppUiState<UnsplashOAuthToken> = AppUiState.Default,
)
