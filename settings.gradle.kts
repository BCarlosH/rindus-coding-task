pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rindus-coding-task"
include(":app")
include(":core-ui")
include(":core-network")
include(":core-data")
include(":core-model")
include(":feature-weather")
include(":feature-ca-web")
