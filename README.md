# ApiClientKmp

Kotlin Multiplatform (KMP) library project for API clients, using Ktor for networking and Koin for dependency injection.

## Structure

```tree
shared/
├── build.gradle.kts
├── core/
│     └── build.gradle.kts
├── network/
│     └── build.gradle.kts
├── common-models/
│     └── build.gradle.kts
│
├── news-api-client/
│     ├── build.gradle.kts
│     └── src/commonMain/
│     ├── data/
│     │     ├── api/
│     │     ├── repository/
│     │     └── model/
│     ├── domain/
│     │     ├── repository/
│     │     ├── model/
│     │     └── usecase/
│     └── di/
│     └── NewsApiClientModule.kt
│
├── unsplash/
│     ├── build.gradle.kts
│     └── src/commonMain/
│           ├── data/
│           │     ├── api/
│           │     ├── repository/
│           │     └── model/
│           ├── domain/
│           │     ├── repository/
│           │     ├── model/
│           │     └── usecase/
│           └── di/
│           └── UnsplashApiClientModule.kt
```

Each API client module is independently usable as a dependency in different apps.

## Kotlin Multiplatform Library Usage Guide

This project is a Kotlin Multiplatform Library. Below are instructions for importing this library into another app project.

---

### Method 1: Use includeBuild() to Import Source Directly

#### Steps (includeBuild method)

1. **Add to your app project's `settings.gradle.kts`:**

    ```kotlin
    includeBuild("/absolute/path/to/this/project")
    ```

    Example:

    ```kotlin
    includeBuild("../testing-kmmp")
    ```

2. **Add to your app project's `build.gradle.kts` dependencies:**

    ```kotlin
    implementation("com.lackstudio.module.kmp.testing:testing")
    ```

3. **Sync Gradle**

> Advantage: Any changes in the library source will be immediately reflected in the app without republishing.

---

### Method 2: Publish to Local Maven (mavenLocal)

#### Steps (publish to mavenLocal)

1. **Configure your library module's `build.gradle.kts`:**

    ```kotlin
    plugins {
        id("org.jetbrains.kotlin.multiplatform")
        id("com.android.library")
        id("maven-publish")
    }

    group = "com.lackstudio.module.kmp.testing"
    version = "0.0.1"

    kotlin {
        androidTarget()
        // ... other targets ...
    }

    android {
        namespace = "com.lackstudio.module.kmp.testing"
        compileSdk = 34
        defaultConfig { minSdk = 21 }
    }

    publishing {
        repositories {
            mavenLocal()
        }
    }
    ```

2. **In the library project directory, run:**

    ```sh
    ./gradlew publishToMavenLocal
    ```

    This will publish the library to `~/.m2/repository`.

3. **Add `mavenLocal` to your app project's `settings.gradle.kts`:**

    ```kotlin
    dependencyResolutionManagement {
        repositories {
            mavenLocal()
            google()
            mavenCentral()
        }
    }
    ```

4. **Add to your app project's `build.gradle.kts` dependencies and `libs.versions.toml`:**

    In `libs.versions.toml`:

    ```toml
    [libraries]
    testing = { group = "com.lackstudio.module.kmp.testing", name = "testing", version = "0.0.1" }
    ```

    In your app's `build.gradle.kts`:

    ```kotlin
    implementation(libs.testing)
    ```

    Or, without version catalog:

    ```kotlin
    implementation("com.lackstudio.module.kmp.testing:testing:0.0.1")
    ```

5. **Sync Gradle**

> Advantage: Allows sharing the library across projects/teams without exposing source code.

---

### Notes

- If you change the library source, you must re-run `publishToMavenLocal` to update the local maven repository.
- Adjust groupId, artifactId, and version according to your actual project settings.
