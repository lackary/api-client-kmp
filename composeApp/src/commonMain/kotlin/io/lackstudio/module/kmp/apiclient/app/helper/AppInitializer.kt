package io.lackstudio.module.kmp.apiclient.app.helper

import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

object AppInitializer {
    fun onApplicationStart(webClientId: String) {
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = webClientId))
    }
}
