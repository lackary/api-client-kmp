Pod::Spec.new do |spec|
    spec.name                     = 'omnifeed_auth'
    spec.version                  = '0.13.2'
    spec.homepage                 = 'https://github.com/lackary/omnifeed-kmp'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'OmniFeed Authentication Module'
    spec.vendored_frameworks      = 'build/cocoapods/framework/OmniFeedAuth.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target    = '18.5'
    spec.dependency 'FirebaseAuth', '~> 12.14.0'
    spec.dependency 'FirebaseCore', '~> 12.14.0'
    spec.dependency 'FirebaseFirestore', '~> 12.14.0'
    spec.dependency 'GoogleSignIn', '~> 9.0.0'
    if !Dir.exist?('build/cocoapods/framework/OmniFeedAuth.framework') || Dir.empty?('build/cocoapods/framework/OmniFeedAuth.framework')
        raise "
        Kotlin framework 'OmniFeedAuth' doesn't exist yet, so a proper Xcode project can't be generated.
        'pod install' should be executed after running ':generateDummyFramework' Gradle task:
            ./gradlew :omnifeed-auth:generateDummyFramework
        Alternatively, proper pod installation is performed during Gradle sync in the IDE (if Podfile location is set)"
    end
    spec.xcconfig = {
        'ENABLE_USER_SCRIPT_SANDBOXING' => 'NO',
    }
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':omnifeed-auth',
        'PRODUCT_MODULE_NAME' => 'OmniFeedAuth',
    }
    spec.script_phases = [
        {
            :name => 'Build omnifeed_auth',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                    echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                    exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
end
