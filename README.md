<p align="center">
  <a href="https://github.com/jdbenitez94/criollo-kmp-foundation/actions/workflows/ci.yml"><img src="https://github.com/jdbenitez94/criollo-kmp-foundation/actions/workflows/ci.yml/badge.svg?branch=main&event=push" alt="CI"/></a>
  <a href="https://kotlinlang.org/docs/multiplatform.html"><img src="https://img.shields.io/badge/Kotlin_Multiplatform-Platform_Support-%237F52FF?logo=kotlin&logoColor=white" alt="Kotlin Multiplatform"/></a>
  <a href="https://central.sonatype.com/namespace/io.github.jdbenitez94.criollo.kmp.foundation"><img src="https://img.shields.io/maven-central/v/io.github.jdbenitez94.criollo.kmp.foundation/coroutines.svg" alt="Maven Central"/></a>
  <a href="https://codecov.io/gh/jdbenitez94/criollo-kmp-foundation"><img src="https://img.shields.io/badge/codecov-pending-lightgrey" alt="codecov"/></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License"/></a>
</p>

# Criollo KMP Foundation

Reusable Kotlin Multiplatform / Compose Multiplatform infrastructure libraries for the Criollo architecture approach.

**Group:** `io.github.jdbenitez94.criollo.kmp.foundation`  
**Version:** `0.1.0`

## Artifacts

| Artifact | Coordinates | Role |
|----------|-------------|------|
| `coroutines` | `…foundation:coroutines` | Keyed `TaskScope` registry |
| `coroutines-compose` | `…foundation:coroutines-compose` | `rememberTaskScope()` |
| `coroutines-viewmodel` | `…foundation:coroutines-viewmodel` | `ViewModel.taskScope()` |

```kotlin
dependencies {
    implementation("io.github.jdbenitez94.criollo.kmp.foundation:coroutines:0.1.0")
    implementation("io.github.jdbenitez94.criollo.kmp.foundation:coroutines-viewmodel:0.1.0")
    // optional Compose UI without ViewModel:
    implementation("io.github.jdbenitez94.criollo.kmp.foundation:coroutines-compose:0.1.0")
}
```

Packages live under `io.github.jdbenitez94.criollo.kmp.foundation.coroutines` (+ `.compose` / `.viewmodel`).

## Docs

- [Managed tasks (`TaskScope`)](docs/managed-tasks.md)
- [JS / Wasm webpack notes](docs/js-wasm.md)
- [Adding a module](docs/adding-a-module.md)
- [Publishing](docs/publishing.md)

## Local build

```bash
./gradlew qualityCheck jvmLibraryTests
./gradlew publishToMavenLocal
```

Requires JDK 21 and Android SDK (`local.properties` with `sdk.dir=…`).

## License

MIT — see [LICENSE](LICENSE).
