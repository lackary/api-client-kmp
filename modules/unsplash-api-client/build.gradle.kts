import com.android.build.gradle.internal.testFixtures.getTestFixturesCapabilityForProject
import com.android.build.gradle.internal.testFixtures.testFixturesFeatureName
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.buildkonfig.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.devtool.ksp)
    alias(libs.plugins.buildkonfig)
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
            baseName = "UnsplashApiClient"
            xcf.add(this)
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(project(":modules:core"))
            implementation(kotlin("stdlib-common"))
            implementation(libs.koin.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.touchlab.kermit)
        }

        jvmMain.dependencies {

        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.ktor.client.cio)
            implementation(libs.koin.test.junit4)
        }

        androidMain.dependencies {

        }
        androidUnitTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {

        }
        iosTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.ktor.client.darwin)
        }
    }
}

buildkonfig {
    packageName = "io.lackstudio.module.kmp.apiclient.unsplash.config"
    val unsplashApiKey = project.properties["UNSPLASH_API_KEY"] as String

    defaultConfigs {
        buildConfigField(STRING, "UNSPLASH_API_KEY", unsplashApiKey)
    }
}

android {
    namespace = "io.lackstudio.module.kmp.apiclient.unsplash"
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
            artifactId = "unsplash-api-client"
            from(components["kotlin"])
        }
    }
    repositories {
        mavenLocal()
    }
}
