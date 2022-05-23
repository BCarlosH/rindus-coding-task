object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"

    object Versions {
        const val androidApplication = "7.2.0"
        const val androidLibrary = "7.2.0"
        const val jetbrainsKotlinAndroid = "1.6.10"
    }
}

object Libs {

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val androidxCoreKtx = "androidx.core:core-ktx:1.7.0"

        object Compose {
            private const val version = "1.1.1"
            const val foundation = "androidx.compose.foundation:foundation:${version}"
            const val ui = "androidx.compose.ui:ui:${version}"
            const val runtime = "androidx.compose.runtime:runtime:${version}"
            const val material = "androidx.compose.material:material:${version}"
            const val animation = "androidx.compose.animation:animation:${version}"
            const val tooling = "androidx.compose.ui:ui-tooling:${version}"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${version}"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Lifecycle {
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
        }

        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.4.2"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        }
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        // TODO: add the converter Gson, Moshi, etc
    }

    object OkHttp3 {
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.8.1"
    }

    object Hilt {
        private const val version = "2.41"
        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val androidTesting = "com.google.dagger:hilt-android-testing:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
    }
}
