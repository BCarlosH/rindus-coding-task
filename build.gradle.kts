import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

// TODO: remove buildscript{} block if it contains nothing but comments
buildscript {
    // Don't declare classpath dependencies here.
    // Instead, declare them as implementation dependency in `/buildSrc/build.gradle.kts`.
    // It has the same outcome on the configuration of the project with the benefit that
    // we can use type-safe kotlin scripts defined in `buildSrc` sources.
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" // Run: .\gradlew ktlintFormat
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<KtlintExtension> {
        debug.set(true)
        ignoreFailures.set(true)
        disabledRules.set(setOf("no-wildcard-imports"))
        reporters {
            reporter(ReporterType.CHECKSTYLE)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
