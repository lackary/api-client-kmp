package io.lackstudio.module.kmp.apiclient.core.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURLCredential
import platform.Foundation.NSURLSessionAuthChallengeCancelAuthenticationChallenge
import platform.Foundation.NSURLSessionAuthChallengePerformDefaultHandling
import platform.Foundation.NSURLSessionAuthChallengeUseCredential
import platform.Foundation.credentialForTrust
import platform.Foundation.serverTrust
import platform.Security.SecTrustEvaluateWithError
import platform.UIKit.UIDevice

actual fun provideHttpClientEngine(): HttpClientEngine = Darwin.create()

@OptIn(ExperimentalForeignApi::class)
actual fun applyPlatformConfig(config: HttpClientConfig<*>) {

//    if (!UIDevice.currentDevice.model.contains("Simulator")) return
//    val darwinConfig = config as? HttpClientConfig<DarwinClientEngineConfig> ?: return
//    darwinConfig.engine {
//        handleChallenge { _,_, challenge, completionHandler ->
//            val isSimulator = UIDevice.currentDevice.model.contains("Simulator")
//
//            if (isSimulator) {
//                // 模擬器：直接交給系統預設處理（避免 -1202 錯誤）
//                completionHandler(NSURLSessionAuthChallengePerformDefaultHandling, null)
//            } else {
//                // 實機：針對特定 Host 信任憑證
//                if (challenge.protectionSpace.host == "api.unsplash.com") {
//                    val trust = challenge.protectionSpace.serverTrust
//                    if (trust != null) {
//                        val credential = NSURLCredential.credentialForTrust(trust)
//                        completionHandler(NSURLSessionAuthChallengeUseCredential, credential)
//                    } else {
//                        completionHandler(NSURLSessionAuthChallengePerformDefaultHandling, null)
//                    }
//                } else {
//                    completionHandler(NSURLSessionAuthChallengePerformDefaultHandling, null)
//                }
//            }
//        }
//    }

}