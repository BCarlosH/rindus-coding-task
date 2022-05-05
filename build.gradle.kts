plugins {
    id(Plugins.androidApplication) version Plugins.Versions.androidApplication apply false
    id(Plugins.androidLibrary) version Plugins.Versions.androidLibrary apply false
    id(Plugins.jetbrainsKotlinAndroid) version Plugins.Versions.jetbrainsKotlinAndroid apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
