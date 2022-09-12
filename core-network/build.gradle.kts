plugins {
    id("scripts.common-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Libs.AndroidX.androidxCoreKtx)
    implementation(Libs.OkHttp3.loggingInterceptor)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitConverterGson)
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.androidCompiler)
}
