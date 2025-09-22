package io.lackstudio.module.kmp.apiclient.app.di

import io.lackstudio.module.kmp.apiclient.app.ui.viewmodel.AppViewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { AppViewModel(get(), get()) }
}