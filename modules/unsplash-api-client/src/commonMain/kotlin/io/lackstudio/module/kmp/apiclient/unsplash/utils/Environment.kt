package io.lackstudio.module.kmp.apiclient.unsplash.utils

object Environment {
    const val BASE_API_URL = "https://api.unsplash.com"
    const val API_PHOTOS = "/photos"
    const val API_COLLECTIONS = "/collections"
    const val API_USERS = "/users"
    const val API_SEARCH = "/search"
    const val API_TOPICS = "/topics"
    fun getAuthToken(): String = "Client-ID ${getUnsplashApiKey()}"
//    val AUTH_TOKEN by lazy { "Client-ID ${getUnsplashApiKey()}"}
}

expect fun getUnsplashApiKey(): String