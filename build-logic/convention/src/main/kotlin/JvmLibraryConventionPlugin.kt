import com.davidmendozamartinez.sunrating.addKotlinCommonDependencies
import com.davidmendozamartinez.sunrating.configureJavaPluginExtension
import com.davidmendozamartinez.sunrating.configureKotlinJvmProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "java-library")
            apply(plugin = "org.jetbrains.kotlin.jvm")

            configureJavaPluginExtension()
            configureKotlinJvmProjectExtension()

            dependencies {
                addKotlinCommonDependencies()
            }
        }
    }
}
