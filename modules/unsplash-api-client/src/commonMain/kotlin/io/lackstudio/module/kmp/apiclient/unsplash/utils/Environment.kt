package io.lackstudio.module.kmp.apiclient.unsplash.utils

import io.ktor.http.auth.AuthScheme

object Environment {
    // url
    const val HOST_NAME = "unsplash.com"
    const val BASE_API_URL = "https://api.unsplash.com"
    const val API_PHOTOS = "/photos"
    const val API_COLLECTIONS = "/collections"
    const val API_USERS = "/users"
    const val API_SEARCH = "/search"
    const val API_TOPICS = "/topics"
    const val API_ME = "/me"
    const val OAUTH_AUTHORIZE = "https://unsplash.com/oauth/authorize"
    const val OAUTH_TOKEN = "https://unsplash.com/oauth/token"

    // parameter
    const val PARAM_CLIENT_ID = "client_id"
    const val PARAM_CLIENT_SECRET = "client_secret"
    const val PARAM_REDIRECT_URI = "redirect_uri"
    const val PARAM_CODE = "code"
    const val PARAM_GRANT_TYPE = "grant_type"
    const val AUTH_SCHEME_PUBLIC = "Client-ID"

    val OAUTH_PATH_SEGMENTS = listOf("oauth", "authorize")
}