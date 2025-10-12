package io.lackstudio.module.kmp.apiclient.app.ui.viewmodel

import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.ui.state.AppUiState
import io.lackstudio.module.kmp.apiclient.ui.viewmodel.BaseViewModel
import io.lackstudio.module.kmp.apiclient.unsplash.domain.model.UnsplashPhoto
import io.lackstudio.module.kmp.apiclient.unsplash.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val appLogger: AppLogger
) : BaseViewModel() {
    // 使用 StateFlow 暴露 UI 狀態
    private val _uiState =
        MutableStateFlow<AppUiState<List<UnsplashPhoto>>>(AppUiState.Default)
    val uiState: StateFlow<AppUiState<List<UnsplashPhoto>>> = _uiState.asStateFlow()

    fun loadPhotos() {
        handleUseCaseCall(
            flow = _uiState,
            useCase = {getPhotosUseCase.invoke(page=1, perPage=10)},
        )
    }
}