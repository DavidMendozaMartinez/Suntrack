import com.android.build.gradle.LibraryExtension
import com.davidmendozamartinez.sunrating.addCommonKotlinDependencies
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            configureLibraryExtension()
            configureKotlinAndroidProjectExtension()

            dependencies {
                addCommonKotlinDependencies()
            }
        }
    }

    private fun Project.configureLibraryExtension() {
        extensions.configure<LibraryExtension> {
            compileSdk = 35

            defaultConfig {
                minSdk = 24
            }

            testOptions {
                targetSdk = 35
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }

    private fun Project.configureKotlinAndroidProjectExtension() {
        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }
}
