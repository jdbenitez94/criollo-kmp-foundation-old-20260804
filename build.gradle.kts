plugins {
    alias(libs.plugins.convention.root)
    alias(libs.plugins.com.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.org.jetbrains.compose) apply false
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose) apply false
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.org.jetbrains.dokka) apply false
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint) apply false
    alias(libs.plugins.io.gitlab.arturbosch.detekt) apply false
}
