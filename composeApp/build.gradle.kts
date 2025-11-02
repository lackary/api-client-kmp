import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.Properties

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
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.compose.hotReload)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.kotlin.native.cocoapods)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }

//        dependencies {
//            debugImplementation(compose.uiTooling)
//            androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//            debugImplementation(libs.androidx.compose.ui.test.mainfest)
//        }
    }

//    listOf(
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "ComposeApp"
        version = "1.0.0" // Or any valid version number
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "18.5" // Specify your iOS deployment target
//        podfile = project.file("../iosApp/Podfile") // Adjust path if needed
        framework {
            baseName = "ComposeApp"
            isStatic = true
        }

        /**
         * The necessary Firebase pods are already managed by the 'libs.mirzemehdi.kmpauthFirebase' library.
         * Do NOT uncomment the pod definitions below.
         *
         * Doing so will cause a "symbol multiply defined" build error after a clean build,
         * because the dependencies would be included twice.
         *
         * However, these pods are still required for the native iOS app to function correctly.
         * You MUST manually add them to your `iosApp/Podfile`.
         *
         * Example for your Podfile:
         * pod 'FirebaseCore', '~> 12.4.0'
         * pod 'FirebaseAuth', '~> 12.4.0'
         * pod 'GoogleSignIn', '~> 9.0.0'
         */
//        pod("FirebaseCore") {
//            version = "~> 12.4.0"
//            extraOpts += listOf("-compiler-option", "-fmodules")
//        }
//        pod("FirebaseAuth") {
//            version = "~> 12.4.0"
//            extraOpts += listOf("-compiler-option", "-fmodules")
//        }
//        pod("GoogleSignIn") {
//            version = "~> 9.0.0"
//            extraOpts += listOf("-compiler-option", "-fmodules")
//        }

    }

    // desktop
    jvm()

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kmp.androidx.lifecycle.viewmodelCompose)
            implementation(libs.kmp.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.compose)
            implementation(libs.mirzemehdi.kmpauthGoogle)
            implementation(libs.mirzemehdi.kmpauthFirebase)
            implementation(libs.mirzemehdi.kmpauthUihelper)
            implementation(libs.touchlab.kermit)
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

buildkonfig {
    packageName = "io.lackstudio.module.kmp.apiclient.app.config"
    val localProps = Properties()
    val localPropsFile = rootProject.file("local.properties")

    if (localPropsFile.exists()) {
        localProps.load(localPropsFile.inputStream())
    }

    val errorMessage = "not found. Please set it as an environment variable or in local.properties."

    val unsplashAccessKey = System.getenv("UNSPLASH_ACCESS_KEY")
        ?: localProps.getProperty("UNSPLASH_ACCESS_KEY")
        ?: error("UNSPLASH_ACCESS_KEY $errorMessage ")

    val unsplashSecretKey = System.getenv("UNSPLASH_SECRET_KEY")
        ?: localProps.getProperty("UNSPLASH_SECRET_KEY")
        ?: error("UNSPLASH_SECRET_KEY  $errorMessage")

    val googleServicesWebClientId = System.getenv("GOOGLE_SERVICES_WEB_CLIENT_ID")
        ?: localProps.getProperty("GOOGLE_SERVICES_WEB_CLIENT_ID")
        ?: error("GOOGLE_SERVICES_WEB_CLIENT_ID $errorMessage")

    defaultConfigs {
        buildConfigField(STRING, "UNSPLASH_ACCESS_KEY", unsplashAccessKey)
        buildConfigField(STRING, "UNSPLASH_SECRET_KEY", unsplashSecretKey)
        buildConfigField(STRING, "GOOGLE_SERVICES_WEB_CLIENT_ID", googleServicesWebClientId)
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

dependencies {
    debugImplementation(compose.uiTooling)
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

afterEvaluate {
    tasks.withType<JavaExec> {
        jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
        jvmArgs("--add-opens", "java.desktop/java.awt.peer=ALL-UNNAMED")

        if (System.getProperty("os.name").contains("Mac")) {
            jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
            jvmArgs("--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED")
            jvmArgs("--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED")
        }
    }
}

// Skip Android Lint tasks (to avoid running Lint like generateDebugAndroidTestLintModel)
tasks.matching { it.name.contains(Regex("lint", RegexOption.IGNORE_CASE))}.configureEach {
    onlyIf {
        if (project.hasProperty("skip.lint")) {
            println("Skipping lint task ($name) because -Pskip.lint=true")
            false
        } else true
    }
}

// Skip JVM / Android tests
tasks.withType<Test>().configureEach {
    onlyIf {
        if (project.hasProperty("skip.tests")) {
            println("Skipping JVM/Android test ($name) because -Pskip.tests=true")
            false
        } else true
    }
}

// Skip Kotlin/Native tests
tasks.withType<KotlinNativeTest>().configureEach {
    onlyIf {
        if (project.hasProperty("skip.native.tests")) {
            println("Skipping Kotlin/Native test ($name) because -Pskip.native.tests=true")
            false
        } else true
    }
}

// Important: Skip Kotlin/Native link tasks (to avoid test linking like linkDebugTestIosSimulatorArm64)
tasks.withType<KotlinNativeLink>().configureEach {
    onlyIf {
        if (project.hasProperty("skip.native.tests")) {
            println("Skipping Kotlin/Native link task ($name) because -Pskip.native.tests=true")
            false
        } else true
    }
}

//// For special test tasks on the Android platform (e.g., connectedAndroidTest/instrumentation tests),
//// they might not be of the `Test` type, but rather `DeviceProviderInstrumentTestTask` or other specific types.
//// To ensure more comprehensive coverage, we need to find them and apply the same logic.
//// Although they are not necessarily all of type Test, it's safer to cover them using `withName`:
//tasks.configureEach {
//    if (name.contains("AndroidTest", ignoreCase = true) ||
//        name.contains("Test", ignoreCase = true)
//    ) {
//        // Apply the skipping logic using onlyIf
//        onlyIf { !project.hasProperty("skip.tests") }
//    }
//}