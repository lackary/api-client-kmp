package io.lackstudio.module.kmp.apiclient.unsplash.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSURLAuthenticationMethodServerTrust
import platform.Foundation.NSURLCredential
import platform.Foundation.NSURLSessionAuthChallengeUseCredential
import platform.Foundation.credentialForTrust
import platform.Foundation.serverTrust
import kotlinx.cinterop.*
import platform.Foundation.NSURLSessionAuthChallengeCancelAuthenticationChallenge
import platform.Foundation.NSURLSessionAuthChallengePerformDefaultHandling

@OptIn(ExperimentalForeignApi::class)
actual fun provideHttpClientEngineTest(): HttpClientEngine = Darwin.create {
    configureSession {

        // Allow cellular network (mobile data) usage.
        setAllowsCellularAccess(true)

        // Do not use the URL credential storage to avoid storing unnecessary credentials during testing.
        // Passing null disables this feature.
        setURLCredentialStorage(null)

        // Set additional HTTP headers, here configured as an empty Map.
        setHTTPAdditionalHeaders(emptyMap<Any?, Any?>())

        handleChallenge { session, task, challenge, completionHandler ->
            if (challenge.protectionSpace.authenticationMethod == NSURLAuthenticationMethodServerTrust) {
                val trust = challenge.protectionSpace.serverTrust
                if (trust != null) {
                    println("🚫 警告: 正在跳過 SSL/TLS 憑證驗證。")
                    val credential = NSURLCredential.credentialForTrust(trust)
                    completionHandler(NSURLSessionAuthChallengeUseCredential, credential)
                } else {
                    println("Error: serverTrust is unexpectedly null.")
                    completionHandler(
                        NSURLSessionAuthChallengeCancelAuthenticationChallenge,
                        null
                    )
                }
            } else {
                completionHandler(
                    NSURLSessionAuthChallengePerformDefaultHandling,
                    null
                )
            }
        }
    }
}
