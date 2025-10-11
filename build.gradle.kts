// Create a readable version number variable in the root directory
// The default value is "0.0.1", the actual version number will be set by the CI/CD process
val projectVersion: String by lazy {
    val versionFile = File("VERSION.txt")
    if (versionFile.exists()) {
        versionFile.readText().trim()
    } else {
        "0.0.1"
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.hotReload) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.devtool.ksp) apply false
    alias(libs.plugins.gms.google.services) apply false
    alias(libs.plugins.kotlin.native.cocoapods) apply false
}

subprojects {
    //trick: for the same plugin versions in all sub-modules
    group = "io.lackstudio.module.kmp.apiclient"
    version = projectVersion
}

// This Gradle task is responsible for writing the version number to the VERSION.txt file
tasks.register("setBuildVersion") {
    doLast {
        // Get the new version number and build number from the command line parameters
        val newVersion = project.properties["newVersion"] as String
        val buildNumber = project.properties["buildNumber"] as String

        // Combine the version number, format is "<SemVer>.<BuildNumber>"
        // For example: 1.2.3-beta.456
        val combinedVersion = if (newVersion.contains("-")) {
            "$newVersion.$buildNumber"
        } else {
            "$newVersion-beta.$buildNumber"
        }

        // Write the combined version number to a file for subsequent build scripts to use
        val versionFile = file("VERSION.txt")
        versionFile.writeText(combinedVersion)
    }
}
