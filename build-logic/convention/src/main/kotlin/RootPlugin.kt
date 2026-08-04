import io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.configureXcodeAvailability
import io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.libsVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class RootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        require(target == target.rootProject) { "convention.root applies only to the root project" }

        target.configureXcodeAvailability()
        target.group = io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.ProjectConfig.group
        target.version = io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.ProjectConfig.version

        target.subprojects {
            pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
                pluginManager.apply("convention.ktlint")
                pluginManager.apply("convention.detekt")
            }
        }

        target.tasks.register("qualityCheck") {
            group = "verification"
            description = "Runs ktlintCheck and detekt across all library modules."
            dependsOn(
                target.subprojects.map { sub ->
                    sub.tasks.matching { it.name == "ktlintCheck" || it.name == "detekt" }
                },
            )
        }

        target.tasks.register("jvmLibraryTests") {
            group = "verification"
            description = "Runs JVM unit tests for all library modules."
            dependsOn(
                ":coroutines:jvmTest",
                ":coroutines-compose:jvmTest",
                ":coroutines-viewmodel:jvmTest",
            )
        }

        target.tasks.withType<Test>().configureEach {
            useJUnit()
        }

        // Touch catalog so root configuration fails fast if versions are missing.
        target.libsVersion("kotlin")
    }
}
