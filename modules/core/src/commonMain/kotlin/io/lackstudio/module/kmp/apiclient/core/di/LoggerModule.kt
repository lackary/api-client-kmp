package io.lackstudio.module.kmp.apiclient.core.di

import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLoggerImpl
import io.lackstudio.module.kmp.apiclient.core.common.logging.KtorKermitLoggerAdapter
import io.lackstudio.module.kmp.apiclient.core.common.logging.LogConfiguration
import io.lackstudio.module.kmp.apiclient.core.common.util.getKermitLogger
import org.koin.core.qualifier.named
import org.koin.dsl.module
import io.ktor.client.plugins.logging.Logger as KtorLogger

const val APP_LOGGER_TAG = "app_logger_tag"
const val KTOR_LOGGER_TAG = "ktor_logger_tag"


val appLoggerModule = module {
    single(named(APP_LOGGER_TAG)) {
        getKermitLogger(LogConfiguration.appTag)
    }
    single<AppLogger> { AppLoggerImpl(get(named(APP_LOGGER_TAG))) }
}

val ktorLoggerModule = module {
    single(named(KTOR_LOGGER_TAG)) {
        getKermitLogger(LogConfiguration.ktorTag)
    }
    single<KtorLogger> { KtorKermitLoggerAdapter(get(named(KTOR_LOGGER_TAG))) }

}