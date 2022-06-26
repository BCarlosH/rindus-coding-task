plugins {
    id("scripts.common-android")
    id("scripts.common-android-compose")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-model"))
    implementation(project(":core-data"))

    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)

    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}
