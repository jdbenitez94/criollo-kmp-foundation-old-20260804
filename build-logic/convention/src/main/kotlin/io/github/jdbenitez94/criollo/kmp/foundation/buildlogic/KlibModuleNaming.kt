package io.github.jdbenitez94.criollo.kmp.foundation.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinNativeCompilerOptions

object KlibModuleNaming {
    val jsWasmTargets = setOf("js", "wasmJs")

    fun configureUniqueModuleName(project: Project) {
        val baseModuleName = project.uniqueKlibModuleName()
        project.extensions.configure<KotlinMultiplatformExtension> {
            targets.configureEach {
                if (name in jsWasmTargets) return@configureEach
                compilations.configureEach {
                    val compilationName = name
                    val uniqueModuleName = compilationModuleName(baseModuleName, compilationName)
                    compileTaskProvider.configure {
                        if (name.contains("Metadata", ignoreCase = true)) return@configure
                        compilerOptions {
                            when (this) {
                                is KotlinNativeCompilerOptions -> moduleName.set(uniqueModuleName)
                                is KotlinJvmCompilerOptions -> moduleName.set(uniqueModuleName)
                                else -> {
                                    val args = freeCompilerArgs.get().filterNot { it.startsWith("-module-name=") }
                                    freeCompilerArgs.set(args + "-module-name=$uniqueModuleName")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    internal fun compilationModuleName(baseModuleName: String, compilationName: String): String =
        if (compilationName == "main") {
            baseModuleName
        } else {
            "${baseModuleName}_$compilationName"
        }

    fun Project.uniqueKlibModuleName(): String =
        "criollo_" + path.removePrefix(":").replace(':', '_').replace('-', '_')
}
