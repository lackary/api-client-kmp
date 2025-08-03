plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.devtool.ksp) apply false
}

subprojects {
    //trick: for the same plugin versions in all sub-modules
    group = "com.lackstudio.module.kmp.apiclient"
    version = "0.0.1"
}
