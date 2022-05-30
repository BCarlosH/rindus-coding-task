package scripts

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Config.compileSdk
    defaultConfig {
        minSdk = Config.minSdk // TODO: check the warnings after updating com.android.tools.build:gradle:7.2.1
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}
