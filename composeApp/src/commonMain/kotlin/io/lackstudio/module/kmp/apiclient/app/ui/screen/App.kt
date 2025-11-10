package io.lackstudio.module.kmp.apiclient.app.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import com.mmk.kmpauth.uihelper.google.GoogleSignInButtonIconOnly
import dev.gitlive.firebase.auth.FirebaseUser
import io.ktor.client.HttpClient
import io.lackstudio.module.kmp.apiclient.app.di.viewModelModule
import io.lackstudio.module.kmp.apiclient.app.platform.getUnsplashAccessKey
import io.lackstudio.module.kmp.apiclient.app.ui.event.HomeUiEvent
import io.lackstudio.module.kmp.apiclient.app.ui.intent.HomeUiIntent
import io.lackstudio.module.kmp.apiclient.app.ui.viewmodel.AppViewModel
import io.lackstudio.module.kmp.apiclient.app.utils.Environment as AppEnvironment
import io.lackstudio.module.kmp.apiclient.composeapp.generated.resources.Res
import io.lackstudio.module.kmp.apiclient.composeapp.generated.resources.compose_multiplatform
import io.lackstudio.module.kmp.apiclient.core.common.logging.AppLogger
import io.lackstudio.module.kmp.apiclient.core.common.util.appPlatformLogWriter
import io.lackstudio.module.kmp.apiclient.core.di.appLoggerModule
import io.lackstudio.module.kmp.apiclient.core.network.extension.hrefWithHost
import io.lackstudio.module.kmp.apiclient.ui.component.OAuthWebViewBottomSheet
import io.lackstudio.module.kmp.apiclient.core.network.oauth.model.UnsplashAuthorizeRequest
import io.lackstudio.module.kmp.apiclient.unsplash.di.unsplashModule
import io.lackstudio.module.kmp.apiclient.unsplash.utils.Environment as UnsplashEnvironment
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    val appLogger: AppLogger = koinInject()
    val appViewModel: AppViewModel = koinInject()
    val client: HttpClient = koinInject()

    appLogger.info("AppKt", "App MaterialTheme create.")

    Logger.withTag("AppKt").i { "Kermit test" }

//    // MVVM stateFlow
//    val photoUiState by appViewModel.photoUiState.collectAsState()
//
//    // MVVM received data
//    LaunchedEffect(key1 = Unit) {
//        appViewModel.loadPhotos()
//    }

    // MVI stateFLow
    val uiState by appViewModel.uiState.collectAsState()

    // Use a nullable String to store the URL to be displayed. If it's null, the sheet is not shown.
    // This controls the creation and destruction of the OAuthWebViewBottomSheet.
    var authUrlToShow: String? by remember { mutableStateOf(null) }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { showContent = !showContent },
                modifier = Modifier.testTag("display_button")
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                        modifier =  Modifier.testTag("multiplatform_logo")
                    )
                    Text(
                        text = "Show Me Example!!",
                        modifier = Modifier.testTag("display_text")
                    )
                }
            }

            var signedInUserName: String by remember { mutableStateOf("") }
            val onFirebaseResult: (Result<FirebaseUser?>) -> Unit = { result ->
                if (result.isSuccess) {
                    val firebaseUser = result.getOrNull()
                    signedInUserName =
                        firebaseUser?.displayName ?: firebaseUser?.email ?: "Null User"
                    appLogger.info("AppKt", "signedInUserName $signedInUserName")
                } else {
                    signedInUserName = "Null User"
                    appLogger.info("AppKt", "Error Result: ${result.exceptionOrNull()?.message}")
                }

            }

            val coroutineScope = rememberCoroutineScope()
            ButtonSignIn(
                Modifier.testTag("ButtonGoogleSignIn")
            ){
                coroutineScope.launch {  }
            }

            //Google Sign-In with Custom Button and authentication without Firebase
            GoogleButtonUiContainer(onGoogleSignInResult = { googleUser ->
                val idToken = googleUser?.idToken // Send this idToken to your backend to verify
                signedInUserName = googleUser?.displayName ?: "Null User"
            }) {
                Button(onClick = { this.onClick() }) { Text("Google Sign-In(Custom Design)") }
            }

            // ************************** UiHelper Text Buttons *************
            HorizontalDivider(
                Modifier.fillMaxWidth().padding(16.dp),
                DividerDefaults.Thickness,
                DividerDefaults.color
            )
            AuthUiHelperButtonsAndFirebaseAuth(
                modifier = Modifier.width(IntrinsicSize.Max),
                onFirebaseResult = onFirebaseResult
            )

            //************************** UiHelper IconOnly Buttons *************
            HorizontalDivider(
                Modifier.fillMaxWidth().padding(16.dp),
                DividerDefaults.Thickness,
                DividerDefaults.color
            )
            IconOnlyButtonsAndFirebaseAuth(
                modifier = Modifier.fillMaxWidth(),
                onFirebaseResult = onFirebaseResult
            )

            //************************** My Login Buttons *************
            HorizontalDivider(
                Modifier.fillMaxWidth().padding(16.dp),
                DividerDefaults.Thickness,
                DividerDefaults.color
            )

            val authRequest = UnsplashAuthorizeRequest(
                clientId = getUnsplashAccessKey(),
                redirectUri = AppEnvironment.AUTH_REDIRECT_URL,
                responseType = "code",
                scope = "public"
            )
            val authorizeRequestUrl = client.hrefWithHost(
                host = UnsplashEnvironment.HOST_NAME,
                resource = authRequest
            )

            // Button or any action to open the Bottom Sheet
            Button(onClick = { authUrlToShow = authorizeRequestUrl }) {
                Text("Open WebView")
            }

            appLogger.info("Appkt", "authorizeRequestUrl = $authorizeRequestUrl")

            // Show the Bottom Sheet
            authUrlToShow?.let {
                OAuthWebViewBottomSheet(
                    url = authorizeRequestUrl, // Replace with the URL you want to display
                    onDismissRequest = {
                        authUrlToShow = null
                    },
                    onAuthCodeReceived = { code ->
                        appViewModel.processIntent(HomeUiIntent.ExchangeOAuth(code))
                    }
                ) { onExecuteJavascript ->
                    LaunchedEffect(Unit) {
                        appViewModel.eventsFlow.collect { event ->
                            appLogger.debug("Appkt", "event $event")
                            when (event) {
                                is HomeUiEvent.ShowAuthSuccess -> {
                                    appLogger.debug("AppKt", "ShowAuthSuccess")
//                                val jsCall = "displayExchangeSuccess('${event.tokenType}')".trimIndent()
//                                onExecuteJavascript(jsCall)
                                }

                                is HomeUiEvent.ShowAuthError -> {
                                    appLogger.debug("AppKt", "ShowAuthError")
                                    val jsCall = "displayAuthError('${event.message}')".trimIndent()
                                    onExecuteJavascript(jsCall)
                                }

                                is HomeUiEvent.ShowAuthProfile -> {
                                    appLogger.debug("AppKt", "ShowAuthProfile")
                                    val jsCall = "displayUserInfo('${event.profileImageUrl}', '${event.username}')".trimIndent()
                                    onExecuteJavascript(jsCall)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonSignIn(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = "Google Sign In")
    }
}

@Composable
fun AuthUiHelperButtonsAndFirebaseAuth(
    modifier: Modifier = Modifier,
    onFirebaseResult: (Result<FirebaseUser?>) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        //Google Sign-In Button and authentication with Firebase
        GoogleButtonUiContainerFirebase(onResult = onFirebaseResult, linkAccount = false) {
            GoogleSignInButton(
                modifier = Modifier.fillMaxWidth().height(44.dp),
                fontSize = 19.sp
            ) { this.onClick() }
        }
    }
}

@Composable
fun IconOnlyButtonsAndFirebaseAuth(
    modifier: Modifier = Modifier,
    onFirebaseResult: (Result<FirebaseUser?>) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {

        //Google Sign-In IconOnly Button and authentication with Firebase
        GoogleButtonUiContainerFirebase(onResult = onFirebaseResult, linkAccount = false) {
            GoogleSignInButtonIconOnly(onClick = { this.onClick() })
        }
    }
}

/**
 * This is a Composable function designed specifically for Jetpack Compose previews.
 *
 * This function addresses a common error encountered in the preview environment:
 * `java.lang.IllegalStateException: KoinApplication has not been started`。
 *
 * The error occurs because the Android Studio preview engine does not launch the full Android application
 * (the `Application` class), so the Koin dependency injection framework does not have a chance to be initialized.
 *
 * The solution is to use the `KoinApplication` Composable. This Composable can independently start a Koin instance
 * within the preview environment and load the necessary modules for the application. This ensures that when your `App`
 * Composable is rendered, its dependencies (such as ViewModel, Repository, etc.) can be correctly injected by Koin.
 *
 * By using this approach, you can avoid Koin-related errors during UI previews and render the screen correctly.
 */
@Preview()
@Composable
fun AppPreview() {
    KoinApplication(application = {
        // Configure your Koin modules here
        modules(
            listOf(
                appLoggerModule(appPlatformLogWriter()),
                unsplashModule(tokenType = UnsplashEnvironment.AUTH_SCHEME_PUBLIC, token = getUnsplashAccessKey()),
                viewModelModule
            )
        )
    }) {
        // Within this block, the App Composable can now use Koin normally.
        App()
    }
}