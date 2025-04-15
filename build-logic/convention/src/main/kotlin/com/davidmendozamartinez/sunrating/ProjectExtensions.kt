package com.davidmendozamartinez.sunrating

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.configureCommonExtension(
    crossinline action: T.() -> Unit = {},
) {
    extensions.configure<T> {
        compileSdk = BuildDefaults.AndroidSdk.COMPILE

        defaultConfig {
            minSdk = BuildDefaults.AndroidSdk.MIN
        }

        compileOptions {
            sourceCompatibility = BuildDefaults.JavaCompilation.SourceCompatibility
            targetCompatibility = BuildDefaults.JavaCompilation.TargetCompatibility
        }

        action()
    }
}

fun Project.configureJavaPluginExtension() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = BuildDefaults.JavaCompilation.SourceCompatibility
        targetCompatibility = BuildDefaults.JavaCompilation.TargetCompatibility
    }
}

fun Project.configureKotlinAndroidProjectExtension() {
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(BuildDefaults.KotlinCompiler.JvmTarget)
        }
    }
}

fun Project.configureKotlinJvmProjectExtension() {
    extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(BuildDefaults.KotlinCompiler.JvmTarget)
        }
    }
}
