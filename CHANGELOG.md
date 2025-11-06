## [0.7.1](https://github.com/lackary/api-client-kmp/compare/0.7.0...0.7.1) (2025-11-06)


### Bug Fixes

* **ci:** resolve iphonesimulator wasn't found in GitHub Action ([95d1e98](https://github.com/lackary/api-client-kmp/commit/95d1e98744447345d536bea030d82097e0b5ac9d))
* **ci:** resolve null cast error in setBuildVersion task ([d34ebe6](https://github.com/lackary/api-client-kmp/commit/d34ebe6d593d93a9113e4435e579e7db8ea58e0a))

# [0.7.0](https://github.com/lackary/api-client-kmp/compare/0.6.0...0.7.0) (2025-11-05)


### Bug Fixes

* **build:** add token type and token int composeApp test ([9232b46](https://github.com/lackary/api-client-kmp/commit/9232b46df6e6b495c95d938d6520d5b6ec8c0060))
* **build:** skip all composeApp test ([2fbe896](https://github.com/lackary/api-client-kmp/commit/2fbe896abfadbd6bfab9f81244a01eb165bec7fd))
* **build:** solve the dependency error by Firebase. ([92ced6e](https://github.com/lackary/api-client-kmp/commit/92ced6e84ac8a6029bc01d6c0d890396baecf1be))
* **build:** solve the Lint error by android SDK version ([dbc9332](https://github.com/lackary/api-client-kmp/commit/dbc9332d8872c55e07286d7d60d17277ad598d10))
* **ci:** build failed by iOS Simulator version ([5de3ed9](https://github.com/lackary/api-client-kmp/commit/5de3ed96bc5f46e829bb3e08f3fd49300ca45e83))
* **ci:** build failed by serialization error ([704d42f](https://github.com/lackary/api-client-kmp/commit/704d42f6b76caba4ff27bee709cc429aa946743c))
* **ci:** remove Unresolved reference in GitHub Action ([1069d24](https://github.com/lackary/api-client-kmp/commit/1069d24e81e85f1f1f2f969f0f88c89e0c9f1e99))
* **web:** execute js failed in callback/index.html ([171fac4](https://github.com/lackary/api-client-kmp/commit/171fac45facd0e059983b44435d5bfb749148fdb))


### Features

* **MVI:** add a overloading method for MVI ([cf291b4](https://github.com/lackary/api-client-kmp/commit/cf291b4082420c43793e9d4e4768b8520031f60b))
* **OAuth:** add the relevant OAuth feature in core module ([b486e18](https://github.com/lackary/api-client-kmp/commit/b486e181189e2fada5172a7c38a0f0ecdfb8437c))
* **oauth:** implement Unsplash user authorization flow in sample app ([6d25a5d](https://github.com/lackary/api-client-kmp/commit/6d25a5ddccf0bee1165a025b3e901f0c6e1950a0))
* **webview:** add a WebView of BottomSheet for OAuth 2.0 flow ([e358529](https://github.com/lackary/api-client-kmp/commit/e3585292b480d363657515983ae062c086caade9))

# [0.6.0](https://github.com/lackary/api-client-kmp/compare/0.5.0...0.6.0) (2025-10-21)


### Features

* **web:** add OAuth 2.0 redirect url ([793ac2a](https://github.com/lackary/api-client-kmp/commit/793ac2a62c486e7fb7af78b5025306afd85f61e5))

# [0.5.0](https://github.com/lackary/api-client-kmp/compare/0.4.0...0.5.0) (2025-10-12)


### Bug Fixes

* **build:** build failed in task ':composeApp:linkDebugTestIosSimulatorArm64' ([e60ff55](https://github.com/lackary/api-client-kmp/commit/e60ff5577843931d2f7c961083a635dfa48e7e48))
* **build:** build failed without google-services.json ([a21ce31](https://github.com/lackary/api-client-kmp/commit/a21ce319b1bcc2a841fde83c68a85c052b9837b7))
* **build:** fix build failed by Unresolved reference ([42aeb2d](https://github.com/lackary/api-client-kmp/commit/42aeb2d49579554bb8eef627dc0bfa4b44d52d33))
* **ci:** build failed by ld invocation reported errors ([b01caa2](https://github.com/lackary/api-client-kmp/commit/b01caa24b8bafd5a4b4f183afd5e9c8057d9af58))
* **ci:** build failed by link error ([49c8dd1](https://github.com/lackary/api-client-kmp/commit/49c8dd12bc9a94f53f1d044945f8841968c746fd))
* **ci:** build failed in GitHub Action Build and Test [#23](https://github.com/lackary/api-client-kmp/issues/23) ([6fcfbf0](https://github.com/lackary/api-client-kmp/commit/6fcfbf0ef07222f49639687d042b552aba96b093))
* **ci:** build failed in GitHub Action Build and Test [#25](https://github.com/lackary/api-client-kmp/issues/25) ([4aaef6a](https://github.com/lackary/api-client-kmp/commit/4aaef6acd93214dfe314265b1238abd49b4d74fb))
* **ci:** build failed without turning on android.useAndroidX property ([f05895c](https://github.com/lackary/api-client-kmp/commit/f05895c3c838289b892096715f1ed85036a16033))
* **ci:** build failed without UNSPLASH_API_KEY ([d48d356](https://github.com/lackary/api-client-kmp/commit/d48d356719d1f3b9094c337c53f0b5ff9c45f9ec))


### Features

* **auth:** add Google login with KMPAuth in Android and iOS ([ab86d6d](https://github.com/lackary/api-client-kmp/commit/ab86d6d80b0499be617c5745cbdec921b0e6e7a6))

# [0.4.0](https://github.com/lackary/api-client-kmp/compare/0.3.0...0.4.0) (2025-09-22)


### Bug Fixes

* **ci:** Compose Multiplatform test failed in  the test task ([86c3cc7](https://github.com/lackary/api-client-kmp/commit/86c3cc7e774c1242f7b5ab2ca9a76dcbf71616cd))
* **ci:** Compose Multiplatform test failed in  the test task ([24d46ad](https://github.com/lackary/api-client-kmp/commit/24d46ad3332e8500a245bd12c137910726edb7c8))


### Features

* **app:** Create Compose Multiplatform app ([bf6c18d](https://github.com/lackary/api-client-kmp/commit/bf6c18d00b6f61c0d4a35f311f1bfd95906c42bb))

# [0.3.0](https://github.com/lackary/api-client-kmp/compare/0.2.1...0.3.0) (2025-09-17)


### Bug Fixes

* **ci:** Build failure due to missing AndroidX property ([3a171a4](https://github.com/lackary/api-client-kmp/commit/3a171a4cc0dff35f17a4abef55bada0f9b7dc883))


### Features

* **module:** Create ui module ([41914c8](https://github.com/lackary/api-client-kmp/commit/41914c82565a49e683504b5c9670ec5425174ec6))

## [0.2.1](https://github.com/lackary/api-client-kmp/compare/0.2.0...0.2.1) (2025-09-09)


### Bug Fixes

* **ci:** Add API key in the automated release ([1557a01](https://github.com/lackary/api-client-kmp/commit/1557a015716a73ec9287f1a6888e1842086ca914))

# [0.2.0](https://github.com/lackary/api-client-kmp/compare/0.1.0...0.2.0) (2025-09-09)


### Bug Fixes

* **ci:** read API key from environment ([abca10c](https://github.com/lackary/api-client-kmp/commit/abca10c79feded09cf9cb68ba67b2d7ea4ab5426))


### Features

* **module:** Create unsplash-api-client module ([81d6231](https://github.com/lackary/api-client-kmp/commit/81d623182e0bd3d3f0d833a53df94f7f5f008077))

# [0.1.0](https://github.com/lackary/api-client-kmp/compare/0.0.1...0.1.0) (2025-09-09)


### Bug Fixes

* **ci:** add package-lock.json to fix CI build ([35d6324](https://github.com/lackary/api-client-kmp/commit/35d6324d24f4f8901b4c6913c29966598ff5447a))


### Features

* **http:** Add a dynamically install http plugin ([477c8d3](https://github.com/lackary/api-client-kmp/commit/477c8d38c8da8881505992d2bb3c533ee2d05d13))
* **logging:** Add app logger ([5f6d41d](https://github.com/lackary/api-client-kmp/commit/5f6d41d105fe964926e8b163bfb6f04758ccd310))
* **logging:** Add tag into each log levels in AppLogger ([8b8f404](https://github.com/lackary/api-client-kmp/commit/8b8f40470e0c38514bb63a0067ec72ea7b124d88))
* **module:** Create core module ([491647d](https://github.com/lackary/api-client-kmp/commit/491647d14f8e4fc42fcdbbd72a8db6e438fb399b))
