package io.lackstudio.module.kmp.apiclient.app.ui.event

sealed class HomeUiEvent {
    // For notifying the View to perform a one-time error display on the WebView/UI
    data class ShowAuthError(val message: String) : HomeUiEvent()

    // For notifying the View to perform a one-time success notification on the WebView/UI
    data class ShowAuthSuccess(val tokenType: String) : HomeUiEvent()

    // For notifying the view to perform the profile info on the WebView/UI
    // after successfully fetching the Profile
    data class ShowAuthProfile(val profileImageUrl: String, val username: String) : HomeUiEvent()
}
