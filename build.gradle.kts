import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" // Run: .\gradlew ktlintFormat
    id("com.github.ben-manes.versions") version "0.42.0" // Run: .\gradlew dependencyUpdates
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

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        val stableKeyword =
            listOf("RELEASE", "FINAL", "GA").any { candidate.version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(candidate.version)
        isStable.not()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
