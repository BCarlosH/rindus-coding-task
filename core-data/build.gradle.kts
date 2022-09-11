plugins {
    id("scripts.common-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core-model"))
    implementation(project(":core-network"))

    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.androidCompiler)

    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Coroutines.test)
    testImplementation(Libs.AndroidX.Test.core)
    testImplementation(Libs.AndroidX.Test.Ext.junit)
    testImplementation(Libs.Mockk.mockk)
}
