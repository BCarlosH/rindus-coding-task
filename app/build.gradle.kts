plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlinAndroid)
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.templateusingjetpackcompose"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    packagingOptions {
        resources.excludes.addAll(arrayOf("META-INF/AL2.0", "META-INF/LGPL2.1"))
    }
}

dependencies {
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)
    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    debugImplementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Navigation.navigationCompose)

    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}
