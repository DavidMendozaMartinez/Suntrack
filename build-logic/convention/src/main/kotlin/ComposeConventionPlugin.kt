import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val commonExtension: CommonExtension<*, *, *, *, *, *>? = when {
                plugins.hasPlugin("com.android.application") -> extensions.getByType<ApplicationExtension>()
                plugins.hasPlugin("com.android.library") -> extensions.getByType<LibraryExtension>()
                else -> null
            }
            commonExtension?.buildFeatures?.compose = true

            dependencies {
                add(configurationName = "implementation", dependencyNotation = platform("androidx.compose:compose-bom:2025.02.00"))
                add(configurationName = "implementation", dependencyNotation = "androidx.compose.material3:material3")
                add(configurationName = "implementation", dependencyNotation = "androidx.compose.ui:ui-tooling-preview")
                add(configurationName = "debugImplementation", dependencyNotation = "androidx.compose.ui:ui-tooling")
                add(configurationName = "implementation", dependencyNotation = "androidx.navigation:navigation-compose:2.8.9")
            }
        }
    }
}
