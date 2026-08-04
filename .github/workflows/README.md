# GitHub Actions — criollo-kmp-foundation

CI for the Criollo KMP Foundation monorepo.

## Workflow: `ci.yml`

| Job     | Purpose |
|---------|---------|
| `build` | `./gradlew qualityCheck jvmLibraryTests` (Detekt, KtLint, JVM unit tests; configuration cache enforced) |

Separate workflows:

- [`codeql.yml`](codeql.yml) — Kotlin/Java static security analysis (PR/push to `main`, weekly schedule, manual).
- [`release.yml`](release.yml) — on tag `v*`: version check, quality + tests, Maven Central publish, GitHub Release notes.

Triggers: push and pull_request to `main` (docs-only / markdown-only changes are skipped via `paths-ignore`).

## Action pinning

Third-party actions use immutable commit SHAs with a version comment for humans, e.g. `actions/checkout@<sha> # v4`. Dependabot (`github-actions` ecosystem) opens weekly PRs to refresh pins.

## Secrets (release)

| Secret | Description |
|--------|-------------|
| `OSSRH_USERNAME` / `OSSRH_PASSWORD` | Maven Central (OSSRH) credentials |
| `SIGNING_KEY_ID` / `SIGNING_KEY` / `SIGNING_PASSWORD` | In-memory PGP signing for publications |

Same secrets pattern as **saveable** under `io.github.jdbenitez94.*`.

## Local parity

```bash
./gradlew qualityCheck jvmLibraryTests
./gradlew publishToMavenLocal
```
