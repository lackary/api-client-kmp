package io.lackstudio.module.kmp.apiclient.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initializeKoin(allModules: List<Module>): KoinApplication {
    return startKoin {
        modules(allModules)
    }
}