# Publishing

Publishing targets Maven Central (OSSRH) with in-memory PGP signing.

## Coordinates

- Group: `io.github.jdbenitez94.criollo.kmp.foundation`
- Version: `ProjectConfig.version` in build-logic (must match git tag `vX.Y.Z`)

## Local smoke

```bash
./gradlew publishToMavenLocal
```

Artifacts land under `~/.m2/repository/io/github/jdbenitez94/criollo/kmp/foundation/`,
including:

- `bom` (Java Platform / dependencyManagement for all modules)
- library modules (`coroutines`, `coroutines-compose`, `coroutines-viewmodel` — Gradle `:coroutines`, `:coroutines:compose`, `:coroutines:viewmodel`)
- `project-conventions` Gradle plugin (+ plugin-marker POM for id
  `io.github.jdbenitez94.criollo.kmp.foundation.project-conventions`)

## Release (CI)

1. Ensure GitHub secrets: `OSSRH_USERNAME`, `OSSRH_PASSWORD`, `SIGNING_KEY_ID`, `SIGNING_KEY`, `SIGNING_PASSWORD` (namespace `io.github.jdbenitez94`).
2. Bump `ProjectConfig.version` and update `CHANGELOG.md`.
3. Tag and push: `git tag v0.1.0 && git push origin v0.1.0`.
4. [`.github/workflows/release.yml`](../.github/workflows/release.yml) runs quality + tests, then `publishAllPublicationsToMavenCentralRepository`.

## Gradle properties (local publish with signing)

```
mavenCentralUsername=…
mavenCentralPassword=…
signingInMemoryKeyId=…
signingInMemoryKey=…
signingInMemoryPassword=…
signing.required=true
```

Omit signing props for unsigned `publishToMavenLocal` during development.
