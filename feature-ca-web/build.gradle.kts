plugins {
    id("scripts.common-android")
    id("scripts.common-android-compose")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-model"))
    implementation(project(":core-data"))

    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Navigation.navigationCompose)
    implementation(Libs.AndroidX.hiltNavigationCompose)
    debugImplementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.androidCompiler)
    implementation(Libs.Accompanist.webView)
}
