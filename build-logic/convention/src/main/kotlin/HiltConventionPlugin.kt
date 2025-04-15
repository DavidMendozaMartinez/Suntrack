import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.dagger.hilt.android")
            apply(plugin = "kotlin-kapt")

            dependencies {
                add(configurationName = "implementation", dependencyNotation = "com.google.dagger:hilt-android:2.51.1")
                add(configurationName = "kapt", dependencyNotation = "com.google.dagger:hilt-android-compiler:2.51.1")
            }
        }
    }
}
