package io.lackstudio.module.kmp.apiclient.app.ui.state

import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashOAuthToken
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashUser

data class HomeUiState(
    val photos: AppUiState<List<UnsplashPhoto>> = AppUiState.Default,
    val profile: AppUiState<UnsplashUser> = AppUiState.Default,
    val oAuthToken: AppUiState<UnsplashOAuthToken> = AppUiState.Default,
)
