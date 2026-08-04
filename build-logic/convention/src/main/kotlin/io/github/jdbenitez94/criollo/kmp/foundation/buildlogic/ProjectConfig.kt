package io.github.jdbenitez94.criollo.kmp.foundation.buildlogic

object ProjectConfig {
    const val version = "0.1.0"
    const val group = "io.github.jdbenitez94.criollo.kmp.foundation"

    object Artifacts {
        const val coroutines = "coroutines"
        const val coroutinesCompose = "coroutines-compose"
        const val coroutinesViewmodel = "coroutines-viewmodel"
    }

    object Publishing {
        const val repoUrl = "https://github.com/jdbenitez94/criollo-kmp-foundation"
        const val licenseName = "MIT License"
        const val licenseUrl = "https://opensource.org/licenses/MIT"
        const val developerId = "jdbenitez94"
        const val developerName = "Joaquin Daniel Benitez"
        const val mavenCentralReleaseUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
        const val mavenCentralSnapshotUrl = "https://s01.oss.sonatype.org/service/local/snapshots/"
    }

    object Android {
        const val compileSdk = 37
        const val minSdk = 24
    }

    object Namespaces {
        const val coroutines = "$group.coroutines"
        const val coroutinesCompose = "$group.coroutines.compose"
        const val coroutinesViewmodel = "$group.coroutines.viewmodel"
    }
}
