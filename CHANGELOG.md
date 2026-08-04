# Changelog

## 1.0.0 (2026-08-04)


### Features

* **bom:** add Bill of Materials for foundation artifacts ([1c4b732](https://github.com/jdbenitez94/criollo-kmp-foundation/commit/1c4b732911724a1790e4331393cf44ba74a949ab))
* **coroutines:** add compose rememberTaskScope adapter submodule ([038fca4](https://github.com/jdbenitez94/criollo-kmp-foundation/commit/038fca4bb402cfbe711a5b5755c0205ff6ad7b99))
* **coroutines:** add keyed TaskScope coroutine registry ([5a203fe](https://github.com/jdbenitez94/criollo-kmp-foundation/commit/5a203fec753dcc75eb3fd8ff500ede6aa65b3283))
* **coroutines:** add ViewModel taskScope adapter submodule ([f459a75](https://github.com/jdbenitez94/criollo-kmp-foundation/commit/f459a75393e04d4a4c6ccd9721c6b058fcbc2b2b))
* **project-conventions:** add Gradle plugin to sync shared style configs ([c30d37f](https://github.com/jdbenitez94/criollo-kmp-foundation/commit/c30d37f0def5aaf1ee91aabe80fe134bb848c356))

## 0.1.0

- Initial release: `bom`, `coroutines`, `coroutines-compose`, `coroutines-viewmodel`.
- Gradle layout nests adapters under `:coroutines` (`:coroutines:compose`, `:coroutines:viewmodel`); Maven coordinates stay hyphenated.
- `project-conventions` Gradle plugin: sync/check shared `.editorconfig` and Detekt v1/v2 configs.
