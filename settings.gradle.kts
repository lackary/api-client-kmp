enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("com.codingfeline.buildkonfig") version "0.17.1" apply false
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "api-client-kmp"

include(":modules:core")
include(":modules:ui")
include(":modules:unsplash-api-client")