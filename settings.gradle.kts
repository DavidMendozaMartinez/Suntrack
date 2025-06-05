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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SunRating"
include(":app")
include(":common")
include(":data")
include(":domain")
include(":feature:events")
include(":feature:places")
include(":feature:settings")
include(":framework:database")
include(":framework:location")
include(":framework:network")
include(":framework:notification")
include(":framework:preferences")
include(":framework:schedule")
include(":ui")
