plugins {
    id("scripts.common-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.androidCompiler)

    testImplementation(Libs.JUnit.junit)
}
