import io.github.jdbenitez94.criollo.kmp.foundation.buildlogic.libsVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class KtLintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("org.jlleitschuh.gradle.ktlint")

        target.extensions.configure<KtlintExtension> {
            android.set(false)
            ignoreFailures.set(false)
            enableExperimentalRules.set(false)
            version.set(target.rootProject.libsVersion("ktlint-engine"))
            filter {
                exclude { element -> element.file.path.contains("build") }
                exclude { element -> element.file.path.contains("generated") }
            }
        }
    }
}
