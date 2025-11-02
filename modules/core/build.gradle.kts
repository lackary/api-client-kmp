import org.jetbrains.kotlin.gradle.dsl.JvmTarget

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    id("maven-publish")
}

kotlin {
    androidTarget {
        // This is necessary to publish the Android library variant.
        publishLibraryVariants("release")
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }

        val xcf = XCFramework()
        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = "CoreApiClient"
                xcf.add(this)
                isStatic = true
            }
        }

    jvm()

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(kotlin("stdlib-common"))
            // Ktor Client Core
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            // Ktor
            implementation(libs.ktor.serialization.kotlinx.json)

            // Kotlinx
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datatime)

            // Koin Core (Common Main)
            api(libs.koin.core) // Use an API to make dependencies visible to downstream modules.

            // Kermit Logger
            api(libs.touchlab.kermit)
//            api(libs.touchlab.kermit.ktor.logger)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
            implementation(libs.koin.core)
            implementation(libs.touchlab.kermit)
            implementation(libs.touchlab.kermit.test)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
            // Kermit Logger
            api(libs.touchlab.kermit)
            implementation(libs.org.slf4j.api)
            implementation(libs.ch.qos.logback.classic)

        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.cio)
            implementation(libs.koin.test.junit4)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            // Kermit Logger
            api(libs.touchlab.kermit)
        }

        androidUnitTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            api(libs.touchlab.kermit)
        }
        iosTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "io.lackstudio.module.kmp.apiclient.core"
    compileSdk = 36
    defaultConfig {
        minSdk = 30
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

}

publishing {
    publications {
        // withType<MavenPublication>().all {
        //     artifactId = "testing" // or your artifact name
        // }
        create<MavenPublication>("maven") {
            artifactId = "core"
            from(components["kotlin"])
        }
    }
    repositories {
        mavenLocal()
    }
}