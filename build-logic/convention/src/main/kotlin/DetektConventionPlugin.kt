import io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("io.gitlab.arturbosch.detekt")

        target.extensions.configure<DetektExtension> {
            buildUponDefaultConfig = true
            allRules = false
            config.setFrom(target.rootProject.file("config/detekt/detekt.yml"))
            source.setFrom(
                "src/commonMain/kotlin",
                "src/androidMain/kotlin",
                "src/jvmMain/kotlin",
                "src/iosMain/kotlin",
                "src/jsMain/kotlin",
                "src/wasmJsMain/kotlin",
                "src/jvmTest/kotlin",
            )
        }

        val formatting = target.rootProject.libs
            .findLibrary("io-gitlab-arturbosch-detekt-formatting")
            .orElseThrow { IllegalArgumentException("Missing detekt-formatting library") }
        target.dependencies.add("detektPlugins", formatting)
    }
}
