pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Subway"
include(":app")
include(":data")
include(":domain")

// Core
include(
    ":core:ui",
    ":core:navigation",
    ":core:designsystem",
)

// Feature
include(
    ":feature:main",
    ":feature:home",
    ":feature:detail",
)
