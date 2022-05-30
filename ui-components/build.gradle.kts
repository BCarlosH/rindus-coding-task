plugins {
    id("scripts.common-android")
}

dependencies {
    implementation(Libs.AndroidX.androidxCoreKtx)

    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}
