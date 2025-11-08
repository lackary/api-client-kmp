package io.lackstudio.module.kmp.apiclient.app.di

import io.lackstudio.module.kmp.apiclient.app.ui.viewmodel.AppViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory { AppViewModel(
        getPhotosUseCase = get(),
        exchangeOAuthUseCase = get(),
        getMeUseCase = get(),
        appLogger = get(),
        accessTokenProvider = get())
    }
}