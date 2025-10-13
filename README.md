# ApiClientKmp

Kotlin Multiplatform (KMP) library project for API clients, using Ktor for networking and Koin for dependency injection.

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

## KMPAuth Integration Guide (iOS - Google Sign-In)

This guide explains how to set up Google Sign-In on iOS using KMPAuth.

### 1. Prerequisites

Before you begin, follow the KMPAuth documentation to set up your Firebase project and download the necessary configuration files:

- `GoogleService-Info.plist` (for the iOS app)

Place `GoogleService-Info.plist` in the `iosApp/` directory of your project.

### 2. Create Secret Configuration Files

To manage your API keys and client IDs securely, create two configuration files in the `iosApp/Configuration/` directory.

**`Secrets-Debug.xcconfig`**

```xcconfig
GOOGLE_CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID"
REVERSE_CLIENT_ID = "YOUR_REVERSE_CLIENT_ID"
```

**`Secrets-Release.xcconfig`**

```xcconfig
GOOGLE_CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID"
REVERSE_CLIENT_ID = "YOUR_REVERSE_CLIENT_ID"
```

> **Note:** Replace the placeholder values with your actual credentials. You can find `GOOGLE_CLIENT_ID` and `REVERSE_CLIENT_ID` inside your `GoogleService-Info.plist` file. `GOOGLE_SERVER_CLIENT_ID` is the web client ID, used for backend authentication.

### 3. Configure Xcode Project

Follow these steps to link the `.xcconfig` files and make the values available in your `Info.plist`.

#### Link xcconfig Files to Build Configurations

1. In Xcode, select your project file (`iosApp`) in the Project Navigator to open the project settings.
2. In the central editor pane, ensure you select the **PROJECT** (`iosApp`), not a target from the `TARGETS` list.
3. Navigate to the **Info** tab.
4. Under the **Configurations** section, you will see `Debug` and `Release`.
5. For the `Debug` configuration row, choose `Secrets-Debug` from the dropdown menu under the "Based on Configuration File" column.
6. For the `Release` configuration row, choose `Secrets-Release`. Targets will inherit these settings.

#### Read Values in Info.plist

1. Select the `iosApp` **target**.
2. Go to the **Info** tab.
3. Expand **URL Types**.
4. Click the `+` button to add a new URL Type.
5. In the **URL Schemes** field for the new type, enter `$(REVERSE_CLIENT_ID)`. The `$` notation tells Xcode to substitute the value from your `.xcconfig` file at build time.

This setup ensures that your sensitive keys are kept out of source control and are correctly configured for both debug and release builds.

---

## Kotlin CocoaPods Dependency Manager Setup Guide

This guide outlines the steps to set up a Kotlin Multiplatform project with CocoaPods for iOS development.

### 1. Add Kotlin CocoaPods Dependency and Sync Gradle

First, add the Kotlin CocoaPods plugin dependency to your `build.gradle.kts` file and sync the project with your Gradle files.

```kotlin
kotlin{
   iosArm64()
   iosSimulatorArm64()

   cocoapods {
      name = "ComposeApp"
      version = "1.0.0" // Or any valid version number
      summary = "Some description for the Shared Module"
      homepage = "Link to the Shared Module homepage"
      ios.deploymentTarget = "18.5" // Specify your iOS deployment target
      podfile = project.file("../iosApp/Podfile") // Adjust path if needed
      framework {
         baseName = "ComposeApp"
         isStatic = true
      }
   }
}
```

### 2. Ensure a Dummy Framework Exists

You need to have a dummy `*.framework` file that includes an empty header. This is a workaround to make CocoaPods recognize the Kotlin framework before it's actually built.

### 3. Initialize Pods

If you have CocoaPods installed, run `pod init` in your iOS project's root directory. This will create a `Podfile` where you can list your dependencies.

```sh
# Uncomment the next line to define a global platform for your project
platform :ios, '18.5'

target 'iosApp' do
  # Comment the next line if you don't want to use dynamic frameworks
  use_frameworks!

  # Pods for iosApp
  pod 'ComposeApp', :path => '../composeApp'
  pod '${dependency_you_need}'

end
```

### 4. Install Pods

Run the following command to install the necessary libraries:

```sh
pod install --repo-update --clean-install
```

### 5. Open the Xcode Workspace

Open the newly created `*.xcworkspace` file. This workspace contains your original project along with the installed Pods.

### 6. Generate the Real Framework

Finally, you can generate the real framework by building the project.
