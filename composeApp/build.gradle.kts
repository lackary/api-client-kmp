import org.gradle.internal.jvm.Jvm
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.compose.hotReload)
    alias(libs.plugins.gms.google.services)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }

        dependencies {
            debugImplementation(compose.uiTooling)
            androidTestImplementation(libs.androidx.compose.ui.test.junit4)
            debugImplementation(libs.androidx.compose.ui.test.mainfest)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    // desktop
    jvm()

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kmp.androidx.lifecycle.viewmodelCompose)
            implementation(libs.kmp.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.compose)
            implementation(libs.mirzemehdi.kmpauthGoogle)
            implementation(libs.mirzemehdi.kmpauthFirebase)
            implementation(libs.mirzemehdi.kmpauthUihelper)
            implementation(projects.modules.unsplashApiClient)
            implementation(projects.modules.core)
            implementation(projects.modules.ui)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activityCompose)
        }
        androidUnitTest.dependencies {  }
        androidInstrumentedTest.dependencies {  }

        iosMain.dependencies {  }
        iosTest.dependencies {  }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(compose.desktop.uiTestJUnit4)
        }
    }
}

android {
    namespace = "io.lackstudio.module.kmp.apiclient.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.lackstudio.module.kmp.apiclient.app"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

compose.desktop {
    application {
        mainClass = "io.lackstudio.module.kmp.apiclient.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.lackstudio.module.kmp.apiclient.app"
            packageVersion = "1.0.0"
        }
    }
}

tasks.withType<Test>().configureEach {
    // Check if the 'skip.tests' property is passed from the command line
    // If this property exists, onlyIf returns false, and the tests will be skipped (SKIPPED)
    onlyIf { !project.hasProperty("skip.tests") }
}

//// For special test tasks on the Android platform (connectedAndroidTest/instrumentation tests)
//// They might not be of the `Test` type, but rather `DeviceProviderInstrumentTestTask` or other specific types.
//// To ensure more comprehensive coverage, we need to find them and apply the same logic.
//// Although they are not necessarily all of the Test type, it's safer to cover them with withName:
//tasks.configureEach {
//    if (name.contains("AndroidTest", ignoreCase = true) ||
//        name.contains("Test", ignoreCase = true)
//    ) {
//        // Use onlyIf to apply the skip logic
//        onlyIf { !project.hasProperty("skip.tests") }
//    }
//}