# Adding a module

Checklist for a new publishable KMP artifact in this monorepo.

1. **Create the project directory** (e.g. `logging/`) with `build.gradle.kts` applying:
   - `org.jetbrains.kotlin.multiplatform`
   - `com.android.kotlin.multiplatform.library`
   - `criollo.kmp-library`
   - Extra plugins only if needed (Compose, serialization, …)

2. **Register in** [`settings.gradle.kts`](../settings.gradle.kts):
   ```kotlin
   include(":logging")
   ```

3. **Extend** [`ProjectConfig`](../build-logic/convention/src/main/kotlin/io/github/jdbenitez94/criollo/kmp/foundation/buildlogic/ProjectConfig.kt):
   - `Artifacts.*` constant
   - `Namespaces.*` Android namespace
   - POM description branch in `CriolloPublishingCommon.kt`

4. **Teach** [`CriolloKmpLibraryConventionPlugin`](../build-logic/convention/src/main/kotlin/CriolloKmpLibraryConventionPlugin.kt) the new `:path` → namespace mapping.

5. **Wire verification** in [`RootPlugin`](../build-logic/convention/src/main/kotlin/RootPlugin.kt) (`jvmLibraryTests` dependsOn).

6. **JS/Wasm:** if the module uses browser targets, consider copying `webpack.config.d/resolve-fallback.js` — see [js-wasm.md](js-wasm.md).

7. **Document** the artifact in the root README and bump `CHANGELOG.md`.

8. **Consumers** (e.g. Saveable): add Maven coordinates to their version catalog; keep `includeBuild` dependencySubstitution entries in sync for local dogfood.
