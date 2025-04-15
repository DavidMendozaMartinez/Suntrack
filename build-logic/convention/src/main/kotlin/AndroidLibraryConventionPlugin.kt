import com.android.build.gradle.LibraryExtension
import com.davidmendozamartinez.sunrating.addKotlinCommonDependencies
import com.davidmendozamartinez.sunrating.configureCommonExtension
import com.davidmendozamartinez.sunrating.configureKotlinAndroidProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            configureCommonExtension<LibraryExtension>()
            configureKotlinAndroidProjectExtension()

            dependencies {
                addKotlinCommonDependencies()
            }
        }
    }
}
