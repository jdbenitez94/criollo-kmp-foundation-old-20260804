package io.github.jdbenitez94.criollo.kmp.foundation.buildlogic

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension

fun Project.configureCriolloPublishing(artifactIdOverride: String? = null) {
    pluginManager.apply("maven-publish")
    pluginManager.apply("signing")

    val versionString = ProjectConfig.version
    version = versionString
    group = ProjectConfig.group

    val baseArtifactId = artifactIdOverride ?: name

    plugins.withId("java") {
        extensions.configure<JavaPluginExtension> {
            withSourcesJar()
            withJavadocJar()
        }
    }

    fun configurePublicationArtifactIds() {
        extensions.configure<PublishingExtension> {
            publications.withType<MavenPublication>().configureEach {
                groupId = ProjectConfig.group
                artifactId = when (name) {
                    "kotlinMultiplatform", "mavenJava" -> baseArtifactId
                    else -> "$baseArtifactId-$name"
                }
            }
        }
    }

    extensions.configure<PublishingExtension> {
        publications.withType<MavenPublication>().configureEach {
            pom {
                name.set(baseArtifactId)
                description.set(pomDescriptionFor(baseArtifactId))
                url.set(ProjectConfig.Publishing.repoUrl)
                licenses {
                    license {
                        name.set(ProjectConfig.Publishing.licenseName)
                        url.set(ProjectConfig.Publishing.licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(ProjectConfig.Publishing.developerId)
                        name.set(ProjectConfig.Publishing.developerName)
                    }
                }
                scm {
                    url.set(ProjectConfig.Publishing.repoUrl)
                    connection.set("scm:git:git://${ProjectConfig.Publishing.repoUrl.removePrefix("https://")}.git")
                    developerConnection.set(
                        "scm:git:ssh://${ProjectConfig.Publishing.repoUrl.removePrefix("https://")}.git",
                    )
                }
            }
        }

        repositories {
            maven {
                name = "mavenCentral"
                val releaseUrl = ProjectConfig.Publishing.mavenCentralReleaseUrl
                val snapshotUrl = ProjectConfig.Publishing.mavenCentralSnapshotUrl
                url = uri(if (versionString.endsWith("SNAPSHOT")) snapshotUrl else releaseUrl)

                credentials {
                    username = providers.gradleProperty("mavenCentralUsername").orNull
                    password = providers.gradleProperty("mavenCentralPassword").orNull
                }
            }
        }
    }

    configurePublicationArtifactIds()
    afterEvaluate { configurePublicationArtifactIds() }

    extensions.configure<SigningExtension> {
        val key = providers.gradleProperty("signingInMemoryKey").orNull
        val password = providers.gradleProperty("signingInMemoryPassword").orNull
        val keyId = providers.gradleProperty("signingInMemoryKeyId").orNull

        if (!key.isNullOrBlank() && !password.isNullOrBlank()) {
            useInMemoryPgpKeys(keyId, key, password)
        }

        val isRequired = providers.gradleProperty("signing.required")
            .map { it.toBoolean() }
            .orElse(false)
        setRequired(isRequired)

        this@configureCriolloPublishing.extensions.configure<PublishingExtension> {
            sign(publications)
        }
    }
}

private fun pomDescriptionFor(artifactId: String): String = when (artifactId) {
    ProjectConfig.Artifacts.coroutines ->
        "Keyed TaskScope coroutine registry for Kotlin Multiplatform."
    ProjectConfig.Artifacts.coroutinesCompose ->
        "Compose rememberTaskScope() adapter for Criollo TaskScope."
    ProjectConfig.Artifacts.coroutinesViewmodel ->
        "ViewModel taskScope() property delegate for Criollo TaskScope."
    else -> "Criollo KMP Foundation library module."
}
