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
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
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
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging)
            // Ktor
            api(libs.ktor.serialization.kotlinx.json)

            // Kotlinx Serialization
            api(libs.kotlinx.serialization.core)
            api(libs.kotlinx.serialization.json)

            // Koin Core (Common Main)
            api(libs.koin.core) // Use an API to make dependencies visible to downstream modules.

            // Kermit Logger
            api(libs.touchlab.kermit)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.core)
        }

        jvmMain.dependencies {
            api(libs.ktor.client.cio)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            api(libs.ktor.client.cio)
            implementation(libs.koin.test.junit4)
        }

        androidMain.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.android)
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
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.lackstudio.module.kmp.apiclient.core"
    compileSdk = 35
    defaultConfig {
        minSdk = 30
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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