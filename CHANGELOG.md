## [0.13.3](https://github.com/lackary/omnifeed-kmp/compare/0.13.2...0.13.3) (2026-09-07)


### Bug Fixes

* **deps:** update major updates ([0645ed6](https://github.com/lackary/omnifeed-kmp/commit/0645ed6506d12f8b950258f4129bbbc17c4b23ef))

## [0.13.2](https://github.com/lackary/omnifeed-kmp/compare/0.13.1...0.13.2) (2026-09-07)


### Bug Fixes

* **deps:** update dependency org.jetbrains.kotlin-wrappers:kotlin-browser to v2026.9.1 ([13d7060](https://github.com/lackary/omnifeed-kmp/commit/13d7060dfe58b899c0bbea89122d7d8061ca5769))

## [0.13.1](https://github.com/lackary/omnifeed-kmp/compare/0.13.0...0.13.1) (2026-09-06)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([f3b0f24](https://github.com/lackary/omnifeed-kmp/commit/f3b0f240daea50cf9950666bfbc68ddc44cbfadd))

# [0.13.0](https://github.com/lackary/omnifeed-kmp/compare/0.12.9...0.13.0) (2026-09-04)


### Bug Fixes

* **auth:** add UID validation for Firestore operations ([d0ce941](https://github.com/lackary/omnifeed-kmp/commit/d0ce941190cda74c2fbe854ed9e6246b76f3d27c))
* **auth:** call backend endpoint during custom service linking and enhance repository logging ([1cd29ce](https://github.com/lackary/omnifeed-kmp/commit/1cd29ceeb2cfb8a8bfecd0163853a92e4f5b7b9d))
* **auth:** clear deep link buffer on sign-out ([0009d6e](https://github.com/lackary/omnifeed-kmp/commit/0009d6e16c7bbbe33a25d8a7b6b490cb80ad699c))
* **auth:** ensure accurate provider information by syncing token data ([aeacca4](https://github.com/lackary/omnifeed-kmp/commit/aeacca4ca08748af2c7e1f327e44bba219ffc0b4))
* **auth:** handle email collision and support account mapping in custom sign-in ([e2e9f29](https://github.com/lackary/omnifeed-kmp/commit/e2e9f29ac98ea65dd963c12dbbea66b652e3b4f2))
* **auth:** prevent username updates from being overwritten by stale Firestore data ([89b3df0](https://github.com/lackary/omnifeed-kmp/commit/89b3df05e3eec4d272130005c528d9d9070a6e28))
* **extension:** downgrade Node.js runtime and remove httpsTrigger allowInsecure ([e873ad9](https://github.com/lackary/omnifeed-kmp/commit/e873ad9ddcc2ae1675c4c6b7ad6e4903f447303f))
* **extension:** synchronize profile info during account linking ([699537e](https://github.com/lackary/omnifeed-kmp/commit/699537e0dfd6695f4b981502677b2ab29c76fb21))


### Features

* **auth:** add script to encode Firebase configuration files to Base64 ([edafc2e](https://github.com/lackary/omnifeed-kmp/commit/edafc2ec8785e8b574e84d0098a38f90603cfea2))
* **auth:** add user management use cases ([579386f](https://github.com/lackary/omnifeed-kmp/commit/579386fe83f84e33cd82a09556e0d9761d4677fe))
* **auth:** clear service tokens when unlinking provider ([f943110](https://github.com/lackary/omnifeed-kmp/commit/f943110f8a7f10fc58c825265ef7c06caeebed6d))
* **auth:** enhance error handling and standardize use case results ([373b3b1](https://github.com/lackary/omnifeed-kmp/commit/373b3b1a4f0ee51c1152ebf8dac2bfe234ba2305))
* **auth:** enhance reliability and user experience of platform auth managers ([8785cea](https://github.com/lackary/omnifeed-kmp/commit/8785cea3b4b6bc946575bebc563b027f01a03955))
* **auth:** externalize Google Auth Bridge JavaScript to resource file ([0a26518](https://github.com/lackary/omnifeed-kmp/commit/0a2651809120ec29e70a7f065ce9c5ea3d64cda5))
* **auth:** implement advanced account management features ([55eacb0](https://github.com/lackary/omnifeed-kmp/commit/55eacb094ff1b8664eec1611f3d96bdf4d55b042))
* **auth:** implement cross-platform Firebase Auth logic and refactor data models ([c3b1235](https://github.com/lackary/omnifeed-kmp/commit/c3b1235a45e41547693933312034a111bc0d8a2b))
* **auth:** implement encrypted service token sync and profile consistency ([fbce716](https://github.com/lackary/omnifeed-kmp/commit/fbce7169488b2df6e786b66da1fb58ece195d37f))
* **auth:** implement Firestore profile synchronization and REST fallback in JVM platform ([bbfaad0](https://github.com/lackary/omnifeed-kmp/commit/bbfaad02ff38c1e53243baf6b9dbe12d810294ea))
* **auth:** implement Firestore REST API and refactor Firebase services ([b110cf3](https://github.com/lackary/omnifeed-kmp/commit/b110cf345cedbbd7515f9ce1c920790bf922e8ef))
* **auth:** implement generic custom auth service and firebase extension ([3d8da16](https://github.com/lackary/omnifeed-kmp/commit/3d8da16a71529690f7e26c1168020b484a2f404b))
* **auth:** implement local persistence and dynamic token resolution ([58f942b](https://github.com/lackary/omnifeed-kmp/commit/58f942bd4988f1751e8e62ae2efb0427c1db026f))
* **auth:** implement multiplatform authentication manager ([35006ee](https://github.com/lackary/omnifeed-kmp/commit/35006ee0be80a62b76e3d893ceca21e090002764))
* **auth:** implement multiplatform Firebase initialization ([91fe66e](https://github.com/lackary/omnifeed-kmp/commit/91fe66e6585d898587cc5250103e1867cf26685b))
* **auth:** implement multiplatform local storage for auth persistence ([4d36e90](https://github.com/lackary/omnifeed-kmp/commit/4d36e90569cdf4fe3fff3d069af0314d1fedb796))
* **auth:** implement OAuth popup sign-in for web platform ([b207c5a](https://github.com/lackary/omnifeed-kmp/commit/b207c5a8fb49f16a0dff0eb93e3109a20e72914e))
* **auth:** implement re-authentication and enhance error handling ([f74b638](https://github.com/lackary/omnifeed-kmp/commit/f74b6381b7cafbd0c36d6c83e81060a3dbd329de))
* **auth:** implement REST fallback for Google Sign-In ([275bfe7](https://github.com/lackary/omnifeed-kmp/commit/275bfe78eba3bd80524e3768410b870121621352))
* **auth:** implement sticky identity enforcement and enhance token management ([32546b9](https://github.com/lackary/omnifeed-kmp/commit/32546b956a82991a10f5cae791efa07fa59b3c44))
* **auth:** implement unified multiplatform authentication system ([f35a0cd](https://github.com/lackary/omnifeed-kmp/commit/f35a0cdd551ca631d11ae2c83298fb79b9b091a9))
* **auth:** implement Unsplash account linking and status tracking ([921bbb6](https://github.com/lackary/omnifeed-kmp/commit/921bbb6576306c68d1fe37bb40f98f958101565a))
* **auth:** implement update username functionality and enhance error handling ([74d6308](https://github.com/lackary/omnifeed-kmp/commit/74d63088e03ae0c888fdfba2a3103b2a0c7cd5c7))
* **auth:** integrate Google Identity Services via JS bridge for web ([c2e261a](https://github.com/lackary/omnifeed-kmp/commit/c2e261a57469fce375cf633944fe753fe2cae1e0))
* **auth:** introduce omnifeed-auth module ([6710aab](https://github.com/lackary/omnifeed-kmp/commit/6710aab99f706c167a669b85930b5693368eda01))
* **auth:** migrate auth providers configuration to environment variables ([7e85a4c](https://github.com/lackary/omnifeed-kmp/commit/7e85a4ce048d0222b56446bd0b2638a24267e7c6))
* **auth:** persist and synchronize user state during account linking and unlinking ([d1e2d46](https://github.com/lackary/omnifeed-kmp/commit/d1e2d46aed0cfd7b382207c9cf45b1dc47baf8a0))
* **auth:** persist authenticated user sessions ([9077c1e](https://github.com/lackary/omnifeed-kmp/commit/9077c1e3f9d92ca7f84eb9be834ae13f25519944))
* **auth:** populate Firebase user profile with third-party info ([84dbb25](https://github.com/lackary/omnifeed-kmp/commit/84dbb2513ddebd0b0927267e1f953adc596c1c26))
* **auth:** refactor user profile schema and enhance data synchronization ([44be022](https://github.com/lackary/omnifeed-kmp/commit/44be02298198eb4dee04e502d4fd0ce4fb00e5f9))
* **auth:** update WasmJs repository to match interface changes ([c6ae055](https://github.com/lackary/omnifeed-kmp/commit/c6ae0553e03263735c4c22007ca7e601053e900d))
* **firebase:** add platform-specific friendly error message utility ([cf32712](https://github.com/lackary/omnifeed-kmp/commit/cf327129fd3bd62460187395639dc5e6aa05a82a))
* **unsplash:** update user models with missing stats and null-safe private fields ([d1f5a92](https://github.com/lackary/omnifeed-kmp/commit/d1f5a923cb6ce87eda90f6883df33808b4e78229))

## [0.12.9](https://github.com/lackary/omnifeed-kmp/compare/0.12.8...0.12.9) (2026-06-01)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([641acef](https://github.com/lackary/omnifeed-kmp/commit/641acef91f80be7cb737f638431d976d6c4df51e))

## [0.12.8](https://github.com/lackary/omnifeed-kmp/compare/0.12.7...0.12.8) (2026-05-28)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([487a0ab](https://github.com/lackary/omnifeed-kmp/commit/487a0ab7a343dc777ab3b2af97778fde233725b0))

## [0.12.7](https://github.com/lackary/omnifeed-kmp/compare/0.12.6...0.12.7) (2026-05-11)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([c471806](https://github.com/lackary/omnifeed-kmp/commit/c4718062f6ff605126d9d056cac885a861fa6c2b))

## [0.12.6](https://github.com/lackary/omnifeed-kmp/compare/0.12.5...0.12.6) (2026-05-05)


### Bug Fixes

* **unsplash:** make portfolio property nullable ([e8b9ebe](https://github.com/lackary/omnifeed-kmp/commit/e8b9ebe0a91429d719c87c93aadda8456633bdf2))

## [0.12.5](https://github.com/lackary/omnifeed-kmp/compare/0.12.4...0.12.5) (2026-04-27)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([2a18ef4](https://github.com/lackary/omnifeed-kmp/commit/2a18ef455a2345ad6a3e5cf35ffd78a195794859))

## [0.12.4](https://github.com/lackary/omnifeed-kmp/compare/0.12.3...0.12.4) (2026-04-21)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([fc3c9ff](https://github.com/lackary/omnifeed-kmp/commit/fc3c9ffe724b17e0ffedfcc2f50bc84d99006f51))

## [0.12.3](https://github.com/lackary/omnifeed-kmp/compare/0.12.2...0.12.3) (2026-04-06)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([1ed67a9](https://github.com/lackary/omnifeed-kmp/commit/1ed67a921d113bff0f6e94a9e5574af2d255287c))

## [0.12.2](https://github.com/lackary/omnifeed-kmp/compare/0.12.1...0.12.2) (2026-03-23)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([c45c440](https://github.com/lackary/omnifeed-kmp/commit/c45c4406a3ed463484281dbe959a86ac290caf44))

## [0.12.1](https://github.com/lackary/omnifeed-kmp/compare/0.12.0...0.12.1) (2026-03-16)


### Bug Fixes

* **deps:** update non-major updates (patch & minor) ([b1ea1c7](https://github.com/lackary/omnifeed-kmp/commit/b1ea1c7213a400d34be4dd98e48cea34d37fcb35))

# [0.12.0](https://github.com/lackary/omnifeed-kmp/compare/0.11.0...0.12.0) (2026-02-06)


### Features

* **ui:** improve logging and diagnostic capabilities in `BaseViewModel` ([d33d6e2](https://github.com/lackary/omnifeed-kmp/commit/d33d6e2c2d41448989490ceb805119ca6075258f))

# [0.11.0](https://github.com/lackary/omnifeed-kmp/compare/0.10.2...0.11.0) (2026-02-04)


### Features

* **sdk:** introduce unified OmniFeed SDK entry point and refactor dependency injection ([466c0f9](https://github.com/lackary/omnifeed-kmp/commit/466c0f9064af5e0bb178bad95df2cdece53aff04))

## [0.10.2](https://github.com/lackary/omnifeed-kmp/compare/0.10.1...0.10.2) (2026-01-19)


### Bug Fixes

* **core,ui:** handle `JsonConvertException` and update error messaging ([e86884f](https://github.com/lackary/omnifeed-kmp/commit/e86884ffb4febc6e4ec4511dfc1b08c9a7ffcdd5))
* **unsplash:** handle nullable fields in API responses and domain models ([ae848ef](https://github.com/lackary/omnifeed-kmp/commit/ae848ef3856ec2503263c5de0573636d229c8255))

## [0.10.1](https://github.com/lackary/omnifeed-kmp/compare/0.10.0...0.10.1) (2026-01-04)


### Bug Fixes

* **build:** exclude unstable JogAmp deps and update CI to assembleDebug ([889ec7f](https://github.com/lackary/omnifeed-kmp/commit/889ec7f5427ae82453211c0deb064ed473374b6c))

# [0.10.0](https://github.com/lackary/omnifeed-kmp/compare/0.9.1...0.10.0) (2025-12-22)


### Features

* **platform:** add experimental WasmJs support ([72f693e](https://github.com/lackary/omnifeed-kmp/commit/72f693e0b0414fcf9240e0942cf1c76aa501487e))

## [0.9.1](https://github.com/lackary/omnifeed-kmp/compare/0.9.0...0.9.1) (2025-12-05)


### Bug Fixes

* **ios:** correct gradlew path in Xcode build phase script ([9e579c3](https://github.com/lackary/omnifeed-kmp/commit/9e579c35c5f4e1237470100bf4ae761438825e81))
* **web:** resolve the callback url not found ([5996369](https://github.com/lackary/omnifeed-kmp/commit/5996369dcd3faf399100f0625938790b2921f541))

# [0.9.0](https://github.com/lackary/api-client-kmp/compare/0.8.1...0.9.0) (2025-11-28)


### Bug Fixes

* **test:** resolve the PhotoResponseTest failed ([35a763d](https://github.com/lackary/api-client-kmp/commit/35a763df531491f1bf69132f70f62c1023d3685f))
* **viewmodel:** remove hardcoded value in exchangeOAuthIntent ([868d6b6](https://github.com/lackary/api-client-kmp/commit/868d6b6a5bde98b911927c73a80679b41fa50b6e))


### Features

* **api:** implement the remaining Unsplash GET API endpoints. ([2435f3a](https://github.com/lackary/api-client-kmp/commit/2435f3af5cef0c9c79f9a73253880840e8f92890))
* **api:** implement Unsplash collections core API endpoints ([fdaedb8](https://github.com/lackary/api-client-kmp/commit/fdaedb8c75faf4c3e0aa57c9d281ca89ccf33ea0))
* **api:** implement Unsplash topics core API  endpoint ([9b7dcf0](https://github.com/lackary/api-client-kmp/commit/9b7dcf0904846187c4dc4763894190fc9e634b7f))
* **api:** implement Unsplash user core API endpoint ([26da7d0](https://github.com/lackary/api-client-kmp/commit/26da7d04c41c5824108c8210449b24c6ac168159))
* **data:** expose new Unsplash API methods in RemoteDataSource ([8249a8b](https://github.com/lackary/api-client-kmp/commit/8249a8b7eb9f22b9963d599d9dfb82d0a9a9756a))
* **repository:** complete Unsplash repository implementation and domain models ([1db1792](https://github.com/lackary/api-client-kmp/commit/1db1792ac2a9fd6e61b60087eafc8b63bad668e4))
* **test:** implement the api service mock test ([8c655ff](https://github.com/lackary/api-client-kmp/commit/8c655ffe5b13e1a952d4299b0fe3713093aef480))
* **usecase:**  implement remaining  the use cases of unsplash and add integration tests ([f6ee602](https://github.com/lackary/api-client-kmp/commit/f6ee6022f4426c729dbe21165362f7bd595c52d1))

## [0.8.1](https://github.com/lackary/api-client-kmp/compare/0.8.0...0.8.1) (2025-11-19)


### Bug Fixes

* **test:** resolve failing test in RemoteUnsplashDataSourceImplTest ([48f66b7](https://github.com/lackary/api-client-kmp/commit/48f66b713bbb3557cbdc7effdc7246831405a480))

# [0.8.0](https://github.com/lackary/api-client-kmp/compare/0.7.1...0.8.0) (2025-11-11)


### Bug Fixes

* **ui:** resolve the js callback was executed only once. ([36eff9c](https://github.com/lackary/api-client-kmp/commit/36eff9c701ca5054fc36c0c2b379dc64a4e94764))
* **web:** resolve the invalid parameter in callNative ([84c4935](https://github.com/lackary/api-client-kmp/commit/84c4935bc9d8213a4d2edc03934c1e262dcbb386))


### Features

* **api:** implement Unsplash API '/me'. ([8bcfd33](https://github.com/lackary/api-client-kmp/commit/8bcfd33d60624125a135f8b681e54a5bc0eb448f))
* **test:** add unit test for HttpClientExtenstion ([e59ac35](https://github.com/lackary/api-client-kmp/commit/e59ac35e5f57c365dddc0682e3e0ab2405e98bfe))
* **ui:** add GetMeUseCase into view layer ([72f1071](https://github.com/lackary/api-client-kmp/commit/72f1071a12223bfdbe6d9ee1bc0576875c219bf3))
* **web:** add a javascript callback to kmp code ([f47b395](https://github.com/lackary/api-client-kmp/commit/f47b395954030c9a7f05c48fd6e49f5fa2424561))
* **web:** display profile image and username ([a34e864](https://github.com/lackary/api-client-kmp/commit/a34e8642ab168b1209cb99cee13bd06af22a8f33))

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
