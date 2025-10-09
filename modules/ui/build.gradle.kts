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
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = "UiApiClient"
                xcf.add(this)
                isStatic = true
            }
        }

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(libs.kmp.androidx.lifecycle.viewmodelCompose)
            implementation(projects.modules.core)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine.test)
        }

        jvmMain.dependencies {
        }
        jvmTest.dependencies {
        }

        androidMain.dependencies {
        }

        androidUnitTest.dependencies {
        }

        iosMain.dependencies {
        }
        iosTest.dependencies {
        }
    }
}

android {
    namespace = "io.lackstudio.module.kmp.apiclient.ui"
    compileSdk = 35
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
            artifactId = "ui"
            from(components["kotlin"])
        }
    }
    repositories {
        mavenLocal()
    }
}