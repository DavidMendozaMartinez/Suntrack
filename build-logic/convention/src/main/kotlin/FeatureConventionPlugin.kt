import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "sunrating.android.library")
            apply(plugin = "sunrating.compose")
            apply(plugin = "sunrating.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                add(configurationName = "implementation", dependencyNotation = project(":common"))
                add(configurationName = "implementation", dependencyNotation = project(":domain"))
                add(configurationName = "implementation", dependencyNotation = project(":ui"))
                add(configurationName = "implementation", dependencyNotation = "androidx.hilt:hilt-navigation-compose:1.2.0")
                add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
                add(configurationName = "implementation", dependencyNotation = "com.google.accompanist:accompanist-permissions:0.37.3")
            }
        }
    }
}
