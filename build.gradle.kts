// TODO: remove buildscript{} block if it contains nothing but comments
buildscript {
    // Don't declare classpath dependencies here.
    // Instead, declare them as implementation dependency in `/buildSrc/build.gradle.kts`.
    // It has the same outcome on the configuration of the project with the benefit that
    // we can use type-safe kotlin scripts defined in `buildSrc` sources.
}

// TODO: add ktlint plugin

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
