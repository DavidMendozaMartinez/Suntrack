import com.android.build.api.dsl.ApplicationExtension
import com.davidmendozamartinez.sunrating.BuildDefaults
import com.davidmendozamartinez.sunrating.addCommonDependencies
import com.davidmendozamartinez.sunrating.configureCommonExtension
import com.davidmendozamartinez.sunrating.configureKotlinAndroidProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")

            configureCommonExtension<ApplicationExtension> {
                defaultConfig {
                    targetSdk = BuildDefaults.AndroidSdk.TARGET
                }
            }
            configureKotlinAndroidProjectExtension()

            dependencies {
                addCommonDependencies()
            }
        }
    }
}
