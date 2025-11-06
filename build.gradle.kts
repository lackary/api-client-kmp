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

// Inherit from DefaultTask or a more suitable abstract class
// Use @get:Input to mark these properties as task inputs
abstract class SetBuildVersionTask : DefaultTask() {

    // Use Property<String> to hold the version and build numbers, ensuring configuration cache compatibility
    @get:Input
    abstract val newVersion: Property<String>

    @get:Input
    abstract val buildNumber: Property<String>

    @get:OutputFile
    val versionFile = project.layout.projectDirectory.file("VERSION.txt")

    // Use @TaskAction to annotate the task's execution logic
    @TaskAction
    fun execute() {
        // Use .get() to retrieve the actual String value.
        val version = newVersion.get()
        val build = buildNumber.get()

        logger.lifecycle(">> newVersion = $version, buildNumber = $build")

        // Combine the version number, format is "<SemVer>.<BuildNumber>"
        // For example: 1.2.3-beta.456

        val combinedVersion = if (version.contains("-")) {
            "$version.$build"
        } else {
            "$version-beta.$build"
        }

        // Write the combined version number to a file
        logger.lifecycle(">> write version to ${versionFile.asFile}")
        versionFile.asFile.writeText(combinedVersion)

        logger.lifecycle(">> Successfully set VERSION.txt to: $combinedVersion")
    }
}

// Register and configure the new task
tasks.register<SetBuildVersionTask>("setBuildVersion") {
    // Use providers.gradleProperty to safely get -P parameters
    // If the property doesn't exist, it returns an unset Property.
    val pNewVersion = project.providers.gradleProperty("newVersion")
    val pBuildNumber = project.providers.gradleProperty("buildNumber")

    // Use .set() to configure task properties.
    newVersion.set(pNewVersion)
    buildNumber.set(pBuildNumber)

    // Configure group and description
    group = "versioning"
    description = "Writes the project version and build number to VERSION.txt."
}
